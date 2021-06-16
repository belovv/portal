package adminTest.subscriptionTest;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import portal.admin.OrdersSubscriptionsMainPage;
import portal.admin.order_subscription_page.subscription_profile_pages.SubscriptionProfilePage;
import portal.annotations.Admin;
import portal.annotations.TestType;
import portal.data.LinkProvider;
import portal.data.PopUpMessage;
import portal.data.SubscriptionData;
import portal.listener.BaseTestExtension;
import portal.providers.DataProvider;
import portal.providers.MainPageProvider;
import portal.utils.DateTime;
import portal.utils.PopUp;
import portal.utils.PopUpFull;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static portal.data.enumdata.ButtonName.CHANGE;
import static portal.data.enumdata.ButtonName.EXTEND_TESTING;
import static portal.data.enumdata.ModeCharge.FACT;
import static portal.data.enumdata.ModeCharge.FIX;
import static portal.data.enumdata.component.PopUpComponent.MODE_CHARGE;
import static portal.data.enumdata.TabName.STATUS;

@ExtendWith(BaseTestExtension.class)
public class AdminTestSubscriptionProfileTest {

    private final PopUp popUp = new PopUp();
    private final PopUpFull popUpFull = new PopUpFull();
    private final OrdersSubscriptionsMainPage ordersSubscriptionsMainPage = new OrdersSubscriptionsMainPage();
    private final SubscriptionProfilePage subscriptionProfilePage = new SubscriptionProfilePage();
    private final MainPageProvider mainPageProvider = new MainPageProvider();
    private final DataProvider dataProvider = new DataProvider();
    private final PopUpMessage popUpMessage = dataProvider.popUpMessage();
    private final LinkProvider linkProvider = dataProvider.linkProvider();
    private final SubscriptionData subscriptionData = new DataProvider().subscriptionData();
    private final DateTime dateTime = new DateTime();

    @Admin.ordersSubscriptions.subscriptionsProfile
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Перевод активной подписки из тестового в коммерческий режим с датой сегодня")
    @TestType.integration
    void changeModeOfSubscriptionToCommercialToday() {
        mainPageProvider.adminLink(linkProvider.subscriptionActiveTestLink());
        ordersSubscriptionsMainPage.clickOnFirstSubscription();
        subscriptionProfilePage
                .clickOnMenu(STATUS.getValue())
                .clickChangeModeToCommercial();
        //Todo добавить проверку текста сообщения отдельным тестом
        popUpFull
                .changeItem(MODE_CHARGE, FIX.getValue())
                .setComment(subscriptionData.subscriptionTestComment())
                .setDate(dateTime.getTodayDate())
                .clickButtonByName(CHANGE);
        popUp.checkMessageText(popUpMessage.subscriptionToCommercial());
    }

    @Admin.ordersSubscriptions.subscriptionsProfile
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Перевод активной подписки из тестового в коммерческий режим с датой завтра")
    @TestType.integration
    void changeModeOfSubscriptionToCommercialTomorrow() {
        mainPageProvider.adminLink(linkProvider.subscriptionActiveTestLink());
        ordersSubscriptionsMainPage.clickOnFirstSubscription();
        subscriptionProfilePage
                .clickOnMenu(STATUS.getValue())
                .clickChangeModeToCommercial();
        popUpFull
                .changeItem(MODE_CHARGE, FACT.getValue())
                .setComment(subscriptionData.subscriptionTestComment())
                .setDate(dateTime.getTomorrowDate())
                .clickButtonByName(CHANGE);
        popUp.checkMessageText(popUpMessage.subscriptionToCommercial());
    }

    @Disabled
    @Admin.ordersSubscriptions.subscriptionsProfile
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Продлеваем тестирование для подписки без уведомления заказчика")
    @TestType.integration
    void extendTestingOfSubscription() {
        mainPageProvider.adminLink("/subscriptions/list?sort=-order.order_id,-instance_id&filter=%7B\"search\":%5B%5D,\"fields\":%7B\"is_trial\":%5Btrue%5D,\"state\":%5B\"active\"%5D%7D%7D");
        ordersSubscriptionsMainPage.clickOnFirstSubscription();
        String datePage = subscriptionProfilePage.getCurrentTestingDate();
        subscriptionProfilePage.clickExtendTesting();
        assertEquals(datePage, popUpFull.getCurrentTestingDate(), "даты окончания тестирования на странице и и в PopUp не совпадают");
        popUpFull
                .setComment(subscriptionData.subscriptionExtendTestingComment())
                .clickButtonByName(EXTEND_TESTING);
    }

}
