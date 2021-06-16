package customerTest;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
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

@LK.myServices.newSubscription
@ExtendWith(BaseTestExtension.class)
public class CustomerCheckOrderedSubscriptionIdNameTest {

    private final MyServicesPage myServicesPage = new MyServicesPage();
    private final DataProvider dataProvider = new DataProvider();
    private final LinkProvider linkProvider = dataProvider.linkProvider();
    private final SubscriptionData subscriptionData = dataProvider.subscriptionData();
    private final MainPageProvider mainPageProvider = new MainPageProvider(TypeUser.CUSTOMER_CHECK_NAME_SUBSCRIPTION);

    @TestType.web
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Проверяем текст заказанной VMware 3GHz+")
    public void checkOrderVMware3GHz() {
        mainPageProvider.openLkLink(linkProvider.myServiceNewSubscriptionsLinkLK());
        myServicesPage.checkTextSubscription(subscriptionData.vmware3GHzShortName(),
                subscriptionData.vmware3GHzName());
    }

    @TestType.web
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Проверяем текст заказанной VMware 3GHz+")
    public void checkOrderIXVMware() {
        mainPageProvider.openLkLink(linkProvider.myServiceNewSubscriptionsLinkLK());
        myServicesPage.checkTextSubscription(subscriptionData.vmwareIXShortName(),
                subscriptionData.vmwareIXName());
    }

    @TestType.web
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Проверяем текст заказанной VMware защищеннный")
    public void checkOrderVMwareProtect() {
        mainPageProvider.openLkLink(linkProvider.myServiceNewSubscriptionsLinkLK());
        myServicesPage.checkTextSubscription(subscriptionData.vmwareProtectShortName(),
                subscriptionData.vmwareProtectName());
    }

    @TestType.web
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Проверяем текст заказанной VMware закрытый")
    public void checkOrderVMwareClose() {
        mainPageProvider.openLkLink(linkProvider.myServiceNewSubscriptionsLinkLK());
        myServicesPage.checkTextSubscription(subscriptionData.vmwareCloseShortName(),
                subscriptionData.vmwareCloseName());
    }

    @TestType.web
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Проверяем текст заказанной Openstack")
    public void checkOrderOpenstack() {
        mainPageProvider.openLkLink(linkProvider.myServiceNewSubscriptionsLinkLK());
        myServicesPage.checkTextSubscription(subscriptionData.openstackShortName(),
                subscriptionData.openstackName());
    }

    @TestType.web
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Проверяем текст заказанного облачного хранилище")
    public void checkOrderCloudStorage() {
        mainPageProvider.openLkLink(linkProvider.myServiceNewSubscriptionsLinkLK());
        myServicesPage.checkTextSubscription(subscriptionData.cloudStorageShortName(),
                subscriptionData.cloudStorageName());
    }

    @TestType.web
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Проверяем текст заказанного 'Microsoft'")
    public void checkOrderMicrosoft() {
        mainPageProvider.openLkLink(linkProvider.myServiceNewSubscriptionsLinkLK());
        myServicesPage.checkTextSubscription(subscriptionData.microsoftShortName(),
                subscriptionData.microsoftName());
    }

    @TestType.web
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Проверяем текст заказанного 'Резервированный доступ в Интернет'")
    public void checkOrderInternet() {
        mainPageProvider.openLkLink(linkProvider.myServiceNewSubscriptionsLinkLK());
        myServicesPage.checkTextSubscription(subscriptionData.accessInternetShortName(),
                subscriptionData.accessInternetName());
    }

    @TestType.web
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Проверяем текст заказанного 'Резервное копирование'")
    public void checkOrderBackup() {
        mainPageProvider.openLkLink(linkProvider.myServiceNewSubscriptionsLinkLK());
        myServicesPage.checkTextSubscription(subscriptionData.backupShortName(),
                subscriptionData.backupName());
    }
}
