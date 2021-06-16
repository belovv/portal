package adminTest.subscriptionTest;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import portal.admin.OrdersSubscriptionsMainPage;
import portal.admin.order_subscription_page.subscription_profile_pages.HeaderSubscriptionRequestPage;
import portal.admin.order_subscription_page.subscription_profile_pages.SubscriptionProfilePage;
import portal.admin.order_subscription_page.subscription_profile_pages.SubscriptionRequestPage;
import portal.annotations.Admin;
import portal.annotations.TestType;
import portal.data.LinkProvider;
import portal.data.PopUpMessage;
import portal.data.SubscriptionData;
import portal.data.enumdata.component.*;
import portal.listener.BaseTestExtension;
import portal.providers.DataProvider;
import portal.providers.MainPageProvider;
import portal.providers.SubscriptionPageFactory;
import portal.utils.DateTime;
import portal.utils.PopUp;

import java.util.Map;

import static portal.data.enumdata.SubscriptionIdName.*;
import static portal.data.enumdata.SubscriptionName.*;
import static portal.data.enumdata.TabName.REQUESTS;
import static portal.data.enumdata.component.BackupComponent.*;
import static portal.data.enumdata.component.CloudStorageComponent.STORAGE;
import static portal.data.enumdata.component.InternetComponent.INTERNET_IP;
import static portal.data.enumdata.component.InternetComponent.INTERNET_SPEED;
import static portal.data.enumdata.component.MicrosoftComponent.*;
import static portal.data.enumdata.component.OpenstackComponent.*;
import static portal.data.enumdata.component.VMwareComponent.*;

@Admin.editSubscription
@ExtendWith(BaseTestExtension.class)
public class AdminEditRequestSubscriptionTest {

    private final DataProvider dataProvider = new DataProvider();
    private final SubscriptionData subscriptionData = dataProvider.subscriptionData();
    private final LinkProvider linkProvider = dataProvider.linkProvider();
    private final PopUpMessage popUpMessage = dataProvider.popUpMessage();
    private final MainPageProvider mainPageProvider = new MainPageProvider();
    private final SubscriptionPageFactory subscriptionPageFactory = new SubscriptionPageFactory();
    private final OrdersSubscriptionsMainPage ordersSubscriptionsMainPage = new OrdersSubscriptionsMainPage();
    private final SubscriptionProfilePage subscriptionProfilePage = new SubscriptionProfilePage();
    private final HeaderSubscriptionRequestPage headerSubscriptionRequestPage = new HeaderSubscriptionRequestPage();
    private final DateTime dateTime = new DateTime();
    private final PopUp popUp = new PopUp();
    private final String date = dateTime.getTodayDate();
    private final Map<Enum, String> vmwareData = dataProvider.getComponentsValue(VMwareComponent.values());
    private final Map<Enum, String> openstackData = dataProvider.getComponentsValue(OpenstackComponent.values());
    private final Map<Enum, String> internetData = dataProvider.getComponentsValue(InternetComponent.values());
    private final Map<Enum, String> backupData = dataProvider.getComponentsValue(BackupComponent.values());
    private final Map<Enum, String> microsoftData = dataProvider.getComponentsValue(MicrosoftComponent.values());
    private String time;

    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Редактирование заявки VDC VMware защищенная")
    @TestType.integration
    void editRequestVMwareProtect() {
        time = dateTime.getNext2Minutes();
        mainPageProvider.adminLink(linkProvider.subscriptionEdit());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.vmwareProtectName());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage
                .clickEdit()
                .checkAndSetPaperOrder(VMWARE_PROTECT.getEditNote())
                .checkAndSetStartDate(date, time);
        SubscriptionRequestPage vMwareProtectRequestPage = subscriptionPageFactory.getRequestPage(PROTECT);
        vMwareProtectRequestPage
                .setValue(VCPU, vmwareData.get(VCPU))
                .setValue(VRAM, vmwareData.get(VRAM))
                .setValue(HDD_7K, vmwareData.get(HDD_7K))
                .setValue(HDD_10K, vmwareData.get(HDD_10K))
                .setValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .setValue(SSD, vmwareData.get(SSD))
                .setValue(SSD_AF, vmwareData.get(SSD_AF))
                .setValue(USB, vmwareData.get(USB));
        headerSubscriptionRequestPage.clickSave();
        popUp.checkMessageText(popUpMessage.orderChange());
        headerSubscriptionRequestPage
                .checkPaperOrder(VMWARE_PROTECT.getEditNote())
                .checkStartDate(date, time);
        vMwareProtectRequestPage
                .checkValue(VCPU, vmwareData.get(VCPU))
                .checkValue(VRAM, vmwareData.get(VRAM))
                .checkValue(HDD_7K, vmwareData.get(HDD_7K))
                .checkValue(HDD_10K, vmwareData.get(HDD_10K))
                .checkValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .checkValue(SSD, vmwareData.get(SSD))
                .checkValue(SSD_AF, vmwareData.get(SSD_AF))
                .checkValue(USB, vmwareData.get(USB));
    }

    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Редактирование заявки VDC VMware IX")
    @TestType.integration
    void editRequestVMwareIX() {
        time = dateTime.getNext2Minutes();
        mainPageProvider.adminLink(linkProvider.subscriptionEdit());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.vmwareIXName());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage
                .clickEdit()
                .checkAndSetPaperOrder(VMWARE_IX.getEditNote())
                .checkAndSetStartDate(date, time);
        SubscriptionRequestPage vMwareIXRequestPage = subscriptionPageFactory.getRequestPage(IX);
        vMwareIXRequestPage
                .setValue(VCPU, vmwareData.get(VCPU))
                .setValue(VRAM, vmwareData.get(VRAM))
                .setValue(HDD_7K, vmwareData.get(HDD_7K))
                .setValue(HDD_10K, vmwareData.get(HDD_10K))
                .setValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .setValue(SSD, vmwareData.get(SSD))
                .setValue(SSD_AF, vmwareData.get(SSD_AF))
                .setValue(USB, vmwareData.get(USB));
        headerSubscriptionRequestPage.clickSave();
        popUp.checkMessageText(popUpMessage.orderChange());
        headerSubscriptionRequestPage
                .checkPaperOrder(VMWARE_IX.getEditNote())
                .checkStartDate(date, time);
        vMwareIXRequestPage
                .checkValue(VCPU, vmwareData.get(VCPU))
                .checkValue(VRAM, vmwareData.get(VRAM))
                .checkValue(HDD_7K, vmwareData.get(HDD_7K))
                .checkValue(HDD_10K, vmwareData.get(HDD_10K))
                .checkValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .checkValue(SSD, vmwareData.get(SSD))
                .checkValue(SSD_AF, vmwareData.get(SSD_AF))
                .checkValue(USB, vmwareData.get(USB));
    }

    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Редактирование заявки VDC VMware 3 Ггц")
    @TestType.integration
    void editRequestVMware3HGz() {
        time = dateTime.getNext2Minutes();
        mainPageProvider.adminLink(linkProvider.subscriptionEdit());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.vmware3GHzName());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage
                .clickEdit()
                .checkAndSetPaperOrder(VMWARE_3GHZ.getEditNote())
                .checkAndSetStartDate(date, time);
        SubscriptionRequestPage vMware3GHzRequestPage = subscriptionPageFactory.getRequestPage(GHZ);
        vMware3GHzRequestPage
                .setValue(VCPU, vmwareData.get(VCPU))
                .setValue(VRAM, vmwareData.get(VRAM))
                .setValue(HDD_7K, vmwareData.get(HDD_7K))
                .setValue(HDD_10K, vmwareData.get(HDD_10K))
                .setValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .setValue(SSD, vmwareData.get(SSD))
                .setValue(SSD_AF, vmwareData.get(SSD_AF))
                .setValue(USB, vmwareData.get(USB));
        headerSubscriptionRequestPage.clickSave();
        popUp.checkMessageText(popUpMessage.orderChange());
        headerSubscriptionRequestPage
                .checkPaperOrder(VMWARE_3GHZ.getEditNote())
                .checkStartDate(date, time);
        vMware3GHzRequestPage.checkValue(VCPU, vmwareData.get(VCPU))
                .checkValue(VRAM, vmwareData.get(VRAM))
                .checkValue(HDD_7K, vmwareData.get(HDD_7K))
                .checkValue(HDD_10K, vmwareData.get(HDD_10K))
                .checkValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .checkValue(SSD, vmwareData.get(SSD))
                .checkValue(SSD_AF, vmwareData.get(SSD_AF))
                .checkValue(USB, vmwareData.get(USB));
    }

    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Редактирование заявки VDC VMware закрытая")
    @TestType.integration
    void editRequestVMwareClosed() {
        time = dateTime.getNext2Minutes();
        mainPageProvider.adminLink(linkProvider.subscriptionEdit());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.vmwareCloseName());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage
                .clickEdit()
                .checkAndSetPaperOrder(VMWARE_CLOSE.getEditNote())
                .checkAndSetStartDate(date, time);
        SubscriptionRequestPage vMwareCloseRequestPage = subscriptionPageFactory.getRequestPage(CLOSE);
        vMwareCloseRequestPage
                .setValue(VCPU, vmwareData.get(VCPU))
                .setValue(VRAM, vmwareData.get(VRAM))
                .setValue(HDD_7K, vmwareData.get(HDD_7K))
                .setValue(HDD_10K, vmwareData.get(HDD_10K))
                .setValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .setValue(SSD, vmwareData.get(SSD))
                .setValue(SSD_AF, vmwareData.get(SSD_AF));
        headerSubscriptionRequestPage.clickSave();
        popUp.checkMessageText(popUpMessage.orderChange());
        headerSubscriptionRequestPage
                .checkPaperOrder(VMWARE_CLOSE.getEditNote())
                .checkStartDate(date, time);
        vMwareCloseRequestPage
                .checkValue(VCPU, vmwareData.get(VCPU))
                .checkValue(VRAM, vmwareData.get(VRAM))
                .checkValue(HDD_7K, vmwareData.get(HDD_7K))
                .checkValue(HDD_10K, vmwareData.get(HDD_10K))
                .checkValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .checkValue(SSD, vmwareData.get(SSD))
                .checkValue(SSD_AF, vmwareData.get(SSD_AF));
    }

    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Редактирование заявки Openstack")
    @TestType.integration
    void editRequestOpenstack() {
        time = dateTime.getNext2Minutes();
        mainPageProvider.adminLink(linkProvider.subscriptionEdit());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.openstackName());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage
                .clickEdit()
                .checkAndSetPaperOrder(OPENSTACK.getEditNote())
                .checkAndSetStartDate(date, time);
        SubscriptionRequestPage openstackRequestPage = subscriptionPageFactory.getRequestPage(OPENSTACK_CSS);
        openstackRequestPage
                .setValue(OPENSTACK_VCPU, openstackData.get(OPENSTACK_VCPU))
                .setValue(OPENSTACK_VRAM, openstackData.get(OPENSTACK_VRAM))
                .setValue(OPENSTACK_HDD_7K, openstackData.get(OPENSTACK_HDD_7K))
                .setValue(OPENSTACK_HDD_10K, openstackData.get(OPENSTACK_HDD_10K))
                .setValue(OPENSTACK_SSD_SDS, openstackData.get(OPENSTACK_SSD_SDS));
        headerSubscriptionRequestPage.clickSave();
        popUp.checkMessageText(popUpMessage.orderChange());
        headerSubscriptionRequestPage
                .checkPaperOrder(OPENSTACK.getEditNote())
                .checkStartDate(date, time);
        openstackRequestPage
                .checkValue(OPENSTACK_VCPU, openstackData.get(OPENSTACK_VCPU))
                .checkValue(OPENSTACK_VRAM, openstackData.get(OPENSTACK_VRAM))
                .checkValue(OPENSTACK_HDD_7K, openstackData.get(OPENSTACK_HDD_7K))
                .checkValue(OPENSTACK_HDD_10K, openstackData.get(OPENSTACK_HDD_10K))
                .checkValue(OPENSTACK_SSD_SDS, openstackData.get(OPENSTACK_SSD_SDS));

    }

    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Редактирование заявки резервное копирование")
    @TestType.integration
    void editRequestBackup() {
        time = dateTime.getNext2Minutes();
        mainPageProvider.adminLink(linkProvider.subscriptionEdit());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.backupName());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage
                .clickEdit()
                .checkAndSetPaperOrder(BACKUP.getEditNote())
                .checkAndSetStartDate(date, time);
        SubscriptionRequestPage backupRequestPage = subscriptionPageFactory.getRequestPage(BACKUP_CSS);
        backupRequestPage
                .setValue(TRAFFIC_MIN, backupData.get(TRAFFIC_MIN))
                .setValue(VM_COUNT, backupData.get(VM_COUNT))
                .setValue(BACKUP_VOLUME, backupData.get(BACKUP_VOLUME))
                .clickCloud(CLOUD_PLACEMENT, subscriptionData.setEditCloud())
                .setValue(TRAFFIC_SPEED, backupData.get(TRAFFIC_SPEED));
        headerSubscriptionRequestPage.clickSave();
        popUp.checkMessageText(popUpMessage.orderChange());
        headerSubscriptionRequestPage
                .checkPaperOrder(BACKUP.getEditNote())
                .checkStartDate(date, time);
        backupRequestPage
                .checkValue(TRAFFIC_MIN, backupData.get(TRAFFIC_MIN))
                .checkValue(VM_COUNT, backupData.get(VM_COUNT))
                .checkValue(BACKUP_VOLUME, backupData.get(BACKUP_VOLUME))
                .checkCloud(CLOUD_PLACEMENT, subscriptionData.setEditCloud())
                .checkValue(TRAFFIC_SPEED, backupData.get(TRAFFIC_SPEED));
    }

    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Редактирование заявки резервированный доступ в интернет")
    @TestType.integration
    void editRequestInternet() {
        time = dateTime.getNext2Minutes();
        mainPageProvider.adminLink(linkProvider.subscriptionEdit());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.accessInternetName());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage
                .clickEdit()
                .checkAndSetPaperOrder(INTERNET.getEditNote())
                .checkAndSetStartDate(date, time);
        SubscriptionRequestPage internetRequestPage = subscriptionPageFactory.getRequestPage(INTERNET_CSS);
        internetRequestPage
                .setValue(INTERNET_SPEED, internetData.get(INTERNET_SPEED))
                .setValue(INTERNET_IP, internetData.get(INTERNET_IP));
        headerSubscriptionRequestPage.clickSave();
        popUp.checkMessageText(popUpMessage.orderChange());
        headerSubscriptionRequestPage
                .checkPaperOrder(INTERNET.getEditNote())
                .checkStartDate(date, time);
        internetRequestPage
                .checkValue(INTERNET_SPEED, internetData.get(INTERNET_SPEED))
                .checkValue(INTERNET_IP, internetData.get(INTERNET_IP));
    }

    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Редактирование заявки Microsoft")
    @TestType.integration
    void editRequestMicrosoft() {
        time = dateTime.getNext2Minutes();
        mainPageProvider.adminLink(linkProvider.subscriptionEdit());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.microsoftName());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage
                .clickEdit()
                .checkAndSetPaperOrder(MICROSOFT.getEditNote())
                .checkAndSetStartDate(date, time);
        SubscriptionRequestPage microsoftRequestPage = subscriptionPageFactory.getRequestPage(MICROSOFT_CSS);
        microsoftRequestPage
                .setValue(VISIO_PROFESSIONAL, microsoftData.get(VISIO_PROFESSIONAL))
                .setValue(SHAREPOINT_SERVER_STANDARD, microsoftData.get(SHAREPOINT_SERVER_STANDARD))
                .setValue(SERVER_SUITE_STANDARD, microsoftData.get(SERVER_SUITE_STANDARD))
                .setValue(SQL_SERVER_STANDARD_CORE, microsoftData.get(SQL_SERVER_STANDARD_CORE))
                .setValue(EXCHANGE_BASIC, microsoftData.get(EXCHANGE_BASIC))
                .setValue(EXCHANGE_ENTERPRISE_PLUS, microsoftData.get(EXCHANGE_ENTERPRISE_PLUS))
                .setValue(VISIO_STANDARD, microsoftData.get(VISIO_STANDARD))
                .setValue(PROJECT_PROFESSIONAL, microsoftData.get(PROJECT_PROFESSIONAL))
                .setValue(WINDOWS_SERVER_DATA_CENTER, microsoftData.get(WINDOWS_SERVER_DATA_CENTER))
                .setValue(SQL_SERVER_ENTERPRISE, microsoftData.get(SQL_SERVER_ENTERPRISE))
                .setValue(OFFICE_STANDARD, microsoftData.get(OFFICE_STANDARD))
                .setValue(EXCHANGE_ENTERPRISE, microsoftData.get(EXCHANGE_ENTERPRISE))
                .setValue(OFFICE_PROFESSIONAL_PLUS, microsoftData.get(OFFICE_PROFESSIONAL_PLUS))
                .setValue(PROJECT_SERVER, microsoftData.get(PROJECT_SERVER))
                .setValue(WINDOWS_SERVER_STANDARD, microsoftData.get(WINDOWS_SERVER_STANDARD))
                .setValue(REMOTE_DESKTOP_SERVICE, microsoftData.get(REMOTE_DESKTOP_SERVICE))
                .setValue(SQL_SERVER_STANDARD, microsoftData.get(SQL_SERVER_STANDARD))
                .setValue(EXCHANGE_STANDARD_PLUS, microsoftData.get(EXCHANGE_STANDARD_PLUS))
                .setValue(PROJECT, microsoftData.get(PROJECT))
                .setValue(SHAREPOINT_SERVER_PROFESSIONAL, microsoftData.get(SHAREPOINT_SERVER_PROFESSIONAL))
                .setValue(SERVER_SUITE_DATA_CENTER, microsoftData.get(SERVER_SUITE_DATA_CENTER))
                .setValue(SQL_SERVER_WEB, microsoftData.get(SQL_SERVER_WEB))
                .setValue(EXCHANGE_STANDARD, microsoftData.get(EXCHANGE_STANDARD))
                .setValue(WINDOWS_SERVER_STANDARD_VM, microsoftData.get(WINDOWS_SERVER_STANDARD_VM))
                .setValue(RIGHTS_MANAGEMENT_SERVICES, microsoftData.get(RIGHTS_MANAGEMENT_SERVICES))
                .setValue(OFFICE_MULTI_LANGUAGE, microsoftData.get(OFFICE_MULTI_LANGUAGE))
                .setValue(VISUAL_STUDIO, microsoftData.get(VISUAL_STUDIO))
                .setValue(VISUAL_STUDIO_BASIC, microsoftData.get(VISUAL_STUDIO_BASIC))
                .setValue(VISUAL_STUDIO_PROFESSIONAL, microsoftData.get(VISUAL_STUDIO_PROFESSIONAL))
                .setValue(VISUAL_STUDIO_TEST_PROFESSIONAL, microsoftData.get(VISUAL_STUDIO_TEST_PROFESSIONAL))
                .setValue(VISUAL_STUDIO_ENTERPRISE, microsoftData.get(VISUAL_STUDIO_ENTERPRISE))
                .setValue(SHAREPOINT_SERVER, microsoftData.get(SHAREPOINT_SERVER))
                .setValue(SHAREPOINT_HOSTING, microsoftData.get(SHAREPOINT_HOSTING));
        headerSubscriptionRequestPage.clickSave();
        popUp.checkMessageText(popUpMessage.orderChange());
        headerSubscriptionRequestPage
                .checkPaperOrder(MICROSOFT.getEditNote())
                .checkStartDate(date, time);
        microsoftRequestPage
                .checkValue(VISIO_PROFESSIONAL, microsoftData.get(VISIO_PROFESSIONAL))
                .checkValue(SHAREPOINT_SERVER_STANDARD, microsoftData.get(SHAREPOINT_SERVER_STANDARD))
                .checkValue(SERVER_SUITE_STANDARD, microsoftData.get(SERVER_SUITE_STANDARD))
                .checkValue(SQL_SERVER_STANDARD_CORE, microsoftData.get(SQL_SERVER_STANDARD_CORE))
                .checkValue(EXCHANGE_BASIC, microsoftData.get(EXCHANGE_BASIC))
                .checkValue(EXCHANGE_ENTERPRISE_PLUS, microsoftData.get(EXCHANGE_ENTERPRISE_PLUS))
                .checkValue(VISIO_STANDARD, microsoftData.get(VISIO_STANDARD))
                .checkValue(PROJECT_PROFESSIONAL, microsoftData.get(PROJECT_PROFESSIONAL))
                .checkValue(WINDOWS_SERVER_DATA_CENTER, microsoftData.get(WINDOWS_SERVER_DATA_CENTER))
                .checkValue(SQL_SERVER_ENTERPRISE, microsoftData.get(SQL_SERVER_ENTERPRISE))
                .checkValue(OFFICE_STANDARD, microsoftData.get(OFFICE_STANDARD))
                .checkValue(EXCHANGE_ENTERPRISE, microsoftData.get(EXCHANGE_ENTERPRISE))
                .checkValue(OFFICE_PROFESSIONAL_PLUS, microsoftData.get(OFFICE_PROFESSIONAL_PLUS))
                .checkValue(PROJECT_SERVER, microsoftData.get(PROJECT_SERVER))
                .checkValue(WINDOWS_SERVER_STANDARD, microsoftData.get(WINDOWS_SERVER_STANDARD))
                .checkValue(REMOTE_DESKTOP_SERVICE, microsoftData.get(REMOTE_DESKTOP_SERVICE))
                .checkValue(SQL_SERVER_STANDARD, microsoftData.get(SQL_SERVER_STANDARD))
                .checkValue(EXCHANGE_STANDARD_PLUS, microsoftData.get(EXCHANGE_STANDARD_PLUS))
                .checkValue(PROJECT, microsoftData.get(PROJECT))
                .checkValue(SHAREPOINT_SERVER_PROFESSIONAL, microsoftData.get(SHAREPOINT_SERVER_PROFESSIONAL))
                .checkValue(SERVER_SUITE_DATA_CENTER, microsoftData.get(SERVER_SUITE_DATA_CENTER))
                .checkValue(SQL_SERVER_WEB, microsoftData.get(SQL_SERVER_WEB))
                .checkValue(EXCHANGE_STANDARD, microsoftData.get(EXCHANGE_STANDARD))
                .checkValue(WINDOWS_SERVER_STANDARD_VM, microsoftData.get(WINDOWS_SERVER_STANDARD_VM))
                .checkValue(RIGHTS_MANAGEMENT_SERVICES, microsoftData.get(RIGHTS_MANAGEMENT_SERVICES))
                .checkValue(OFFICE_MULTI_LANGUAGE, microsoftData.get(OFFICE_MULTI_LANGUAGE))
                .checkValue(VISUAL_STUDIO, microsoftData.get(VISUAL_STUDIO))
                .checkValue(VISUAL_STUDIO_BASIC, microsoftData.get(VISUAL_STUDIO_BASIC))
                .checkValue(VISUAL_STUDIO_PROFESSIONAL, microsoftData.get(VISUAL_STUDIO_PROFESSIONAL))
                .checkValue(VISUAL_STUDIO_TEST_PROFESSIONAL, microsoftData.get(VISUAL_STUDIO_TEST_PROFESSIONAL))
                .checkValue(VISUAL_STUDIO_ENTERPRISE, microsoftData.get(VISUAL_STUDIO_ENTERPRISE))
                .checkValue(SHAREPOINT_SERVER, microsoftData.get(SHAREPOINT_SERVER))
                .checkValue(SHAREPOINT_HOSTING, microsoftData.get(SHAREPOINT_HOSTING));
    }

    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Редактирование заявки облачное хранилище")
    @TestType.integration
    void editRequestCloudStorage() {
        time = dateTime.getNext2Minutes();
        mainPageProvider.adminLink(linkProvider.subscriptionEdit());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.cloudStorageName());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage
                .clickEdit()
                .checkAndSetPaperOrder(CLOUD_STORAGE.getEditNote())
                .checkAndSetStartDate(date, time);
        SubscriptionRequestPage cloudStorageRequestPage = subscriptionPageFactory.getRequestPage(CLOUD_STORAGE_CSS);
        cloudStorageRequestPage.clickCloud(STORAGE, subscriptionData.tariffGeneral());
        headerSubscriptionRequestPage.clickSave();
        popUp.checkMessageText(popUpMessage.orderChange());
        headerSubscriptionRequestPage
                .checkPaperOrder(CLOUD_STORAGE.getEditNote())
                .checkStartDate(date, time);
        cloudStorageRequestPage.checkCloud(STORAGE, subscriptionData.tariffGeneral());
    }
}
