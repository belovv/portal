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
import portal.lk.CatalogPage;
import portal.providers.DataProvider;
import portal.providers.MainPageProvider;

import static portal.data.enumdata.TypeUser.CUSTOMER_NEW_SUBSCRIPTION;

@LK.catalog
@ExtendWith(BaseTestExtension.class)
public class CustomerCheckNewSubscriptionIdNameTest {

    CatalogPage catalogPage = new CatalogPage();
    DataProvider dataProvider = new DataProvider();
    MainPageProvider openPage = new MainPageProvider(CUSTOMER_NEW_SUBSCRIPTION);
    LinkProvider linkProvider = dataProvider.linkProvider();
    SubscriptionData subscriptionData = dataProvider.subscriptionData();

    @TestType.web
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Проверяем количество услуг для заказа на вкладке Каталог услуг")
    public void checkCatalogGUI() {
        openPage.openLkLink(linkProvider.catalogLinkLK());
        catalogPage.checkValueSubscriptions(6);
    }

    @TestType.web
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Проверяем текст IX VMware")
    public void checkVMwareIX() {
        openPage.openLkLink(linkProvider.catalogLinkLK());
        catalogPage.checkTextSubscription(subscriptionData.vmwareIXShortName(),
                subscriptionData.vmwareIXName(),
                subscriptionData.vmwareIXDescription());
    }

    @TestType.web
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Проверяем текст VMware 3GHz+")
    public void checkVMware3GHz() {
        openPage.openLkLink(linkProvider.catalogLinkLK());
        catalogPage.checkTextSubscription(subscriptionData.vmware3GHzShortName(),
                subscriptionData.vmware3GHzName(),
                subscriptionData.vmware3GHzDescription());
    }

    @TestType.web
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Проверяем текст VMware защищеннный")
    public void checkVMwareProtect() {
        openPage.openLkLink(linkProvider.catalogLinkLK());
        catalogPage.checkTextSubscription(subscriptionData.vmwareProtectShortName(),
                subscriptionData.vmwareProtectName(),
                subscriptionData.vmwareProtectDescription());
    }

    @TestType.web
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Проверяем текст VMware закрытый")
    public void checkVMwareClose() {
        openPage.openLkLink(linkProvider.catalogLinkLK());
        catalogPage.checkTextSubscription(subscriptionData.vmwareCloseShortName(),
                subscriptionData.vmwareCloseName(),
                subscriptionData.vmwareCloseDescription());
    }

    @TestType.web
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Проверяем текст Openstack")
    public void checkOpenstack() {
        openPage.openLkLink(linkProvider.catalogLinkLK());
        catalogPage.checkTextSubscription(subscriptionData.openstackShortName(),
                subscriptionData.openstackName(),
                subscriptionData.openstackDescription());
    }

    @TestType.web
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Проверяем текст облачное хранилище")
    public void checkCloudStorage() {
        openPage.openLkLink(linkProvider.catalogLinkLK());
        catalogPage.checkTextSubscription(subscriptionData.cloudStorageShortName(),
                subscriptionData.cloudStorageName(),
                subscriptionData.cloudStorageDescription());
    }
}
