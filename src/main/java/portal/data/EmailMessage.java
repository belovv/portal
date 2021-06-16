package portal.data;

import org.aeonbits.owner.Config;

public interface EmailMessage extends Config {

    @DefaultValue("Заявка на услугу, индив. договор")
    String newSubscriptionEmailSubject();

    @DefaultValue("Заявка на изменение подписки по договору")
    String changeRequestEmailSubject();

    //*****************Договор********************************
    @DefaultValue("TSCloud dev: Подтверждение Регистрации на портале Техносерв Cloud")
    String confirmRegistrationCustomerFromAdmin();

    @DefaultValue("TSCloud dev: Запрошено оформление индивидуального договора")
    String requestedCustomerIndividualContract();

    @DefaultValue("TSCloud dev: Запрошено оформление договора оферты")
    String requestedCustomerOfferContract();

    @DefaultValue("Заявка на договор оферты была одобрена")
    String approvedOfferContract();

    //*************восстановление пароля*********************

    @DefaultValue("TSCloud dev: Ссылка для сброса пароля")
    String resetPassword();
}
