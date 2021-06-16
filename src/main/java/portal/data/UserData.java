package portal.data;

import org.aeonbits.owner.Config;


public interface UserData extends Config {


    //**************Заказчик ЛК для проверки входа в Аминку********************
    @DefaultValue("test1022@portal.cloud")
    String emailCustomerLoginLK();

    @DefaultValue("0560205547")
    String innCustomerLoginLK();

    //**********заказчик ЛК для восстановления пароля***************
    @DefaultValue("test1036@portal.cloud")
    String emailCustomerRestorePasswordLK();

    @DefaultValue("3663100580")
    String innCustomerRestorePasswordLK();

    //**********заказчик Админки для заказа услуг***************
    @DefaultValue("СЕРВИС-ПРО")
    String customerNewSubscriptionAdmin();

    @DefaultValue("test2015@portal.cloud")
    String emailCustomerNewSubscriptionAdmin();

    @DefaultValue("6671270158")
    String innCustomerNewSubscriptionAdmin();

    @DefaultValue("individual")
    String contractTypeIndividual();

    @DefaultValue("offer")
    String contractTypeOffer();

    //**********заказчик Админки для редактирования услуг***************
    @DefaultValue("test2025@portal.cloud")
    String emailCustomerEditSubscriptionAdmin();

    @DefaultValue("7825403010")
    String innCustomerEditSubscriptionAdmin();

    //**********заказчик Админки для активации заявок новых услуг***************
    @DefaultValue("test2125@portal.cloud")
    String emailCustomerApproveNewSubscriptionAdmin();

    @DefaultValue("5003048144")
    String innCustomerApproveNewSubscriptionAdmin();

    //**********заказчик Админки для активации заявок активных услуг***************
    @DefaultValue("test2126@portal.cloud")
    String emailCustomerApproveSubscriptionAdmin();

    @DefaultValue("2723141776")
    String innCustomerApproveSubscriptionAdmin();

    //**********заказчик Админки для активации заявок новых тестовых услуг***************
    @DefaultValue("test2127@portal.cloud")
    String emailCustomerApproveNewTestSubscriptionAdmin();

    @DefaultValue("3115000482")
    String innCustomerApproveNewTestSubscriptionAdmin();

    //**********заказчик Админки для активации заявок активных тестовых услуг***************
    @DefaultValue("test2128@portal.cloud")
    String emailCustomerApproveTestSubscriptionAdmin();

    @DefaultValue("5036071318")
    String innCustomerApproveTestSubscriptionAdmin();

    //**********заказчик Админки для заявки на изменение***************
    @DefaultValue("test2026@portal.cloud")
    String emailCustomerChangeSubscriptionAdmin();

    @DefaultValue("5003019640")
    String innCustomerChangeSubscriptionAdmin();

    //**********заказчик Админки для проверки писем***************
    @DefaultValue("test2035@portal.cloud")
    String emailCustomerCheckEmail();

    @DefaultValue("7610071510")
    String innCustomerCheckEmail();

    @DefaultValue("ТПФ \"ДИ-МЕР")
    String customerCheckEmail();

    //**************Заказчики для заключения договоров**********
    @DefaultValue("test0393@portal.cloud")        // почта нового заказчика для запроса индивидуального договора без уведомления
    String emailCustomerIndividualNoEmailAdmin();
    @DefaultValue("7722096223")                  // Инн нового заказчика для индивидуального договора без уведомления
    String innCustomerIndividualNoEmailAdmin();

    @DefaultValue("test0394@portal.cloud")        // почта нового заказчика для запроса индивидуального договора с уведомлением заказчика
    String emailCustomerIndividualWithEmailAdmin();
    @DefaultValue("5027167648")                  // Инн нового заказчика для индивидуального договора c уведомлением
    String innCustomerIndividualWithEmailAdmin();

    @DefaultValue("test0373@portal.cloud")        // почта нового заказчика для запроса оферты без уведомления
    String emailCustomerOfferNoEmailAdmin();
    @DefaultValue("8904078559")                  // Инн нового заказчика для оферты без уведомления
    String innCustomerOfferNoEmailAdmin();

    @DefaultValue("test0374@portal.cloud")        // почта нового заказчика для запроса оферты с уведомлением заказчика
    String emailCustomerOfferWithEmailAdmin();
    @DefaultValue("5024121821")                  // Инн нового заказчика для оферты c уведомлением
    String innCustomerOfferWithEmailAdmin();

    //*********Одобрение договоров****************************

    @DefaultValue("test0353@portal.cloud")        // почта нового заказчика для одобрения индивидуального договора без уведомления
    String emailCustomerApproveIndividualNoEmailAdmin();
    @DefaultValue("1102077163")                  // Инн нового заказчика для одобрения индивидуального договора без уведомления
    String innCustomerApproveIndividualNoEmailAdmin();

    @DefaultValue("test0352@portal.cloud")        // почта нового заказчика для одобрения оферты c уведомлением
    String emailCustomerApproveOfferWithEmailAdmin();
    @DefaultValue("7733721373")                  // Инн нового заказчика для одобрения оферты с уведомлением
    String innCustomerApproveOfferWithEmailAdmin();

    //************Заказчики для профиля заказчика****************************
    @DefaultValue("test0363@portal.cloud")       // почта нового заказчика для договора без менеджеров
    String emailCustomerContractNoManagersAdmin();
    @DefaultValue("1655289152")                 // Инн нового заказчика для запроса договора без менеджеров
    String innCustomerContractNoManagersAdmin();

    @DefaultValue("test0362@portal.cloud")       // почта нового заказчика для проверки профиля
    String emailCustomerProfile();
    @DefaultValue("7724341985")                 // Инн нового заказчика для проверки профиля
    String innCustomerProfile();

    //******************менеджеры*******************
    @DefaultValue("Unreal_Sales_Manager")
    String salesManager();

    @DefaultValue("unreal_sales_manager@portal.cloud")
    String emailSalesManager();

    @DefaultValue("Unreal_Service_Manager")
    String unrealServiceManager();

    @DefaultValue("unreal_service_manager@portal.cloud")
    String emailServiceManager();

    @DefaultValue("Email_Sales_Manager")
    String salesManagerForEmail();

    @DefaultValue("email_sales_manager@portal.cloud")
    String emailSalesManagerForEmail();

    @DefaultValue("Email_Service_Manager")
    String unrealServiceManagerForEmail();

    @DefaultValue("email_service_manager@portal.cloud")
    String emailServiceManagerForEmail();

    @DefaultValue("admin")
    String userRoleAdmin();

    //***************Default AdminUser*************

    @DefaultValue("Unreal_Default_Admin")
    String defaultAdmin();

    @DefaultValue("unreal_default_admin@portal.cloud")
    String emailDefaultAdmin();

    @DefaultValue("R0#ht_h4nd")
    String defaultAdminPassword();

    //*************разное**************
    @DefaultValue("Cloud")
    String disableNotificationTrue();

    @DefaultValue("completed")
    String orderStatusCompleted();

}
