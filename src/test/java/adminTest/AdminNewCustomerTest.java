package adminTest;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import portal.admin.CustomersMainPage;
import portal.admin.customer_page.CustomerProfilePage;
import portal.admin.customer_page.NewCustomerPage;
import portal.annotations.Admin;
import portal.annotations.TestType;
import portal.data.EmailMessage;
import portal.data.LinkProvider;
import portal.data.PopUpMessage;
import portal.data.UserData;
import portal.email.EmailSteps;
import portal.listener.BaseTestExtension;
import portal.providers.DataProvider;
import portal.providers.MainPageProvider;
import portal.utils.PopUp;
import portal.utils.Status;

import static portal.data.enumdata.ButtonName.*;
import static portal.data.enumdata.ContractType.INDIVIDUAL_CONTRACT;
import static portal.data.enumdata.ContractType.OFFER_CONTRACT;
import static portal.data.enumdata.Status.*;
import static portal.data.enumdata.TabName.ACTION;

@Admin.createNewCustomer
@ExtendWith(BaseTestExtension.class)
public class AdminNewCustomerTest {

    private final PopUp popUp = new PopUp();
    private final Status status = new Status();
    private final MainPageProvider mainPageProvider = new MainPageProvider();
    private final CustomersMainPage customersMainPage = new CustomersMainPage();
    private final NewCustomerPage newCustomerPage = new NewCustomerPage();
    private final CustomerProfilePage customerProfilePage = new CustomerProfilePage();
    private final EmailSteps emailSteps = new EmailSteps();
    private final DataProvider dataProvider = new DataProvider();
    private final PopUpMessage popUpMessage = dataProvider.popUpMessage();
    private final LinkProvider linkProvider = dataProvider.linkProvider();
    private final UserData userData = dataProvider.userData();
    private final EmailMessage emailMessage = dataProvider.emailMessage();

    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Добавляем нового заказчика без отправки письма")
    @TestType.e2e
    void newCustomerNoEmail() {
        final var customerEmail = dataProvider.getValidEmailAddress();
        final var inn = "7020006645";
        mainPageProvider.adminLink(linkProvider.newCustomerLink());
        newCustomerPage
                .customerName(dataProvider.getFullName())
                .customerPhone(dataProvider.getPhoneNumber())
                .customerEmail(customerEmail)
                .customerINN(inn)
                .clickNoEmail()
                .clickButtonNext()
                .chooseSalesManager(userData.salesManager());
        var shortName = newCustomerPage.getShortName();
        newCustomerPage.clickSaveButton();
        popUp.checkMessageText(popUpMessage.accountCreated());
        customersMainPage
                .searchCustomer(customerEmail)
                .checkShortName(shortName)
                .checkEmail(customerEmail);
        status.checkStatus(MAIL_CHECKED.getValue());
        emailSteps.checkThatNoNewMessageTo(customerEmail);
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Добавляем нового заказчика с отправкой письма")
    @TestType.e2e
    void newCustomerEmail() {
        final var customerEmail = dataProvider.getValidEmailAddress();
        final var inn = "6670412201";
        mainPageProvider.adminLink(linkProvider.newCustomerLink());
        newCustomerPage
                .customerName(dataProvider.getFullName())
                .customerPhone(dataProvider.getPhoneNumber())
                .customerEmail(customerEmail)
                .customerINN(inn)
                .clickButtonNext()
                .chooseSalesManager(userData.salesManager());
        var shortName = newCustomerPage.getShortName();
        newCustomerPage.clickSaveButton();
        popUp.checkMessageText(popUpMessage.accountCreated());
        customersMainPage
                .searchCustomer(customerEmail)
                .checkShortName(shortName)
                .checkEmail(customerEmail);
        status.checkStatus(MAIL_CHECKED.getValue());
        emailSteps.getMessageBySubjectAndRecipient(emailMessage.confirmRegistrationCustomerFromAdmin(), customerEmail);
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Заключение индивидуального договора без уведомления заказчика")
    @TestType.e2e
    void signIndividualContractNoMail() {
        mainPageProvider.adminLink(linkProvider.customersLink());
        customersMainPage
                .searchCustomer(userData.emailCustomerIndividualNoEmailAdmin())
                .clickFirstCustomerFromList();
        customerProfilePage
                .clickOnMenu(ACTION.getValue())
                .chooseContract(INDIVIDUAL_CONTRACT)
                .clickOnButton(CHOOSE_TYPE_CONTRACT.getValue());
        popUp.checkMessageText(popUpMessage.contractRequest());
        status.checkStatus(SIGNING_CONTRACT.getValue());
        emailSteps.checkThatNoNewMessageTo(userData.emailCustomerIndividualNoEmailAdmin());
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Заключение индивидуального договора c уведомлением заказчика")
    @TestType.e2e
    void signIndividualContractWithMail() {
        mainPageProvider.adminLink(linkProvider.customersLink());
        customersMainPage
                .searchCustomer(userData.emailCustomerIndividualWithEmailAdmin())
                .clickFirstCustomerFromList();
        customerProfilePage
                .clickOnMenu(ACTION.getValue())
                .clickOnCheckBox()
                .chooseContract(INDIVIDUAL_CONTRACT)
                .clickOnButton(CHOOSE_TYPE_CONTRACT.getValue());
        popUp.checkMessageText(popUpMessage.contractRequest());
        status.checkStatus(SIGNING_CONTRACT.getValue());
        emailSteps.getMessageBySubjectAndRecipient(emailMessage.requestedCustomerIndividualContract(), userData.emailCustomerIndividualWithEmailAdmin());
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Заключение договора-оферты без уведомления заказчика")
    @TestType.e2e
    void requestOfferNoMail() {
        mainPageProvider.adminLink(linkProvider.customersLink());
        customersMainPage
                .searchCustomer(userData.emailCustomerOfferNoEmailAdmin())
                .clickFirstCustomerFromList();
        customerProfilePage
                .clickOnMenu(ACTION.getValue())
                .chooseContract(OFFER_CONTRACT)
                .clickOnButton(CHOOSE_TYPE_CONTRACT.getValue());
        popUp.checkMessageText(popUpMessage.contractRequest());
        status.checkStatus(SIGNING_CONTRACT.getValue());
        emailSteps.checkThatNoNewMessageTo(userData.emailCustomerOfferNoEmailAdmin());

    }

    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Заключаем договор-оферту c уведомлением заказчика")
    @TestType.e2e
    void requestOfferWithMail() {
        mainPageProvider.adminLink(linkProvider.customersLink());
        customersMainPage
                .searchCustomer(userData.emailCustomerOfferWithEmailAdmin())
                .clickFirstCustomerFromList();
        customerProfilePage
                .clickOnMenu(ACTION.getValue())
                .clickOnCheckBox()
                .chooseContract(OFFER_CONTRACT)
                .clickOnButton(CHOOSE_TYPE_CONTRACT.getValue());
        popUp.checkMessageText(popUpMessage.contractRequest());
        status.checkStatus(SIGNING_CONTRACT.getValue());
        emailSteps.getMessageBySubjectAndRecipient(emailMessage.requestedCustomerOfferContract(), userData.emailCustomerOfferWithEmailAdmin());
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Одобрение индивидуального договора без уведомления заказчика")
    @TestType.e2e
    void approvalIndividualContractNoMail() {
        mainPageProvider.adminLink(linkProvider.customersLink());
        customersMainPage
                .searchCustomer(userData.emailCustomerApproveIndividualNoEmailAdmin())
                .clickFirstCustomerFromList();
        customerProfilePage
                .clickOnMenu(ACTION.getValue())
                .clickOnButton(APPROVE_CONTRACT.getValue());
        popUp.checkMessageText(popUpMessage.contractApproved());
        status.checkStatus(SIGNED_CONTRACT.getValue());
        emailSteps.checkThatNoNewMessageTo(userData.emailCustomerApproveIndividualNoEmailAdmin());
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Одобрение договора-оферты с уведомлением заказчика")
    @TestType.e2e
    void approvalOfferMail() {
        mainPageProvider.adminLink(linkProvider.customersLink());
        customersMainPage
                .searchCustomer(userData.emailCustomerApproveOfferWithEmailAdmin())
                .clickFirstCustomerFromList();
        customerProfilePage
                .clickOnMenu(ACTION.getValue())
                .clickOnCheckBox()
                .clickOnButton(APPROVE_CONTRACT.getValue());
        popUp.checkMessageText(popUpMessage.contractApproved());
        status.checkStatus(SIGNED_CONTRACT.getValue());
        emailSteps.getMessageBySubjectAndRecipient(emailMessage.approvedOfferContract(), userData.emailCustomerApproveOfferWithEmailAdmin());
    }
}
