package adminTest.subscriptionTest;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import portal.admin.OrdersSubscriptionsMainPage;
import portal.admin.order_subscription_page.new_subscriptions_page.NewSubscriptionPageOne;
import portal.admin.order_subscription_page.subscription_profile_pages.SubscriptionProfilePage;
import portal.admin.order_subscription_page.subscription_profile_pages.SubscriptionRequestPage;
import portal.annotations.Admin;
import portal.annotations.TestType;
import portal.data.LinkProvider;
import portal.data.SubscriptionData;
import portal.listener.BaseTestExtension;
import portal.providers.DataProvider;
import portal.providers.MainPageProvider;
import portal.providers.SubscriptionPageFactory;

import static portal.data.enumdata.SubscriptionIdName.*;
import static portal.data.enumdata.TabName.REQUESTS;
import static portal.data.enumdata.TabName.VOLUME;
import static portal.data.enumdata.TypeSubscription.TYPE_COMMERCIAL;
import static portal.data.enumdata.component.BackupComponent.*;
import static portal.data.enumdata.component.CloudStorageComponent.STORAGE;
import static portal.data.enumdata.component.InternetComponent.INTERNET_IP;
import static portal.data.enumdata.component.InternetComponent.INTERNET_SPEED;
import static portal.data.enumdata.component.MicrosoftComponent.*;
import static portal.data.enumdata.component.OpenstackComponent.*;
import static portal.data.enumdata.component.VMwareComponent.*;

@ExtendWith(BaseTestExtension.class)
public class AdminCheckSubscriptionComponentField {

    private final DataProvider dataProvider = new DataProvider();
    private final SubscriptionData subscriptionData = dataProvider.subscriptionData();
    private final LinkProvider linkProvider = dataProvider.linkProvider();
    private final MainPageProvider mainPageProvider = new MainPageProvider();
    private final OrdersSubscriptionsMainPage ordersSubscriptionsMainPage = new OrdersSubscriptionsMainPage();
    private final SubscriptionProfilePage subscriptionProfilePage = new SubscriptionProfilePage();
    private final SubscriptionPageFactory subscriptionPageFactory = new SubscriptionPageFactory();
    private final NewSubscriptionPageOne newSubscriptionPageOne = new NewSubscriptionPageOne();
    private final SubscriptionRequestPage vmware = subscriptionPageFactory.getRequestPage(PROTECT);
    private final SubscriptionRequestPage openstack = subscriptionPageFactory.getRequestPage(OPENSTACK_CSS);
    private final SubscriptionRequestPage internet = subscriptionPageFactory.getRequestPage(INTERNET_CSS);
    private final SubscriptionRequestPage backup = subscriptionPageFactory.getRequestPage(BACKUP_CSS);
    private final SubscriptionRequestPage microsoft = subscriptionPageFactory.getRequestPage(MICROSOFT_CSS);
    private final SubscriptionRequestPage cloudStorage = subscriptionPageFactory.getRequestPage(CLOUD_STORAGE_CSS);


    @Admin.newSubscription
    @TestType.web
    @DisplayName("Проверка название полей компонент при заказе VMware +")
    void checkComponentFieldVMwarePlus() {
        mainPageProvider.adminLink(linkProvider.newSubscriptionLink());
        newSubscriptionPageOne
                .setCustomer("")
                .setSubscription(subscriptionData.vmwareProtectName())
                .setTypeSubscription(TYPE_COMMERCIAL)
                .clickButton();
        //*************Step 2**********************
        subscriptionPageFactory.getNewSubscriptionPage(PROTECT)
                .checkField(SCHEME_PRICING, subscriptionData.tariffName())
                .checkField(VCPU, subscriptionData.cpuName())
                .checkField(VRAM, subscriptionData.ramName())
                .checkField(HDD_7K, subscriptionData.hdd7KName())
                .checkField(HDD_10K, subscriptionData.hdd10KName())
                .checkField(HDD_HYBRID, subscriptionData.hddHybridName())
                .checkField(SSD, subscriptionData.ssdName())
                .checkField(SSD_AF, subscriptionData.ssdAFName())
                .checkField(USB, subscriptionData.usbName())
                .checkField(NOTE, subscriptionData.note())
                .checkBox(subscriptionData.checkBoxMicrosoft());
        subscriptionPageFactory.getNewSubscriptionPage(BACKUP_CSS)
                .checkField(BACKUP_VOLUME, subscriptionData.volumeName())
                .checkField(VM_COUNT, subscriptionData.backupVmName())
                .checkField(TRAFFIC_MIN, subscriptionData.trafficMinName())
                .checkField(TRAFFIC_SPEED, subscriptionData.trafficSpeedName())
                .checkField(CLOUD_PLACEMENT, subscriptionData.cloudName())
                .checkField(NOTE, subscriptionData.note());
        subscriptionPageFactory.getNewSubscriptionPage(INTERNET_CSS)
                .checkField(INTERNET_SPEED, subscriptionData.inetSpeedName())
                .checkField(INTERNET_IP, subscriptionData.ipName())
                .checkField(NOTE, subscriptionData.note());
        subscriptionPageFactory.getNewSubscriptionPage(MICROSOFT_CSS)
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

    @Admin.newSubscription
    @TestType.web
    @DisplayName("Проверка название полей компонент при заказе Openstack")
    void checkComponentFieldNewOpenstack() {
        mainPageProvider.adminLink(linkProvider.newSubscriptionLink());
        newSubscriptionPageOne
                .setCustomer("")
                .setSubscription(subscriptionData.openstackName())
                .setTypeSubscription(TYPE_COMMERCIAL)
                .clickButton();
        //*************Step 2**********************
        subscriptionPageFactory.getNewSubscriptionPage(OPENSTACK_CSS)
                .checkField(SCHEME_PRICING, subscriptionData.tariffName())
                .checkField(OPENSTACK_VCPU, subscriptionData.cpuName())
                .checkField(OPENSTACK_VRAM, subscriptionData.ramName())
                .checkField(OPENSTACK_HDD_7K, subscriptionData.hdd7KSDSName())
                .checkField(OPENSTACK_HDD_10K, subscriptionData.hdd10KSDSName())
                .checkField(OPENSTACK_SSD_SDS, subscriptionData.hddSSDSDSName())
                .checkField(NOTE, subscriptionData.note());
    }

    @Admin.newSubscription
    @TestType.web
    @DisplayName("Проверка название полей компонент при заказе Облачное хранилище")
    void checkComponentFieldNewCloudStorage() {
        mainPageProvider.adminLink(linkProvider.newSubscriptionLink());
        newSubscriptionPageOne
                .setCustomer("")
                .setSubscription(subscriptionData.cloudStorageName())
                .setTypeSubscription(TYPE_COMMERCIAL)
                .clickButton();
        //*************Step 2**********************
        subscriptionPageFactory.getNewSubscriptionPage(CLOUD_STORAGE_CSS)
                .checkField(STORAGE, subscriptionData.cloudStorageTariffName())
                .checkField(NOTE, subscriptionData.note());
    }

    @Admin.newSubscription
    @TestType.web
    @DisplayName("Проверка название полей компонент первой страницы при заказе услуг")
    void checkComponentFieldNewPageOne() {
        mainPageProvider.adminLink(linkProvider.newSubscriptionLink());
        newSubscriptionPageOne
                .checkFieldCustomer()
                .checkFieldSubscription()
                .checkTypeSubscription()
                .checkFieldDate()
                .checkFieldPaperOrder();
    }

    @Admin.ordersSubscriptions.subscriptionsProfile
    @TestType.web
    @DisplayName("Проверка название полей заявки VMware")
    void checkComponentFieldVMware() {
        mainPageProvider.adminLink(linkProvider.subscriptionVmwareProtectLink());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.vmwareProtectName());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        vmware
                .checkField(VCPU, subscriptionData.cpuName())
                .checkField(VRAM, subscriptionData.ramName())
                .checkField(HDD_7K, subscriptionData.hdd7KName())
                .checkField(HDD_10K, subscriptionData.hdd10KName())
                .checkField(HDD_HYBRID, subscriptionData.hddHybridName())
                .checkField(SSD, subscriptionData.ssdName())
                .checkField(SSD_AF, subscriptionData.ssdAFName())
                .checkField(USB, subscriptionData.usbName());
    }

    @Admin.ordersSubscriptions.subscriptionsProfile
    @TestType.web
    @DisplayName("Проверка название полей заявки Openstack")
    void checkComponentFieldOpenstack() {
        mainPageProvider.adminLink(linkProvider.subscriptionOpenstackLink());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.openstackName());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        openstack
                .checkField(OPENSTACK_VCPU, subscriptionData.cpuName())
                .checkField(OPENSTACK_VRAM, subscriptionData.ramName())
                .checkField(OPENSTACK_HDD_7K, subscriptionData.hdd7KSDSName())
                .checkField(OPENSTACK_HDD_10K, subscriptionData.hdd10KSDSName())
                .checkField(OPENSTACK_SSD_SDS, subscriptionData.hddSSDSDSName());
    }

    @Admin.ordersSubscriptions.subscriptionsProfile
    @TestType.web
    @DisplayName("Проверка название полей заявки Доступ в Интернет")
    void checkComponentFieldInternet() {
        mainPageProvider.adminLink(linkProvider.subscriptionInternetLink());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.accessInternetName());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        internet
                .checkField(INTERNET_SPEED, subscriptionData.inetSpeedName())
                .checkField(INTERNET_IP, subscriptionData.ipName());
    }

    @Admin.ordersSubscriptions.subscriptionsProfile
    @TestType.web
    @DisplayName("Проверка название полей заявки Резервное копирование")
    void checkComponentFieldBackup() {
        mainPageProvider.adminLink(linkProvider.subscriptionBackupLink());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.backupName());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        backup
                .checkField(BACKUP_VOLUME, subscriptionData.volumeName())
                .checkField(VM_COUNT, subscriptionData.backupVmName())
                .checkField(TRAFFIC_MIN, subscriptionData.trafficMinName())
                .checkField(TRAFFIC_SPEED, subscriptionData.trafficSpeedName())
                .checkField(CLOUD_PLACEMENT, subscriptionData.cloudName());
    }

    @Admin.ordersSubscriptions.subscriptionsProfile
    @TestType.web
    @DisplayName("Проверка название полей заявки Microsoft")
    void checkComponentFieldMicrosoft() {
        mainPageProvider.adminLink(linkProvider.subscriptionMicrosoftLink());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.microsoftName());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        microsoft
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
                .checkField(SHAREPOINT_HOSTING, subscriptionData.microsoftSharePointHostingName());
    }

    @Admin.ordersSubscriptions.subscriptionsProfile
    @TestType.web
    @DisplayName("Проверка название полей заявки Облачное хранилище")
    void checkComponentFieldCloudStorage() {
        mainPageProvider.adminLink(linkProvider.subscriptionCloudStorageLink());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.cloudStorageName());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        cloudStorage.checkField(STORAGE, subscriptionData.cloudStorageTariffName());
    }

    @Admin.ordersSubscriptions.subscriptionsProfile
    @TestType.web
    @DisplayName("Проверка название полей объемов VMware")
    void checkComponentFieldVolumeVMware() {
        mainPageProvider.adminLink(linkProvider.subscriptionVmwareProtectLink());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.vmwareProtectName());
        subscriptionProfilePage.clickOnMenu(VOLUME.getValue());
        vmware
                .checkField(VCPU, subscriptionData.cpuName())
                .checkField(VRAM, subscriptionData.ramName())
                .checkField(HDD_7K, subscriptionData.hdd7KName())
                .checkField(HDD_10K, subscriptionData.hdd10KName())
                .checkField(HDD_HYBRID, subscriptionData.hddHybridName())
                .checkField(SSD, subscriptionData.ssdName())
                .checkField(SSD_AF, subscriptionData.ssdAFName())
                .checkField(USB, subscriptionData.usbName());
    }

    @Admin.ordersSubscriptions.subscriptionsProfile
    @TestType.web
    @DisplayName("Проверка название полей объемов Openstack")
    void checkComponentFieldVolumeOpenstack() {
        mainPageProvider.adminLink(linkProvider.subscriptionOpenstackLink());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.openstackName());
        subscriptionProfilePage.clickOnMenu(VOLUME.getValue());
        openstack
                .checkField(OPENSTACK_VCPU, subscriptionData.cpuName())
                .checkField(OPENSTACK_VRAM, subscriptionData.ramName())
                .checkField(OPENSTACK_HDD_7K, subscriptionData.hdd7KSDSName())
                .checkField(OPENSTACK_HDD_10K, subscriptionData.hdd10KSDSName())
                .checkField(OPENSTACK_SSD_SDS, subscriptionData.hddSSDSDSName());
    }

    @Disabled("добавить поля")
    @Admin.ordersSubscriptions.subscriptionsProfile
    @TestType.web
    @DisplayName("Проверка название полей объемов Доступ в Интернет")
    void checkComponentFieldVolumeInternet() {
        mainPageProvider.adminLink(linkProvider.subscriptionInternetLink());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.accessInternetName());
        subscriptionProfilePage.clickOnMenu(VOLUME.getValue());
        internet
                .checkField(INTERNET_SPEED, subscriptionData.inetSpeedName())
                .checkField(INTERNET_IP, subscriptionData.ipName());
    }

    @Admin.ordersSubscriptions.subscriptionsProfile
    @TestType.web
    @DisplayName("Проверка название полей объемов Резервное копирование")
    void checkComponentFieldVolumeBackup() {
        mainPageProvider.adminLink(linkProvider.subscriptionBackupLink());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.backupName());
        subscriptionProfilePage.clickOnMenu(VOLUME.getValue());
        backup
                .checkField(BACKUP_VOLUME, subscriptionData.volumeName())
                .checkField(VM_COUNT, subscriptionData.backupVmName())
                .checkField(TRAFFIC_MIN, subscriptionData.trafficMinName())
                .checkField(TRAFFIC_SPEED, subscriptionData.trafficSpeedName())
                .checkField(CLOUD_PLACEMENT, subscriptionData.cloudName());
    }

    @Admin.ordersSubscriptions.subscriptionsProfile
    @TestType.web
    @DisplayName("Проверка название полей объемов Microsoft")
    void checkComponentFieldVolumeMicrosoft() {
        mainPageProvider.adminLink(linkProvider.subscriptionMicrosoftLink());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.microsoftName());
        subscriptionProfilePage.clickOnMenu(VOLUME.getValue());
        microsoft
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
                .checkField(SHAREPOINT_HOSTING, subscriptionData.microsoftSharePointHostingName());
    }

}
