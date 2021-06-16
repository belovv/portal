package portal.data;


import org.aeonbits.owner.Config;

public interface LinkProvider extends Config {

    //******************AdminPortal*******************************
    // ссылка на главную страницу
    @DefaultValue("/dashboard")
    String adminMainPageLink();

    //Ссылка на заказы/подписки
    @DefaultValue("/link/list")
    String orderSubscriptionLink();

    //Ссылка на заказы/подписки->заказы
    @DefaultValue("/link/orders")
    String orderSubscriptionOrderLink();

    //Ссылка на заказы/подписки->заявки
    @DefaultValue("/link/requests")
    String orderSubscriptionRequestsLink();


    //Ссылка на Создать услугу для клиента
    @DefaultValue("/link/list/new")
    String newSubscriptionLink();

    //Ссылка на Добавить нового заказчика
    @DefaultValue("/index/new")
    String newCustomerLink();

    @DefaultValue("/index")
    String customersLink();

    // ссылка на заказы/подписки с фильтром по test2025@portal.cloud
    @DefaultValue("/link/list?sort=-order.order_id,-instance_id&filter=%7B\"search\":%5B\"" +
            "test2025%2540portal.cloud\"%5D,\"fields\":%7B\"state\":%5B\"new\",\"deploy\",\"active\",\"failed\"," +
            "\"disabled\",\"blocked\"%5D%7D%7D")
    String subscriptionEdit();

    // ссылка на заказы/подписки с фильтром по test2026@portal.cloud
    @DefaultValue("/link/list?sort=-order.order_id,-instance_id&filter=%7B\"search\":%5B\"" +
            "test2026%2540portal.cloud\"%5D,\"fields\":%7B\"state\":%5B\"new\",\"deploy\",\"active\",\"failed\"," +
            "\"disabled\",\"blocked\"%5D%7D%7D")
    String subscriptionChange();

    // ссылка на заказы/подписки с фильтром по test2035@portal.cloud
    @DefaultValue("/link/list?sort=-order.order_id,-instance_id&filter=%7B\"search\":%5B\"test2035\"%5D,\"fields\":%7B%7D%7D")
    String subscriptionChangeEmail();

    // ссылка на заказы/подписки с фильтром по test2125@portal.cloud
    @DefaultValue("/link/list?sort=-order.order_id,-instance_id&filter=%7B\"search\":%5B\"test2125\"%5D,\"fields\":%7B%7D%7D")
    String approveNewSubscription();

    // ссылка на заказы/подписки с фильтром по test2126@portal.cloud
    @DefaultValue("/link/list?sort=-order.order_id,-instance_id&filter=%7B\"search\":%5B\"test2126\"%5D,\"fields\":%7B%7D%7D")
    String approveActiveSubscription();

    // ссылка на заказы/подписки с фильтром по test2127@portal.cloud
    @DefaultValue("/link/list?sort=-order.order_id,-instance_id&filter=%7B\"search\":%5B\"test2127\"%5D,\"fields\":%7B%7D%7D")
    String approveNewTestSubscription();

    // ссылка на заказы/подписки с фильтром по test2128@portal.cloud
    @DefaultValue("/link/list?sort=-order.order_id,-instance_id&filter=%7B\"search\":%5B\"test2128\"%5D,\"fields\":%7B%7D%7D")
    String approveActiveTestSubscription();

    // ссылка на облачное хранилище в  списке подписок
    @DefaultValue("/link/list?filter=%7B\"search\":%5B%5D,\"fields\":%7B\"service_type_id\":%5B131%5D%7D%7D")
    String subscriptionCloudStorageLink();

    // ссылка на Microsoft в  списке подписок
    @DefaultValue("/link/list?filter=%7B\"search\":%5B%5D,\"fields\":%7B\"service_type_id\":%5B122%5D%7D%7D")
    String subscriptionMicrosoftLink();

    // ссылка на резервное копирование
    @DefaultValue("/link/list?filter=%7B\"search\":%5B%5D,\"fields\":%7B\"service_type_id\":%5B116%5D%7D%7D")
    String subscriptionBackupLink();

    // ссылка на доступ в интернет
    @DefaultValue("/link/list?filter=%7B\"search\":%5B%5D,\"fields\":%7B\"service_type_id\":%5B119%5D%7D%7D")
    String subscriptionInternetLink();

    // ссылка на доступ в интернет
    @DefaultValue("/link/list?filter=%7B\"search\":%5B%5D,\"fields\":%7B\"service_type_id\":%5B125%5D%7D%7D")
    String subscriptionOpenstackLink();

    // ссылка на доступ в интернет
    @DefaultValue("/link/list?filter=%7B\"search\":%5B%5D,\"fields\":%7B\"service_type_id\":%5B110%5D%7D%7D")
    String subscriptionVmwareProtectLink();

    //ссылка на активные тестовые подписки банка ВТБ
    @DefaultValue("/link/list?sort=-instance_id&filter=%7B\"search\":%5B\"Банк%20ВТБ\"%5D," +
            "\"fields\":%7B\"state\":%5B\"active\"%5D,\"is_trial\":%5Btcloude%5D%7D%7D")
    String subscriptionActiveTestLink();

    //********************Personal Cabinet*******************************
    @DefaultValue("/catalog")
    String catalogLinkLK();

    @DefaultValue("/myservices?filter=%7B%22search%22:%5B%5D,%22fields%22:%7B%22state%22:%22new%22%7D%7D")
    String myServiceNewSubscriptionsLinkLK();

    @DefaultValue("/myservices?filter=%7B\"search\":%5B%5D,\"fields\":%7B\"state\":\"active\"%7D%7D&page=1")
    String myServiceActiveSubscriptionLinkLK();

}
