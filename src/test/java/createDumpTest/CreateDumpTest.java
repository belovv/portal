package createDumpTest;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import portal.annotations.TestType;
import portal.data.ApiSubscriptionData;
import portal.data.UserData;
import portal.data.enumdata.SubscriptionIdName;
import portal.providers.DataProvider;
import portal.retrofit.DumpRetrofitSteps;
import portal.retrofit.RetrofitErrorInterceptor;
import portal.utils.DateTime;

import static io.qameta.allure.Allure.step;
import static portal.data.enumdata.SubscriptionIdName.IX;
import static portal.data.enumdata.TypeUser.*;
import static portal.listener.AttachmentsHelper.attachAsText;

@TestType.dump
public class CreateDumpTest {

    private final DumpRetrofitSteps dumpRetrofitSteps = new DumpRetrofitSteps();
    private final ApiSubscriptionData apiSubscriptionData = new ApiSubscriptionData();
    private final UserData userData = new DataProvider().userData();
    private final String getYesterdayDate = new DateTime().getYesterdayDate();
    private static String salesManagerId = "0";
    private static String serviceManagerId = "0";
    private static String emailSalesManagerId = "0";
    private static String emailServiceManagerId = "0";
    private final ListAppender<ILoggingEvent> listAppender = new ListAppender<>();

    @BeforeEach
    public void start() {
        Logger logger = (Logger) LoggerFactory.getLogger(RetrofitErrorInterceptor.class);
        // create and start a ListAppender
        listAppender.start();
        // add the appender to the logger
        logger.addAppender(listAppender);
    }

    @Test
    public void createCustomers() {

        final String tokenAdmin = dumpRetrofitSteps.getTokenAdmin(USER_ADMIN.getEmail(), USER_ADMIN.getPassword());

        step("Создаем продакт и сервис менеджеров", (step) -> {
            salesManagerId = dumpRetrofitSteps.createManagerAdmin(
                    tokenAdmin, userData.salesManager(),
                    userData.emailSalesManager(),
                    userData.userRoleAdmin());
            emailSalesManagerId = dumpRetrofitSteps.createManagerAdmin(
                    tokenAdmin, userData.salesManagerForEmail(),
                    userData.emailSalesManagerForEmail(),
                    userData.userRoleAdmin());
            serviceManagerId = dumpRetrofitSteps.createManagerAdmin(
                    tokenAdmin, userData.unrealServiceManager(),
                    userData.emailServiceManager(),
                    userData.userRoleAdmin());
            emailServiceManagerId = dumpRetrofitSteps.createManagerAdmin(
                    tokenAdmin, userData.unrealServiceManagerForEmail(),
                    userData.emailServiceManagerForEmail(),
                    userData.userRoleAdmin());
        });

        step("Создаем юзера для проверки входа в админку", (step) -> {
            dumpRetrofitSteps.createManagerAdmin(
                    tokenAdmin, userData.defaultAdmin(),
                    userData.emailDefaultAdmin(),
                    userData.userRoleAdmin());
        });

        step("создаем заказчика для заказа услуг из админки", (step) -> {
            String customerNewSubAdminId = dumpRetrofitSteps.createCustomerAdmin(
                    tokenAdmin, userData.emailCustomerNewSubscriptionAdmin(),
                    userData.innCustomerNewSubscriptionAdmin());
            dumpRetrofitSteps.setManagersToCustomer(tokenAdmin, customerNewSubAdminId, salesManagerId, serviceManagerId);
            dumpRetrofitSteps.requestContract(
                    tokenAdmin, customerNewSubAdminId,
                    userData.disableNotificationTrue(),
                    userData.contractTypeIndividual());
            dumpRetrofitSteps.approveContract(tokenAdmin, customerNewSubAdminId, userData.disableNotificationTrue());
        });

        step("создаем заказчиков для запроса договоров и присваиваем им менеджеров", (step) -> {
            var customerAdminIndividualNoEmailId = dumpRetrofitSteps.createCustomerAdmin(
                    tokenAdmin,
                    userData.emailCustomerIndividualNoEmailAdmin(),
                    userData.innCustomerIndividualNoEmailAdmin());
            dumpRetrofitSteps.setManagersToCustomer(
                    tokenAdmin, customerAdminIndividualNoEmailId,
                    salesManagerId,
                    serviceManagerId);
            var customerAdminIndividualEmailId = dumpRetrofitSteps.createCustomerAdmin(
                    tokenAdmin,
                    userData.emailCustomerIndividualWithEmailAdmin(),
                    userData.innCustomerIndividualWithEmailAdmin());
            dumpRetrofitSteps.setManagersToCustomer(
                    tokenAdmin,
                    customerAdminIndividualEmailId,
                    salesManagerId, serviceManagerId);
            var customerAdminOfferNoEmailId = dumpRetrofitSteps.createCustomerAdmin(
                    tokenAdmin,
                    userData.emailCustomerOfferNoEmailAdmin(),
                    userData.innCustomerOfferNoEmailAdmin());
            dumpRetrofitSteps.setManagersToCustomer(
                    tokenAdmin,
                    customerAdminOfferNoEmailId,
                    salesManagerId,
                    serviceManagerId);
            var customerAdminOfferEmailId = dumpRetrofitSteps.createCustomerAdmin(
                    tokenAdmin,
                    userData.emailCustomerOfferWithEmailAdmin(),
                    userData.innCustomerOfferWithEmailAdmin());
            dumpRetrofitSteps.setManagersToCustomer(tokenAdmin, customerAdminOfferEmailId, salesManagerId, serviceManagerId);
        });

        step("создаем заказчиков для одобрение договоров из админки", (step) -> {
            var customerApproveIndividualAdminId = dumpRetrofitSteps.createCustomerAdmin(
                    tokenAdmin,
                    userData.emailCustomerApproveIndividualNoEmailAdmin(),
                    userData.innCustomerApproveIndividualNoEmailAdmin());
            dumpRetrofitSteps.setManagersToCustomer(
                    tokenAdmin, customerApproveIndividualAdminId,
                    salesManagerId,
                    serviceManagerId);
            dumpRetrofitSteps.requestContract(
                    tokenAdmin,
                    customerApproveIndividualAdminId,
                    userData.disableNotificationTrue(),
                    userData.contractTypeIndividual());
            var customerApproveOfferAdminId = dumpRetrofitSteps.createCustomerAdmin(
                    tokenAdmin,
                    userData.emailCustomerApproveOfferWithEmailAdmin(),
                    userData.innCustomerApproveOfferWithEmailAdmin());
            dumpRetrofitSteps.setManagersToCustomer(
                    tokenAdmin,
                    customerApproveOfferAdminId,
                    salesManagerId, serviceManagerId);
            dumpRetrofitSteps.requestContract(
                    tokenAdmin,
                    customerApproveOfferAdminId,
                    userData.disableNotificationTrue(),
                    userData.contractTypeOffer());
        });

        step("Создаем заказчика для одобрения договора без присвоенных менеджеров", (step) -> {
            dumpRetrofitSteps.createCustomerAdmin(
                    tokenAdmin,
                    userData.emailCustomerContractNoManagersAdmin(),
                    userData.innCustomerContractNoManagersAdmin());
        });

        step("Создаем заказчика для проверки профиля", (step) -> {
            dumpRetrofitSteps.createCustomerAdmin(
                    tokenAdmin,
                    userData.emailCustomerProfile(),
                    userData.innCustomerProfile());
        });

        step("Создаем заказчика для заказа услуг в ЛК", (step) -> {
            var customerNewSubscriptionLkId = dumpRetrofitSteps.createCustomerAdmin(
                    tokenAdmin,
                    CUSTOMER_NEW_SUBSCRIPTION.getEmail(),
                    CUSTOMER_NEW_SUBSCRIPTION.getInn());
            dumpRetrofitSteps.setManagersToCustomer(
                    tokenAdmin,
                    customerNewSubscriptionLkId,
                    salesManagerId,
                    serviceManagerId);
            dumpRetrofitSteps.requestContract(
                    tokenAdmin,
                    customerNewSubscriptionLkId,
                    userData.disableNotificationTrue(),
                    userData.contractTypeIndividual());
            dumpRetrofitSteps.approveContract(tokenAdmin, customerNewSubscriptionLkId, userData.disableNotificationTrue());
        });

        step("Создаем заказчика для проверки входа в ЛК", (step) -> {
            var customerLoginLkId = dumpRetrofitSteps.createCustomerAdmin(
                    tokenAdmin,
                    userData.emailCustomerLoginLK(),
                    userData.innCustomerLoginLK());
            dumpRetrofitSteps.setManagersToCustomer(tokenAdmin, customerLoginLkId, salesManagerId, serviceManagerId);
            dumpRetrofitSteps.requestContract(
                    tokenAdmin,
                    customerLoginLkId,
                    userData.disableNotificationTrue(),
                    userData.contractTypeIndividual());
            dumpRetrofitSteps.approveContract(tokenAdmin, customerLoginLkId, userData.disableNotificationTrue());
        });

        step("Создаем заказчика в ЛК", (step) -> {
            var customerLkId = dumpRetrofitSteps.createCustomerAdmin(
                    tokenAdmin,
                    NEW_CUSTOMER.getEmail(),
                    NEW_CUSTOMER.getInn());
            dumpRetrofitSteps.setManagersToCustomer(
                    tokenAdmin,
                    customerLkId,
                    salesManagerId, serviceManagerId);
        });

        step("Создаем заказчика c неодобренным индивидуальным договором в ЛК", (step) -> {
            var customerLkId = dumpRetrofitSteps.createCustomerAdmin(
                    tokenAdmin,
                    CUSTOMER_INDIVIDUAL.getEmail(),
                    CUSTOMER_INDIVIDUAL.getInn());
            dumpRetrofitSteps.setManagersToCustomer(
                    tokenAdmin,
                    customerLkId,
                    salesManagerId, serviceManagerId);
            dumpRetrofitSteps.requestContract(
                    tokenAdmin,
                    customerLkId,
                    userData.disableNotificationTrue(),
                    userData.contractTypeIndividual());
        });

        step("Создаем заказчика c неодобренным договором оферты в ЛК", (step) -> {
            var customerLkId = dumpRetrofitSteps.createCustomerAdmin(
                    tokenAdmin,
                    CUSTOMER_OFFER.getEmail(),
                    CUSTOMER_OFFER.getInn());
            dumpRetrofitSteps.setManagersToCustomer(
                    tokenAdmin,
                    customerLkId,
                    salesManagerId, serviceManagerId);
            dumpRetrofitSteps.requestContract(
                    tokenAdmin,
                    customerLkId,
                    userData.disableNotificationTrue(),
                    userData.contractTypeOffer());
        });

        step("Создаем заказчика и подписки для проверки названий подписок в ЛК", (step) -> {
            var customerCheckNameSubscriptionLkId = dumpRetrofitSteps.createCustomerAdmin(
                    tokenAdmin,
                    CUSTOMER_CHECK_NAME_SUBSCRIPTION.getEmail(),
                    CUSTOMER_CHECK_NAME_SUBSCRIPTION.getInn());
            dumpRetrofitSteps.setManagersToCustomer(
                    tokenAdmin,
                    customerCheckNameSubscriptionLkId,
                    salesManagerId,
                    serviceManagerId);
            dumpRetrofitSteps.requestContract(
                    tokenAdmin,
                    customerCheckNameSubscriptionLkId,
                    userData.disableNotificationTrue(),
                    userData.contractTypeIndividual());
            dumpRetrofitSteps.approveContract(
                    tokenAdmin,
                    customerCheckNameSubscriptionLkId,
                    userData.disableNotificationTrue());
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmwareIX(),
                    customerCheckNameSubscriptionLkId,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmware3GHz(),
                    customerCheckNameSubscriptionLkId,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmwareProtected(),
                    customerCheckNameSubscriptionLkId,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmwareClosed(),
                    customerCheckNameSubscriptionLkId,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.openstack(),
                    customerCheckNameSubscriptionLkId,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.cloudStorage(),
                    customerCheckNameSubscriptionLkId,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.internet(),
                    customerCheckNameSubscriptionLkId,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.backup(),
                    customerCheckNameSubscriptionLkId,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.microsoft(),
                    customerCheckNameSubscriptionLkId,
                    false,
                    getYesterdayDate);
        });

        step("Создаем заказчика для редактирования услуг из админки и создаем для него подписки", (step) -> {
            var idCustomerEditSubAdmin = dumpRetrofitSteps.createCustomerAdmin(
                    tokenAdmin,
                    userData.emailCustomerEditSubscriptionAdmin(),
                    userData.innCustomerEditSubscriptionAdmin());
            dumpRetrofitSteps.setManagersToCustomer(tokenAdmin, idCustomerEditSubAdmin, salesManagerId, serviceManagerId);
            dumpRetrofitSteps.requestContract(tokenAdmin,
                    idCustomerEditSubAdmin,
                    userData.disableNotificationTrue(),
                    userData.contractTypeIndividual());
            dumpRetrofitSteps.approveContract(
                    tokenAdmin,
                    idCustomerEditSubAdmin,
                    userData.disableNotificationTrue());
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmwareIX(),
                    idCustomerEditSubAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmware3GHz(),
                    idCustomerEditSubAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmwareProtected(),
                    idCustomerEditSubAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmwareClosed(),
                    idCustomerEditSubAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.openstack(),
                    idCustomerEditSubAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.internet(),
                    idCustomerEditSubAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.backup(),
                    idCustomerEditSubAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.cloudStorage(),
                    idCustomerEditSubAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.microsoft(),
                    idCustomerEditSubAdmin,
                    false,
                    getYesterdayDate);
        });

        step("Создаем заказчика для активации заявок новых услуг из админки и создаем для него подписки", (step) -> {
            var idCustomerApproveNewSubAdmin = dumpRetrofitSteps.createCustomerAdmin(
                    tokenAdmin,
                    userData.emailCustomerApproveNewSubscriptionAdmin(),
                    userData.innCustomerApproveNewSubscriptionAdmin());
            dumpRetrofitSteps.setManagersToCustomer(tokenAdmin, idCustomerApproveNewSubAdmin, salesManagerId, serviceManagerId);
            dumpRetrofitSteps.requestContract(tokenAdmin,
                    idCustomerApproveNewSubAdmin,
                    userData.disableNotificationTrue(),
                    userData.contractTypeIndividual());
            dumpRetrofitSteps.approveContract(
                    tokenAdmin,
                    idCustomerApproveNewSubAdmin,
                    userData.disableNotificationTrue());
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmwareIX(),
                    idCustomerApproveNewSubAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmwareProtected(),
                    idCustomerApproveNewSubAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.openstack(),
                    idCustomerApproveNewSubAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.backup(),
                    idCustomerApproveNewSubAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.microsoft(),
                    idCustomerApproveNewSubAdmin,
                    false,
                    getYesterdayDate);
        });

        step("Создаем заказчика для активации заявок активных услуг из админки и создаем для него подписки", (step) -> {
            var idCustomerApproveSubAdmin = dumpRetrofitSteps.createCustomerAdmin(
                    tokenAdmin,
                    userData.emailCustomerApproveSubscriptionAdmin(),
                    userData.innCustomerApproveSubscriptionAdmin());
            dumpRetrofitSteps.setManagersToCustomer(
                    tokenAdmin,
                    idCustomerApproveSubAdmin,
                    salesManagerId,
                    serviceManagerId);
            dumpRetrofitSteps.requestContract(
                    tokenAdmin,
                    idCustomerApproveSubAdmin,
                    userData.disableNotificationTrue(),
                    userData.contractTypeIndividual());
            dumpRetrofitSteps.approveContract(
                    tokenAdmin,
                    idCustomerApproveSubAdmin,
                    userData.disableNotificationTrue());
            var requestIdVmwareIX = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmwareIX(),
                    idCustomerApproveSubAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdVmwareIX,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdVmwareProtected = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmwareProtected(),
                    idCustomerApproveSubAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdVmwareProtected,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdOpenstack = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.openstack(),
                    idCustomerApproveSubAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdOpenstack,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdInternet = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.backup(),
                    idCustomerApproveSubAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdInternet,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdMicrosoft = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.microsoft(),
                    idCustomerApproveSubAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdMicrosoft,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
        });

        step("Создаем заказчика для активации заявок новых тестовых услуг из админки и создаем для него подписки", (step) -> {
            var idCustomerApproveNewSubAdmin = dumpRetrofitSteps.createCustomerAdmin(
                    tokenAdmin,
                    userData.emailCustomerApproveNewTestSubscriptionAdmin(),
                    userData.innCustomerApproveNewTestSubscriptionAdmin());
            dumpRetrofitSteps.setManagersToCustomer(tokenAdmin, idCustomerApproveNewSubAdmin, salesManagerId, serviceManagerId);
            dumpRetrofitSteps.requestContract(tokenAdmin,
                    idCustomerApproveNewSubAdmin,
                    userData.disableNotificationTrue(),
                    userData.contractTypeIndividual());
            dumpRetrofitSteps.approveContract(
                    tokenAdmin,
                    idCustomerApproveNewSubAdmin,
                    userData.disableNotificationTrue());
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmwareIX(),
                    idCustomerApproveNewSubAdmin,
                    true,
                    getYesterdayDate);
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmwareProtected(),
                    idCustomerApproveNewSubAdmin,
                    true,
                    "null");
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.openstack(),
                    idCustomerApproveNewSubAdmin,
                    true,
                    "null");
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.backup(),
                    idCustomerApproveNewSubAdmin,
                    true,
                    "null");
            dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.microsoft(),
                    idCustomerApproveNewSubAdmin,
                    true,
                    "null");
        });

        step("Создаем заказчика для активации заявок активных тестовых услуг из админки и создаем для него подписки", (step) -> {
            var idCustomerApproveSubAdmin = dumpRetrofitSteps.createCustomerAdmin(
                    tokenAdmin,
                    userData.emailCustomerApproveTestSubscriptionAdmin(),
                    userData.innCustomerApproveTestSubscriptionAdmin());
            dumpRetrofitSteps.setManagersToCustomer(
                    tokenAdmin,
                    idCustomerApproveSubAdmin,
                    salesManagerId,
                    serviceManagerId);
            dumpRetrofitSteps.requestContract(
                    tokenAdmin,
                    idCustomerApproveSubAdmin,
                    userData.disableNotificationTrue(),
                    userData.contractTypeIndividual());
            dumpRetrofitSteps.approveContract(
                    tokenAdmin,
                    idCustomerApproveSubAdmin,
                    userData.disableNotificationTrue());
            var requestIdVmwareIX = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
//                    apiSubscriptionData.getDate(IX),
                    apiSubscriptionData.vmwareIX(),
                    idCustomerApproveSubAdmin,
                    true,
                    "null");
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdVmwareIX,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdVmwareProtected = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmwareProtected(),
                    idCustomerApproveSubAdmin,
                    true,
                    "null");
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdVmwareProtected,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdOpenstack = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.openstack(),
                    idCustomerApproveSubAdmin,
                    true,
                    "null");
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdOpenstack,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdInternet = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.backup(),
                    idCustomerApproveSubAdmin,
                    true,
                    "null");
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdInternet,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdMicrosoft = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.microsoft(),
                    idCustomerApproveSubAdmin,
                    true,
                    "null");
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdMicrosoft,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
        });

        step("Создаем заказчика для проверки писем", (step) -> {
            var idCustomerEmailAdmin = dumpRetrofitSteps.createCustomerAdmin(
                    tokenAdmin,
                    userData.emailCustomerCheckEmail(),
                    userData.innCustomerCheckEmail());
            dumpRetrofitSteps.setManagersToCustomer(tokenAdmin, idCustomerEmailAdmin, emailSalesManagerId, emailServiceManagerId);
            dumpRetrofitSteps.requestContract(tokenAdmin,
                    idCustomerEmailAdmin,
                    userData.disableNotificationTrue(),
                    userData.contractTypeIndividual());
            dumpRetrofitSteps.approveContract(
                    tokenAdmin,
                    idCustomerEmailAdmin,
                    userData.disableNotificationTrue());
            var requestIdVmwareIX = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmwareIX(),
                    idCustomerEmailAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdVmwareIX,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdVmware3GHz = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmware3GHz(),
                    idCustomerEmailAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdVmware3GHz,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdVmwareProtected = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmwareProtected(),
                    idCustomerEmailAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdVmwareProtected,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdVmwareClosed = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmwareClosed(),
                    idCustomerEmailAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdVmwareClosed,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdOpenstack = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.openstack(),
                    idCustomerEmailAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdOpenstack,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
        });

        step("Создаем заказчика для заявки на изменение из админки и создаем для него подписки", (step) -> {
            var idCustomerChangeSubAdmin = dumpRetrofitSteps.createCustomerAdmin(
                    tokenAdmin,
                    userData.emailCustomerChangeSubscriptionAdmin(),
                    userData.innCustomerChangeSubscriptionAdmin());
            dumpRetrofitSteps.setManagersToCustomer(
                    tokenAdmin,
                    idCustomerChangeSubAdmin,
                    salesManagerId,
                    serviceManagerId);
            dumpRetrofitSteps.requestContract(
                    tokenAdmin,
                    idCustomerChangeSubAdmin,
                    userData.disableNotificationTrue(),
                    userData.contractTypeIndividual());
            dumpRetrofitSteps.approveContract(
                    tokenAdmin,
                    idCustomerChangeSubAdmin,
                    userData.disableNotificationTrue());
            var requestIdVmwareIX = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmwareIX(),
                    idCustomerChangeSubAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdVmwareIX,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdVmware3GHz = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmware3GHz(),
                    idCustomerChangeSubAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdVmware3GHz,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdVmwareProtected = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmwareProtected(),
                    idCustomerChangeSubAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdVmwareProtected,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdVmwareClosed = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmwareClosed(),
                    idCustomerChangeSubAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdVmwareClosed,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdOpenstack = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.openstack(),
                    idCustomerChangeSubAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdOpenstack,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdBackup = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.internet(),
                    idCustomerChangeSubAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdBackup,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdInternet = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.backup(),
                    idCustomerChangeSubAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdInternet,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdCloud = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.cloudStorage(),
                    idCustomerChangeSubAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdCloud,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdMicrosoft = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.microsoft(),
                    idCustomerChangeSubAdmin,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdMicrosoft,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
        });

        step("Создаем заказчика для заявки на изменение в ЛК и создаем для него подписки", (step) -> {
            var idCustomerChangeSubLK = dumpRetrofitSteps.createCustomerAdmin(
                    tokenAdmin,
                    CUSTOMER_CHANGE_REQUEST.getEmail(),
                    CUSTOMER_CHANGE_REQUEST.getInn());
            dumpRetrofitSteps.setManagersToCustomer(
                    tokenAdmin,
                    idCustomerChangeSubLK,
                    salesManagerId,
                    serviceManagerId);
            dumpRetrofitSteps.requestContract(
                    tokenAdmin,
                    idCustomerChangeSubLK,
                    userData.disableNotificationTrue(),
                    userData.contractTypeIndividual());
            dumpRetrofitSteps.approveContract(
                    tokenAdmin,
                    idCustomerChangeSubLK,
                    userData.disableNotificationTrue());
            var requestIdVmwareIX = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmwareIX(),
                    idCustomerChangeSubLK,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdVmwareIX,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdVmware3GHz = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmware3GHz(),
                    idCustomerChangeSubLK,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdVmware3GHz,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdVmwareProtected = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmwareProtected(),
                    idCustomerChangeSubLK,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdVmwareProtected,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdVmwareClosed = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.vmwareClosed(),
                    idCustomerChangeSubLK,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdVmwareClosed,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdOpenstack = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.openstack(),
                    idCustomerChangeSubLK,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdOpenstack,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdBackup = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.internet(),
                    idCustomerChangeSubLK,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdBackup,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdInternet = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.backup(),
                    idCustomerChangeSubLK,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdInternet,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdCloud = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.cloudStorage(),
                    idCustomerChangeSubLK,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdCloud,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());
            var requestIdMicrosoft = dumpRetrofitSteps.createSubscription(
                    tokenAdmin,
                    apiSubscriptionData.microsoft(),
                    idCustomerChangeSubLK,
                    false,
                    getYesterdayDate);
            dumpRetrofitSteps.changeOrderStatus(
                    tokenAdmin,
                    requestIdMicrosoft,
                    userData.disableNotificationTrue(),
                    userData.orderStatusCompleted());


        });

        step("Создаем заказчика для восстановления пароля", (step) -> {
            dumpRetrofitSteps.createCustomerAdmin(
                    tokenAdmin,
                    userData.emailCustomerRestorePasswordLK(),
                    userData.innCustomerRestorePasswordLK());

        });
    }

    @AfterEach
    public void finish(){
        if(listAppender.list.size()>0){
            attachAsText("Logger", listAppender.list.get(0).getMessage());
        }
    }
}
