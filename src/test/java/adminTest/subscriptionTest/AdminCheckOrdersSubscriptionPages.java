package adminTest.subscriptionTest;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import portal.admin.OrdersSubscriptionsMainPage;
import portal.annotations.Admin;
import portal.annotations.TestType;
import portal.data.LinkProvider;
import portal.listener.BaseTestExtension;
import portal.providers.DataProvider;
import portal.providers.MainPageProvider;

@ExtendWith(BaseTestExtension.class)
public class AdminCheckOrdersSubscriptionPages {

    private final OrdersSubscriptionsMainPage ordersSubscriptionsMainPage = new OrdersSubscriptionsMainPage();
    private final LinkProvider linkProvider = new DataProvider().linkProvider();
    private final MainPageProvider mainPageProvider = new MainPageProvider();

    @Admin.ordersSubscriptions.subscriptions
    @TestType.test
    @TestType.web
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Проверяем что на странице 100 подписок")
    void checkValueSubscriptionIs100() {
        mainPageProvider.adminLink(linkProvider.orderSubscriptionLink());
        ordersSubscriptionsMainPage.checkValueStringsIs(100);
    }

    @Admin.ordersSubscriptions.subscriptions
    @TestType.web
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Проверяем количество (от 1 до 100) подписок на последней странице списка подписок")
    void clickLastPageOfSubscription() {
        mainPageProvider.adminLink(linkProvider.orderSubscriptionLink());
        ordersSubscriptionsMainPage.clickLastPage();
        ordersSubscriptionsMainPage.checkRangeValueStrings(1, 100);
    }

    @Admin.ordersSubscriptions.orders
    @TestType.web
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Проверяем что на странице 100 заказов")
    void checkValueOrdersIs100() {
        mainPageProvider.adminLink(linkProvider.orderSubscriptionOrderLink());
        ordersSubscriptionsMainPage.checkValueStringsIs(100);
    }

    @Admin.ordersSubscriptions.orders
    @TestType.web
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Проверяем количество (от 1 до 100) заказов на последней странице списка заказов")
    void clickLastPageOfOrders() {
        mainPageProvider.adminLink(linkProvider.orderSubscriptionOrderLink());
        ordersSubscriptionsMainPage.clickLastPage();
        ordersSubscriptionsMainPage.checkRangeValueStrings(1, 100);
    }

    @Admin.ordersSubscriptions.requests
    @TestType.web
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Проверяем что на странице 100 заявок")
    void checkValueRequestsIs100() {
        mainPageProvider.adminLink(linkProvider.orderSubscriptionRequestsLink());
        ordersSubscriptionsMainPage.checkValueStringsIs(100);
    }

    @Admin.ordersSubscriptions.requests
    @TestType.web
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Проверяем количество (от 1 до 100) заявок на последней странице списка заявок")
    void clickLastPageOfRequests() {
        mainPageProvider.adminLink(linkProvider.orderSubscriptionRequestsLink());
        ordersSubscriptionsMainPage.clickLastPage();
        ordersSubscriptionsMainPage.checkRangeValueStrings(1, 100);
    }
}
