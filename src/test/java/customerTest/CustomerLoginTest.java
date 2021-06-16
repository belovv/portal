package customerTest;


import com.codeborne.selenide.Selenide;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import portal.annotations.LK;
import portal.annotations.TestType;
import portal.config.WebConfig;
import portal.data.EmailMessage;
import portal.data.UserData;
import portal.email.EmailSteps;
import portal.listener.BaseTestExtension;
import portal.lk.LKMainPage;
import portal.lk.login_pages.UserLoginPage;
import portal.providers.DataProvider;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static portal.data.enumdata.TypeUser.CUSTOMER_NEW_SUBSCRIPTION;
import static portal.data.enumdata.TypeUser.USER_ADMIN;

@ExtendWith(BaseTestExtension.class)
public class CustomerLoginTest {

    final private String URL = new WebConfig().getUrlLk();
    UserLoginPage loginLK = new UserLoginPage();
    LKMainPage lkMainPage = new LKMainPage();
    DataProvider dataProvider = new DataProvider();
    UserData userData = dataProvider.userData();
    EmailMessage emailMessage = dataProvider.emailMessage();
    EmailSteps emailSteps = new EmailSteps();


    @Severity(value = SeverityLevel.BLOCKER)
    @LK.login
    @DisplayName("Входим в личный кабинет")
    @TestType.e2e
    void loginValidUser() {
        Selenide.clearBrowserCookies();
        open(URL);
        loginLK.inputData(CUSTOMER_NEW_SUBSCRIPTION.getEmail(), CUSTOMER_NEW_SUBSCRIPTION.getPassword());
        loginLK.clickLoginButton();
        lkMainPage.checkLogoIsVisible();
    }

    @Severity(value = SeverityLevel.MINOR)
    @LK.login
    @DisplayName("Входим в личный кабинет c неверным паролем")
    @TestType.e2e
    void loginWrongPassword() {
        Selenide.clearBrowserCookies();
        open(URL);
        loginLK.inputData(CUSTOMER_NEW_SUBSCRIPTION.getEmail(), USER_ADMIN.getPassword());
        loginLK.clickLoginButton();
        step("Получаем и проверяем сообщение о ошибке", (step) -> {
            $(".signin_server-error").shouldHave(exactText(
                    "Ошибка при входе в личный кабинет.\n" +
                            "Возможные причины:\n" +
                            "некорректная почта или пароль;\n" +
                            "компания не прошла проверку по ИНН.\n" +
                            "Укажите верный логин / пароль, восстановите пароль, если забыли, или свяжитесь с менеджером."));
        });
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @LK.login
    @DisplayName("Восстанавливаем пароль к личному кабинету")
    @TestType.e2e
    void restorePassword() {
        var password = "7NrYR7SsUwRE#";
        Selenide.clearBrowserCookies();
        open(URL);
        loginLK.clickRestorePassword();
        loginLK.inputEmail(userData.emailCustomerRestorePasswordLK());
        loginLK.clickSend();
        var email = emailSteps.getMessageBySubjectAndRecipient(
                emailMessage.resetPassword(),
                userData.emailCustomerRestorePasswordLK());
        open(emailSteps.getUrlFromMessage(email));
        step("Сохраняем новый пароль и переходим на страницу ввода пароля", (step) -> {
            $(".ng-bs-control-password_input input").setValue(password);
            $(".bs-control-password-and-confirm_confirm-container input").setValue(password);
            $(".signin_submit-button span").click();
            $(".restore-password_description").shouldHave(exactText("Пароль успешно изменен"));
            $(".signin_submit-button span").click();
        });
        loginLK.inputData(userData.emailCustomerRestorePasswordLK(), password);
        loginLK.clickLoginButton();
        lkMainPage.checkLogoIsVisible();


    }

    @Severity(value = SeverityLevel.MINOR)
    @LK.login
    @DisplayName("Проверяем страницу восстановления пароля")
    @TestType.web
    void checkRestorePasswordPage() {
        Selenide.clearBrowserCookies();
        open(URL + "/restore");
        step("Проверяем текст", (step) -> {
            $(".restore-password_header").shouldHave(exactText("Восстановление пароля"));
            $(".bs-control-grid-new_label").shouldHave((exactText("E-mail")));
            $(".restore-password_form-field-description").shouldHave(exactText("На ваш email будет отправлено письмо со ссылкой на страницу смены пароля"));
        });
    }

    @Severity(value = SeverityLevel.NORMAL)
    @LK.login
    @DisplayName("Вводим невалидную почту для восстановления пароля")
    @TestType.integration
    void restorePasswordWrongEmail() {
        Selenide.clearBrowserCookies();
        open(URL + "/restore");
        loginLK.inputEmail("");
        loginLK.clickSend();
        loginLK.checkWarning("Поле является обязательным");
        open(URL + "/restore");
        loginLK.inputEmail("f");
        loginLK.clickSend();
        loginLK.checkWarning("Введите корректный email адрес");
        loginLK.inputEmail(dataProvider.getUnValidEmailAddress());
        loginLK.clickSend();
        step("Проверяем всплывающий текст", (step) -> {
            $(".restore-password_server-error").shouldHave(exactText("Заказчик не найден"));
        });
    }

}





