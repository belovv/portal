package portal.data;


import org.aeonbits.owner.Config;

public interface PopUpMessage extends Config {
    @DefaultValue("Заявка была успешно создана")
    String orderCreate();

    @DefaultValue("Заявка была успешно изменена")
    String orderChange();

    @DefaultValue("Статус изменен")
    String statusChanged();

    @DefaultValue("Услуга переведена в коммерческий режим")
    String subscriptionToCommercial();

    @DefaultValue("Дата изменена")
    String dataChanged();

    @DefaultValue("Вы перевели заявку в статус \"Выполнена\", " +
            "но забыли заполнить ORG ID и VDC ID на вкладке \"Установка\"")
    String requestChanged();

    @DefaultValue("Сервис менеджер должен быть заполнен на вкладке Заказчик->Информация")
    String setServiceManager();

    @DefaultValue("Аккаунт заказчика был успешно создан")
    String accountCreated();

    @DefaultValue("Пользователь успешно изменен")
    String customerChanged();

    @DefaultValue("Договор запрошен")
    String contractRequest();

    @DefaultValue("Договор одобрен")
    String contractApproved();

    @DefaultValue("Превышен лимит для заказчиков без договора")
    String limitMessage();
}
