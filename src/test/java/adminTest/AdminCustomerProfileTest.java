package adminTest;


import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import portal.admin.CustomersMainPage;
import portal.admin.customer_page.CustomerProfilePage;
import portal.annotations.Admin;
import portal.annotations.TestType;
import portal.data.LinkProvider;
import portal.data.PopUpMessage;
import portal.data.UserData;
import portal.listener.BaseTestExtension;
import portal.providers.DataProvider;
import portal.providers.MainPageProvider;
import portal.utils.PopUp;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;
import static portal.data.enumdata.ButtonName.CHOOSE_TYPE_CONTRACT;
import static portal.data.enumdata.ContractType.INDIVIDUAL_CONTRACT;
import static portal.data.enumdata.TabName.ACTION;
import static portal.data.enumdata.TabName.INFORMATION;

@Admin.customers.customerProfile
@ExtendWith(BaseTestExtension.class)
public class AdminCustomerProfileTest {

    PopUp popUp = new PopUp();
    CustomersMainPage customersMainPage = new CustomersMainPage();
    CustomerProfilePage customerProfilePage = new CustomerProfilePage();
    MainPageProvider mainPageProvider = new MainPageProvider();
    DataProvider dataProvider = new DataProvider();
    LinkProvider linkProvider = dataProvider.linkProvider();
    UserData userData = dataProvider.userData();
    PopUpMessage popUpMessage = dataProvider.popUpMessage();


    @Admin.customers.customerProfile
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Заключение договора без назначенных менеджеров")
    @TestType.integration
    void signIndividualContractWithoutManagers() {
        mainPageProvider.adminLink(linkProvider.customersLink());
        customersMainPage
                .searchCustomer(userData.emailCustomerContractNoManagersAdmin())
                .clickFirstCustomerFromList();
        customerProfilePage
                .clickOnMenu(ACTION.getValue())
                .chooseContract(INDIVIDUAL_CONTRACT)
                .clickOnButton(CHOOSE_TYPE_CONTRACT.getValue());
        popUp.checkMessageText(popUpMessage.setServiceManager());
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Назначение менеджеров")
    @TestType.integration
    void setManagers() {
        mainPageProvider.adminLink(linkProvider.customersLink());
        customersMainPage
                .searchCustomer(userData.emailCustomerProfile())
                .clickFirstCustomerFromList();
        customerProfilePage.clickOnMenu(INFORMATION.getValue());
        step("Выбираем менеджера продаж", (step) -> {
            $x("//*[@data-field='sales_manager']").shouldHave(text("Менеджер продаж"));
            $x("//*[@name='sales_manager']//option[@label='" + userData.salesManager() + "']").click();
        });
        step("Выбираем 'сервис менеджера'", (step) -> {
            $x("//*[@data-field='service_manager']").shouldHave(text("Сервисный менеджер"));
            $x("//*[@name='service_manager']//option[@label='" + userData.unrealServiceManager() + "']").click();
        });
        step("кликаем на сохранить", (step) -> {
            $x("//*[text()='Сохранить']").click();
        });

    }
}
