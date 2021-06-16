package customerTest;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import portal.annotations.LK;
import portal.annotations.TestType;
import portal.data.LinkProvider;
import portal.data.SubscriptionData;
import portal.listener.BaseTestExtension;
import portal.lk.MyServicesPage;
import portal.providers.DataProvider;
import portal.providers.MainPageProvider;
import portal.data.enumdata.TypeUser;

@LK.changeRequestSubscription
@ExtendWith(BaseTestExtension.class)
public class CustomerChangeRequestSubscriptionTest {

    private final MyServicesPage myServicesPage = new MyServicesPage();
    private final DataProvider dataProvider = new DataProvider();
    private final LinkProvider linkProvider = dataProvider.linkProvider();
    private final SubscriptionData subscriptionData = dataProvider.subscriptionData();
    private final MainPageProvider mainPageProvider = new MainPageProvider(TypeUser.CUSTOMER_CHANGE_SUBSCRIPTION);

    @Disabled("Нет локаторов, договориться с фронтом")
    @Severity(value = SeverityLevel.CRITICAL)
    @TestType.e2e
    @DisplayName("Меняем объёмы для VMware защищенная")
    void changeRequestVMwareProtect() {
        mainPageProvider.openLkLink(linkProvider.myServiceActiveSubscriptionLinkLK());
        myServicesPage.clickOnActiveSubscription(subscriptionData.vmwareProtectShortName());


    }
}
