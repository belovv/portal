package adminTest.navigationTest;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import portal.admin.CustomersMainPage;
import portal.admin.customer_page.CustomerProfilePage;
import portal.admin.order_subscription_page.subscription_profile_pages.SubscriptionProfilePage;
import portal.annotations.Admin;
import portal.annotations.TestType;
import portal.data.ApiSubscriptionData;
import portal.data.LinkProvider;
import portal.listener.BaseTestExtension;
import portal.lk.LKMainPage;
import portal.providers.DataProvider;
import portal.providers.MainPageProvider;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

@Admin
@ExtendWith(BaseTestExtension.class)
public class AdminNavigationCustomersTest {

    private static final String CUSTOMER_NUMBER_LINK = "/index/4";
    private final Map<String, String> data = new HashMap<>();
    private final MainPageProvider mainPageProvider = new MainPageProvider();
    private final CustomerProfilePage customerProfilePage = new CustomerProfilePage();
    private final SubscriptionProfilePage subscriptionProfilePage = new SubscriptionProfilePage();
    private final LinkProvider linkProvider = new DataProvider().linkProvider();
    private final ApiSubscriptionData dat = new ApiSubscriptionData();
    private final DataProvider provider = new DataProvider();

    @TestType.web
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Переход из профиля заказчика в профиль подписки заказчика")
    void checkLinkFromCustomerProfileToSubscriptionProfile() {
        mainPageProvider.adminLink(CUSTOMER_NUMBER_LINK + "/subscriptions");
        step("Кликаем на первую подписку из списка", (step) -> {
            data.put("idSub", $("td[slot-key='instance_id']").text());
            $("td[slot-key='instance_id']").click();
        });
        subscriptionProfilePage.checkSubscriptionPageTitleById(data.get("idSub"));
    }

    @TestType.e2e
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Переход из профиля заказчика админки в личный кабинет заказчика")
    void checkLinkFromAdminToLK() {
        mainPageProvider.adminLink(CUSTOMER_NUMBER_LINK + "/actions?history_sort=-date");
        customerProfilePage.clickOnButton("Войти в кабинет");
        new LKMainPage().checkLogoIsVisible();
    }
}
