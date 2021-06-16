package customerTest;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import portal.annotations.LK;
import portal.annotations.TestType;
import portal.config.WebConfig;
import portal.data.LinkProvider;
import portal.data.LkData;
import portal.data.SubscriptionData;
import portal.data.UserData;
import portal.data.enumdata.component.VMwareComponent;
import portal.listener.BaseTestExtension;
import portal.lk.CatalogPage;
import portal.lk.LKMainPage;
import portal.lk.MyServicesPage;
import portal.lk.NewSubscriptionOrderPage;
import portal.lk.login_pages.UserLoginPage;
import portal.lk.login_pages.UserRegistrationPage;
import portal.providers.DataProvider;
import portal.providers.MainPageProvider;
import portal.providers.SubscriptionPageFactory;
import portal.utils.PopUp;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static portal.data.enumdata.SubscriptionIdName.GHZ;
import static portal.data.enumdata.SubscriptionName.*;
import static portal.data.enumdata.TypeSubscription.COMMERCIAL;
import static portal.data.enumdata.TypeSubscription.TESTING;
import static portal.data.enumdata.TypeUser.CUSTOMER_OFFER;
import static portal.data.enumdata.TypeUser.NEW_CUSTOMER;
import static portal.data.enumdata.component.VMwareComponent.*;

@ExtendWith(BaseTestExtension.class)
public class NewCustomerTest {

    private final String URL = new WebConfig().getUrlLk();
    private final UserLoginPage loginLK = new UserLoginPage();
    private final UserRegistrationPage userRegistrationPage = new UserRegistrationPage();
    private final LKMainPage lkMainPage = new LKMainPage();
    private final DataProvider dataProvider = new DataProvider();
    private final UserData userData = dataProvider.userData();
    private final LinkProvider linkProvider = dataProvider.linkProvider();
    private final SubscriptionData subscriptionData = dataProvider.subscriptionData();
    private final LkData lkData = dataProvider.lkData();
    private final MyServicesPage myServicesPage = new MyServicesPage();
    private final CatalogPage catalogPage = new CatalogPage();
    private final MainPageProvider mainPageNewCustomer = new MainPageProvider(NEW_CUSTOMER);
    private final MainPageProvider mainPageOfferCustomer = new MainPageProvider(CUSTOMER_OFFER);
    private final NewSubscriptionOrderPage vmware3GHzOrder = new SubscriptionPageFactory().getCustomerOrderPage(GHZ);


    @Disabled("не выключена капча")
    @LK.createNewCustomer
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Создаем аккаунт в ЛК")
    @TestType.e2e
    public void createUser() {
        Selenide.clearBrowserCookies();
        open(URL);
        step("Кликаем на 'Регистрация'", (step) -> {
            $(".signin_header-container bs-button").click();
        });
        userRegistrationPage.stepOneCheckText();
        userRegistrationPage.stepTwoSetNewUser(
                dataProvider.getFullName(),
                dataProvider.getPhoneNumber(),
                dataProvider.getValidEmailAddress(),
                "6449966915",
                NEW_CUSTOMER.getPassword());
//         email.deleteAllMessage();

        userRegistrationPage.stepThreeCheckText();
        userRegistrationPage.stepFour();
        //   email.waitAndCheckCountNewEmail(2);
//        Message userEmail = email.waitAndGetNewLetterBySubject("Техносерв Cloud: Подтверждение регистрации на портале Техносерв Cloud");
        // получаем ссылку из текста письма
        //      String link = email.getUrlsFromMessage(userEmail).get(0);
        //заходим по ссылке
        //       open(link);
        userRegistrationPage.stepFive();
        //Входим в личный кабинет нового пользователя
        //    loginLK.inputData(userAdminData.emailCustomerLoginLK(), userAdminData.customerPassword());
        loginLK.clickLoginButton();
        lkMainPage.checkLogoIsVisible();
    }

    //TODO вынести текст
    @LK.createNewCustomer
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Проверка сообщения для нового пользователя")
    @TestType.web
    public void checkMessageOfNewCustomer() {
        mainPageNewCustomer.openLkLink(linkProvider.catalogLinkLK());
        lkMainPage.clickMyServicesPage();
        myServicesPage.checkText(lkData.contractMessage());
    }

    @Severity(value = SeverityLevel.NORMAL)
    @TestType.e2e
    @DisplayName("Проверяем возможность заказа услуги, заказчиком без договора")
    void create3GHzVMwareTestNewCustomer() {
        mainPageNewCustomer.openLkLink(linkProvider.catalogLinkLK());
        catalogPage.clickOrderSubscription(subscriptionData.vmware3GHzName());
        vmware3GHzOrder
                .clickOrderType(TESTING)
                .setValue(NOTE, VMWARE_3GHZ.getTestNote())
                .setValue(NOTE, INTERNET.getTestNote())
                .clickOrder();
        myServicesPage.checkText(lkData.contractMessage());
    }

    @Severity(value = SeverityLevel.NORMAL)
    @TestType.e2e
    @DisplayName("Проверяем возможность заказа  не более 7 услуг для заказчика без договора")
    void checkMaxSubscriptionNewCustomer() {
        mainPageNewCustomer.openLkLink(linkProvider.catalogLinkLK());
        Map<Enum, String> vmwareData = dataProvider.getComponentsValue(VMwareComponent.values());
        for (int i = 0; i < 8; i++) {
            catalogPage.clickOrderSubscription(subscriptionData.vmware3GHzName());
            vmware3GHzOrder
                    .clickOrderType(COMMERCIAL)
                    .setTariff(subscriptionData.tariffFact())
                    .setValue(VCPU, vmwareData.get(VCPU))
                    .setValue(VRAM, vmwareData.get(VRAM))
                    .setValue(HDD_10K, vmwareData.get(HDD_10K))
                    .setValue(NOTE, VMWARE_CLOSE.getCommercialNote())
                    .checkBox(subscriptionData.checkBoxBackup())
                    .checkBox(subscriptionData.checkBoxInet())
                    .clickOrder();
        }
        new PopUp().checkMessageText(dataProvider.popUpMessage().limitMessage());
    }

    @Disabled("Не закончен")
    @Severity(value = SeverityLevel.NORMAL)
    @TestType.e2e
    @DisplayName("Проверяем реквизиты для офертников")
    void checkMessageOfferCustomer() {
        mainPageOfferCustomer.openLkLink(linkProvider.catalogLinkLK());
    }
}
