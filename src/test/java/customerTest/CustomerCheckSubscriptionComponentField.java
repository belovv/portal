package customerTest;

import org.junit.jupiter.api.DisplayName;
import portal.annotations.LK;
import portal.annotations.TestType;
import portal.data.LinkProvider;
import portal.data.SubscriptionData;
import portal.data.enumdata.TypeUser;
import portal.lk.CatalogPage;
import portal.lk.NewSubscriptionOrderPage;
import portal.providers.DataProvider;
import portal.providers.MainPageProvider;
import portal.providers.SubscriptionPageFactory;

import static portal.data.enumdata.SubscriptionIdName.*;
import static portal.data.enumdata.TypeSubscription.COMMERCIAL;
import static portal.data.enumdata.component.BackupComponent.*;
import static portal.data.enumdata.component.CloudStorageComponent.STORAGE;
import static portal.data.enumdata.component.InternetComponent.INTERNET_IP;
import static portal.data.enumdata.component.InternetComponent.INTERNET_SPEED;
import static portal.data.enumdata.component.MicrosoftComponent.*;
import static portal.data.enumdata.component.OpenstackComponent.*;
import static portal.data.enumdata.component.VMwareComponent.*;


public class CustomerCheckSubscriptionComponentField {

    private final DataProvider dataProvider = new DataProvider();
    private final SubscriptionData subscriptionData = dataProvider.subscriptionData();
    private final LinkProvider linkProvider = dataProvider.linkProvider();
    private final MainPageProvider mainPageProvider = new MainPageProvider(TypeUser.CUSTOMER_CHECK_NAME_SUBSCRIPTION);
    private final CatalogPage catalogPage = new CatalogPage();
    private final SubscriptionPageFactory subscriptionPageFactory = new SubscriptionPageFactory();
    private final NewSubscriptionOrderPage vmwareProtectOrderPage = subscriptionPageFactory.getCustomerOrderPage(PROTECT);
    private final NewSubscriptionOrderPage openstackOrderPage = subscriptionPageFactory.getCustomerOrderPage(OPENSTACK_CSS);
    private final NewSubscriptionOrderPage backupOrderPage = subscriptionPageFactory.getCustomerOrderPage(BACKUP_CSS);
    private final NewSubscriptionOrderPage internetOrderPage = subscriptionPageFactory.getCustomerOrderPage(INTERNET_CSS);
    private final NewSubscriptionOrderPage microsoftOrderPage = subscriptionPageFactory.getCustomerOrderPage(MICROSOFT_CSS);

    @LK
    @TestType.web
    @DisplayName("Проверка название полей компонент VMware+")
    void checkComponentFieldVMware() {
        mainPageProvider.openLkLink(linkProvider.catalogLinkLK());
        catalogPage.clickOrderSubscription(subscriptionData.vmwareProtectName());
        vmwareProtectOrderPage
                .clickOrderType(COMMERCIAL)
                .checkField(SCHEME_PRICING, subscriptionData.tariffName())
                .checkField(VCPU, subscriptionData.cpuName())
                .checkField(VRAM, subscriptionData.ramName())
                .checkField(HDD_7K, subscriptionData.hdd7KName())
                .checkField(HDD_10K, subscriptionData.hdd10KName())
                .checkField(HDD_HYBRID, subscriptionData.hddHybridName())
                .checkField(SSD, subscriptionData.ssdName())
                .checkField(SSD_AF, subscriptionData.ssdAFName())
                .checkField(USB, subscriptionData.usbName())
                .checkField(NOTE, subscriptionData.note());
        backupOrderPage
                .checkField(BACKUP_VOLUME, subscriptionData.volumeName())
                .checkField(VM_COUNT, subscriptionData.backupVmName())
                .checkField(TRAFFIC_MIN, subscriptionData.trafficMinName())
                .checkField(TRAFFIC_SPEED, subscriptionData.trafficSpeedName())
                .checkField(CLOUD_PLACEMENT, subscriptionData.cloudName())
                .checkField(NOTE, subscriptionData.note());
        internetOrderPage
                .checkField(INTERNET_SPEED, subscriptionData.inetSpeedName())
                .checkField(INTERNET_IP, subscriptionData.ipName())
                .checkField(NOTE, subscriptionData.note())
                .checkBox(subscriptionData.checkBoxMicrosoft());
        microsoftOrderPage
                .checkField(VISIO_PROFESSIONAL, subscriptionData.visioProfessionalName())
                .checkField(SHAREPOINT_SERVER_STANDARD, subscriptionData.sharePointServerStandardName())
                .checkField(SERVER_SUITE_STANDARD, subscriptionData.serverSuiteStandardName())
                .checkField(SQL_SERVER_STANDARD_CORE, subscriptionData.sqlServerStandardCoreName())
                .checkField(EXCHANGE_BASIC, subscriptionData.exchangeBasicName())
                .checkField(EXCHANGE_ENTERPRISE_PLUS, subscriptionData.exchangeEnterprisePlusName())
                .checkField(VISIO_STANDARD, subscriptionData.visioStandardName())
                .checkField(PROJECT_PROFESSIONAL, subscriptionData.projectProfessionalName())
                .checkField(WINDOWS_SERVER_DATA_CENTER, subscriptionData.windowsServerDataCenterName())
                .checkField(SQL_SERVER_ENTERPRISE, subscriptionData.sqlServerEnterpriseName())
                .checkField(OFFICE_STANDARD, subscriptionData.officeStandardName())
                .checkField(EXCHANGE_ENTERPRISE, subscriptionData.exchangeEnterpriseName())
                .checkField(OFFICE_PROFESSIONAL_PLUS, subscriptionData.officeProfessionalPlusName())
                .checkField(PROJECT_SERVER, subscriptionData.projectServerName())
                .checkField(WINDOWS_SERVER_STANDARD, subscriptionData.windowsServerStandardName())
                .checkField(REMOTE_DESKTOP_SERVICE, subscriptionData.remoteDesktopServiceName())
                .checkField(SQL_SERVER_STANDARD, subscriptionData.sqlServerStandardName())
                .checkField(EXCHANGE_STANDARD_PLUS, subscriptionData.exchangeStandardPlusName())
                .checkField(PROJECT, subscriptionData.projectName())
                .checkField(SHAREPOINT_SERVER_PROFESSIONAL, subscriptionData.sharePointServerProfessionalName())
                .checkField(SERVER_SUITE_DATA_CENTER, subscriptionData.serverSuiteDataCenterName())
                .checkField(SQL_SERVER_WEB, subscriptionData.sqlServerWebName())
                .checkField(EXCHANGE_STANDARD, subscriptionData.exchangeStandardName())
                .checkField(WINDOWS_SERVER_STANDARD_VM, subscriptionData.windowsServerStandardVirtualMachineName())
                .checkField(RIGHTS_MANAGEMENT_SERVICES, subscriptionData.rightsManagementServicesName())
                .checkField(OFFICE_MULTI_LANGUAGE, subscriptionData.microsoftOfficeMultiLanguageName())
                .checkField(VISUAL_STUDIO, subscriptionData.visualStudioName())
                .checkField(VISUAL_STUDIO_BASIC, subscriptionData.visualStudioBasicName())
                .checkField(VISUAL_STUDIO_PROFESSIONAL, subscriptionData.visualStudioProfessionalName())
                .checkField(VISUAL_STUDIO_TEST_PROFESSIONAL, subscriptionData.visualStudioTestProfessionalName())
                .checkField(VISUAL_STUDIO_ENTERPRISE, subscriptionData.visualStudioEnterpriseName())
                .checkField(SHAREPOINT_SERVER, subscriptionData.microsoftSharePointServerName())
                .checkField(SHAREPOINT_HOSTING, subscriptionData.microsoftSharePointHostingName())
                .checkField(NOTE, subscriptionData.note());
    }

    @LK
    @TestType.web
    @DisplayName("Проверка название полей компонент Openstack ")
    void checkComponentFieldOpenstack() {
        mainPageProvider.openLkLink(linkProvider.catalogLinkLK());
        catalogPage.clickOrderSubscription(subscriptionData.openstackName());
        openstackOrderPage
                .clickOrderType(COMMERCIAL)
                .checkField(SCHEME_PRICING, subscriptionData.tariffName())
                .checkField(OPENSTACK_VCPU, subscriptionData.cpuName())
                .checkField(OPENSTACK_VRAM, subscriptionData.ramName())
                .checkField(OPENSTACK_HDD_7K, subscriptionData.hdd7KSDSName())
                .checkField(OPENSTACK_HDD_10K, subscriptionData.hdd10KSDSName())
                .checkField(OPENSTACK_SSD_SDS, subscriptionData.hddSSDSDSName())
                .checkField(NOTE, subscriptionData.note());
    }

    @LK
    @TestType.web
    @DisplayName("Проверка название полей компонент Облачное хранилище ")
    void checkComponentFieldCloudStorage() {
        mainPageProvider.openLkLink(linkProvider.catalogLinkLK());
        catalogPage.clickOrderSubscription(subscriptionData.cloudStorageName());
        openstackOrderPage
                .clickOrderType(COMMERCIAL)
                .checkField(STORAGE, "Тариф")
                .checkField(NOTE, subscriptionData.note());
    }
}
