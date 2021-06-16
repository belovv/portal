package adminTest;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import portal.admin.OrdersSubscriptionsMainPage;
import portal.annotations.Admin;
import portal.annotations.TestType;
import portal.listener.BaseTestExtension;
import portal.providers.MainPageProvider;
import portal.utils.Excel;

import java.io.File;


@Disabled
@ExtendWith(BaseTestExtension.class)
public class AdminDownloadTest {


    Excel excel = new Excel();
    OrdersSubscriptionsMainPage ordersSubscriptionsMainPage = new OrdersSubscriptionsMainPage();
    MainPageProvider mainPageProvider = new MainPageProvider();

    @Disabled
    @Admin.ordersSubscriptions.subscriptions
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Экспорт подписок в Excel, заказы/подписки->подписки")
    @TestType.integration
    void exportSubscriptionToExcel() {
        mainPageProvider.adminLink("/admin/subscriptions/list?sort=-order.order_id,-instance_id");
        File allSubscriptionExcel = ordersSubscriptionsMainPage.clickExportExcel();
        excel.checkSmallExcelHaveNoBlank(allSubscriptionExcel);
    }
}
