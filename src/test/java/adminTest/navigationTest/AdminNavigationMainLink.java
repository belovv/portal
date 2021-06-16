package adminTest.navigationTest;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import portal.admin.AdminMainPage;
import portal.admin.CustomersMainPage;
import portal.admin.OrdersSubscriptionsMainPage;
import portal.listener.BaseTestExtension;
import portal.providers.DataProvider;
import portal.data.LinkProvider;
import portal.annotations.Admin;
import portal.annotations.TestType;
import portal.providers.MainPageProvider;

@Admin
@ExtendWith(BaseTestExtension.class)
public class AdminNavigationMainLink {

    AdminMainPage adminMainPage = new AdminMainPage();
    LinkProvider linkProvider = new DataProvider().linkProvider();
    MainPageProvider mainPageProvider = new MainPageProvider();

    @TestType.web
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Проверяем ссылку Заказчики")
    void clickCustomers() {
        mainPageProvider.adminLink(linkProvider.adminMainPageLink());
        adminMainPage.clickCustomers();
        new CustomersMainPage().getCustomerPageTitle();
    }

    @TestType.web
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Проверяем ссылку Заказы/Подписки")
    void clickOrderSubscriptions() {
        mainPageProvider.adminLink(linkProvider.adminMainPageLink());
        adminMainPage.clickOrderSubscriptions();
        new OrdersSubscriptionsMainPage().checkOrdersSubscriptionPageTitle();
    }
}
