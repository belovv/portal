package adminTest.navigationTest;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import portal.admin.OrdersSubscriptionsMainPage;
import portal.admin.customer_page.CustomerProfilePage;
import portal.admin.order_subscription_page.new_subscriptions_page.NewSubscriptionPageOne;
import portal.admin.order_subscription_page.order_profile_page.OrderPage;
import portal.admin.order_subscription_page.subscription_profile_pages.SubscriptionProfilePage;
import portal.annotations.Admin;
import portal.annotations.TestType;
import portal.data.LinkProvider;
import portal.listener.BaseTestExtension;
import portal.providers.DataProvider;
import portal.providers.MainPageProvider;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;

@ExtendWith(BaseTestExtension.class)
public class AdminNavigationOrderSubscriptionTest {

    private final Map<String, String> data = new HashMap<>();
    OrdersSubscriptionsMainPage ordersSubscriptionsMainPage = new OrdersSubscriptionsMainPage();
    SubscriptionProfilePage subscriptionProfilePage = new SubscriptionProfilePage();
    OrderPage orderPage = new OrderPage();
    CustomerProfilePage customerProfilePage = new CustomerProfilePage();
    NewSubscriptionPageOne newSubscriptionPageOne = new NewSubscriptionPageOne();
    LinkProvider linkProvider = new DataProvider().linkProvider();
    MainPageProvider mainPageProvider = new MainPageProvider();

    @Admin.ordersSubscriptions
    @TestType.web
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Проверяем ссылку Заказы/Подписки->Заказы")
    void clickOrders() {
        mainPageProvider.adminLink(linkProvider.orderSubscriptionLink());
        ordersSubscriptionsMainPage.clickOn("Заказы");
        ordersSubscriptionsMainPage.checkPageTitle("Заказы");
    }

    @Admin.ordersSubscriptions
    @TestType.web
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Проверяем ссылку Заказы/Подписки->Заявки")
    void clickRequests() {
        mainPageProvider.adminLink(linkProvider.orderSubscriptionLink());
        ordersSubscriptionsMainPage.clickOn("Заявки");
        ordersSubscriptionsMainPage.checkPageTitle("Заявки");
    }

    @Admin.ordersSubscriptions.requests
    @TestType.web
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Проверяем ссылку Заявки->Подписки")
    void clickSubscriptions() {
        mainPageProvider.adminLink(linkProvider.orderSubscriptionRequestsLink());
        ordersSubscriptionsMainPage.clickOn("Подписки");
        ordersSubscriptionsMainPage.checkOrdersSubscriptionPageTitle();
    }

    @Admin.ordersSubscriptions.requests
    @TestType.web
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Проверяем ссылку Заказы/Подписки в строке пути страницы Заказы/Подписки>Заявки")
    void clickSubscriptionPath() {
        mainPageProvider.adminLink(linkProvider.orderSubscriptionRequestsLink());
        step("Кликаем на Заказы/Подписки в строке пути страницы Заказы/Подписки>Заявки", (step) -> {
            $(".bs-breadcrumbs_link-span").click();
        });
        ordersSubscriptionsMainPage.checkOrdersSubscriptionPageTitle();
    }

    @Admin.ordersSubscriptions.subscriptions
    @TestType.web
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Кликаем на номер заказа подписки")
    void clickOnOrderOfSubscriptionPage() {
        mainPageProvider.adminLink(linkProvider.orderSubscriptionLink());
        String orderID = ordersSubscriptionsMainPage.clickOnOrder();
        orderPage.checkOrderPageTitleByNumber(orderID);
    }

    @Admin.ordersSubscriptions.subscriptions
    @TestType.web
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Кликаем на номер заказчика на странице подписки")
    void clickCustomerOnSubscriptionPage() {
        mainPageProvider.adminLink(linkProvider.orderSubscriptionLink());
        String customerId = ordersSubscriptionsMainPage.clickOnCustomer();
        customerProfilePage.checkCustomerPageTitleById(customerId);

    }

    @Admin.ordersSubscriptions.subscriptions
    @TestType.web
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Кликаем на 1-ю подписку из списка на странице подписки")
    void clickSubscriptionFromListOfSubscriptionPages() {
        mainPageProvider.adminLink(linkProvider.orderSubscriptionLink());
        String subscriptionId = ordersSubscriptionsMainPage.clickOnFirstSubscription();
        subscriptionProfilePage.checkSubscriptionPageTitleById(subscriptionId);
    }


    @Admin.ordersSubscriptions.subscriptions
    @TestType.web
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Кликаем на 'Создать услугу для клиента' на странице подписки")
    void clickCreateNewSubscriptionOfSubscriptionPage() {
        mainPageProvider.adminLink(linkProvider.orderSubscriptionLink());
        step("Кликаем на Создать услугу для клиента", (step) -> {
            $x("//*[text()='Создать услугу для клиента']").click();
        });
        newSubscriptionPageOne.checkNewSubscriptionTitle();
    }

    @Admin.ordersSubscriptions.orders
    @TestType.web
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Кликаем на номер заказчика на странице заказы")
    void clickOnCustomerOfPageOrders() {
        mainPageProvider.adminLink(linkProvider.orderSubscriptionOrderLink());
        String ID = ordersSubscriptionsMainPage.clickOnCustomer();
        customerProfilePage.checkCustomerPageTitleById(ID);
    }

    @Admin.ordersSubscriptions.orders
    @TestType.web
    @DisplayName("Кликаем на 1-й заказ из списка, на странице заказы")
    @Severity(value = SeverityLevel.CRITICAL)
    void clickOnOrderFromListOrdersPage() {
        mainPageProvider.adminLink(linkProvider.orderSubscriptionOrderLink());
        String orderID = ordersSubscriptionsMainPage.clickOnOrder();
        orderPage.checkOrderPageTitleByNumber(orderID);
    }

    @Admin.ordersSubscriptions.requests
    @TestType.web
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Кликаем на номер подписки на странице заявки")
    void clickOnSubscriptionOfRequestsPage() {
        mainPageProvider.adminLink(linkProvider.orderSubscriptionRequestsLink());
        step("Кликаем на номер услуги на странице заявки и получаем номер подписки", (step) -> {
            data.put("idSub", $x("//*[contains(@href,'/subscriptions/list/')]").text().split("-")[1]);
            $x("//*[contains(@href,'/subscriptions/list/')]").click();
        });
        subscriptionProfilePage.checkSubscriptionPageTitleById(data.get("idSub"));
    }

    @Admin.ordersSubscriptions.requests
    @TestType.web
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Кликаем на номер заказчика на странице заявки")
    void clickOnCustomerOfRequestsPage() {
        mainPageProvider.adminLink(linkProvider.orderSubscriptionRequestsLink());
        String id = ordersSubscriptionsMainPage.clickOnCustomer();
        customerProfilePage.checkCustomerPageTitleById(id);
    }

    @Admin.ordersSubscriptions.requests
    @TestType.web
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Кликаем на номер заказа на странице заявки")
    void clickOnOrderOfRequestsPage() {
        mainPageProvider.adminLink(linkProvider.orderSubscriptionRequestsLink());
        String orderID = ordersSubscriptionsMainPage.clickOnOrder();
        orderPage.checkOrderPageTitleByNumber(orderID);
    }

}
