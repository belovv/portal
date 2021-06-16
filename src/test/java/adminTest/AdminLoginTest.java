package adminTest;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import portal.admin.AdminLoginPage;
import portal.admin.AdminMainPage;
import portal.annotations.Admin;
import portal.annotations.TestType;
import portal.config.WebConfig;
import portal.data.EmailMessage;
import portal.data.UserData;
import portal.email.EmailSteps;
import portal.listener.BaseTestExtension;
import portal.providers.DataProvider;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static portal.data.enumdata.TypeUser.NEW_CUSTOMER;
import static portal.data.enumdata.TypeUser.USER_ADMIN;

@Admin.login
@ExtendWith(BaseTestExtension.class)
public class AdminLoginTest {

    AdminLoginPage adminLoginPage = new AdminLoginPage();
    AdminMainPage adminMainPage = new AdminMainPage();
    WebConfig webConfig = new WebConfig();
    DataProvider dataProvider = new DataProvider();
    UserData userData = dataProvider.userData();
    EmailMessage emailMessage = dataProvider.emailMessage();
    EmailSteps emailSteps = new EmailSteps();

    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Входим в Админку")
    @TestType.integration
    void loginValidAdmin() {
        Selenide.clearBrowserCookies();
        open(webConfig.getUrlAdmin());
        adminLoginPage
                .inputData(USER_ADMIN.getEmail(), USER_ADMIN.getPassword())
                .clickLoginButton();
        adminMainPage.checkLogoIsVisible();
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Входим в Админку c неверным паролем")
    @TestType.integration
    void loginWrongPassword() {
        Selenide.clearBrowserCookies();
        open(webConfig.getUrlAdmin());
        adminLoginPage
                .inputData(USER_ADMIN.getEmail(), NEW_CUSTOMER.getPassword())
                .clickLoginButton();
        adminLoginPage.checkErrorMessage(
                "Некорректные почта или пароль");
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Восстанавливаем пароль доступа к админке")
    @TestType.integration
    void forgotPasswordAdmin() {
        Selenide.clearBrowserCookies();
        open(webConfig.getUrlAdmin());
        step("Нажимаем на 'Забыли'", (step) -> {
            $(".bs-signin_forgot-password").click();
        });
        step("Вводим email", (step) -> {
            $("input[name='email']").setValue(userData.emailDefaultAdmin());
        });
        step("Нажимаем отправить", (step) -> {
            $(".ng-bs-restore-password_button").shouldHave(exactText("Отправить")).click();
        });
        var email = emailSteps.getMessageBySubjectAndRecipient(
                emailMessage.resetPassword(),
                userData.emailDefaultAdmin());
        open(emailSteps.getUrlFromMessage(email));
        step("Сохраняем новый пароль", (step) -> {
            $("#bsControlPasswordAndConfirm-0").setValue(userData.defaultAdminPassword());
            $("#bsControlPasswordAndConfirm-0_confirm").setValue(userData.defaultAdminPassword());
            $("button[type='button']").shouldHave(exactText("Отправить")).click();
        });
        adminLoginPage
                .inputData(userData.emailDefaultAdmin(), userData.defaultAdminPassword())
                .clickLoginButton();
        adminMainPage.checkLogoIsVisible();

    }
}
