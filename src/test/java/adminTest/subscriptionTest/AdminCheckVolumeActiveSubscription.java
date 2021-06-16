package adminTest.subscriptionTest;

import org.junit.jupiter.api.Disabled;
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
import portal.listener.BaseTestExtension;
import portal.providers.DataProvider;
import portal.providers.MainPageProvider;
import portal.providers.SubscriptionPageFactory;
import portal.utils.DateTime;
import portal.utils.PopUp;
import portal.utils.PopUpFull;
import portal.utils.Status;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.refresh;
import static portal.data.enumdata.ButtonName.SAVE;
import static portal.data.enumdata.Status.DONE;
import static portal.data.enumdata.SubscriptionIdName.*;
import static portal.data.enumdata.TabName.REQUESTS;
import static portal.data.enumdata.TabName.VOLUME;
import static portal.data.enumdata.component.InternetComponent.INTERNET_IP;
import static portal.data.enumdata.component.InternetComponent.INTERNET_SPEED;
import static portal.data.enumdata.component.MicrosoftComponent.*;
import static portal.data.enumdata.component.OpenstackComponent.*;
import static portal.data.enumdata.component.VMwareComponent.*;


//TODO не забыть про тестовые подписки
@Admin.checkVolumeSubscription
@ExtendWith(BaseTestExtension.class)
public class AdminCheckVolumeActiveSubscription {
    private final MainPageProvider mainPageProvider = new MainPageProvider();
    private final DataProvider dataProvider = new DataProvider();
    private final LinkProvider linkProvider = dataProvider.linkProvider();
    private final SubscriptionData subscriptionData = dataProvider.subscriptionData();
    private final PopUpMessage popUpMessage = dataProvider.popUpMessage();
    private final OrdersSubscriptionsMainPage ordersSubscriptionsMainPage = new OrdersSubscriptionsMainPage();
    private final SubscriptionProfilePage subscriptionProfilePage = new SubscriptionProfilePage();
    private final SubscriptionPageFactory subscriptionPageFactory = new SubscriptionPageFactory();
    private final HeaderSubscriptionRequestPage headerSubscriptionRequestPage = new HeaderSubscriptionRequestPage();
    private final PopUpFull popUpFull = new PopUpFull();
    private final PopUp popUp = new PopUp();
    private final DateTime dateTime = new DateTime();
    private final Map<Enum, String> data = new HashMap<>();
    private final String date = dateTime.getTodayDate();
    private String time;
    private final Status status = new Status();

    @TestType.e2e
    @DisplayName("Проверка выставления объемов для активной VMware защищенная")
    void checkVolumeActiveVMwareProtect() {
        mainPageProvider.adminLink(linkProvider.approveActiveSubscription());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.vmwareProtectName());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        SubscriptionRequestPage subscriptionRequestPage = subscriptionPageFactory.getRequestPage(PROTECT);
        subscriptionRequestPage
                .getVolume(VCPU, data)
                .getVolume(VRAM, data)
                .getVolume(HDD_7K, data)
                .getVolume(HDD_10K, data)
                .getVolume(HDD_HYBRID, data)
                .getVolume(SSD, data)
                .getVolume(SSD_AF, data)
                .getVolume(USB, data);
        headerSubscriptionRequestPage.clickLaunchDate();
        time = dateTime.getTimeToExecutionDate();
        popUpFull
                .setDate(date)
                .setTime(time)
                .clickButtonByName(SAVE);
        popUp.checkMessageText(popUpMessage.dataChanged());
        headerSubscriptionRequestPage.clickStatus();
        popUpFull
                .changeRequestStatus(DONE.getValue())
                .clickButtonByName(SAVE);
        popUp.checkMessageText(popUpMessage.requestChanged());
        dateTime.waitLaunchTime(time);
        refresh();
        subscriptionProfilePage.clickOnMenu(VOLUME.getValue());
        subscriptionRequestPage
                .checkVolume(VCPU, data)
                .checkVolume(VRAM, data)
                .checkVolume(HDD_7K, data)
                .checkVolume(HDD_10K, data)
                .checkVolume(HDD_HYBRID, data)
                .checkVolume(SSD, data)
                .checkVolume(SSD_AF, data)
                .checkVolume(USB, data);
    }

    @TestType.e2e
    @DisplayName("Проверка выставления объемов для активной IX VMware защищенная")
    void checkVolumeActiveVMwareIX() {
        mainPageProvider.adminLink(linkProvider.approveActiveSubscription());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.vmwareIXName());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        SubscriptionRequestPage subscriptionRequestPage = subscriptionPageFactory.getRequestPage(IX);
        subscriptionRequestPage
                .getVolume(VCPU, data)
                .getVolume(VRAM, data)
                .getVolume(HDD_7K, data)
                .getVolume(HDD_10K, data)
                .getVolume(HDD_HYBRID, data)
                .getVolume(SSD, data)
                .getVolume(SSD_AF, data)
                .getVolume(USB, data);
        headerSubscriptionRequestPage.clickLaunchDate();
        time = dateTime.getTimeToExecutionDate();
        popUpFull
                .setDate(date)
                .setTime(time)
                .clickButtonByName(SAVE);
        popUp.checkMessageText(popUpMessage.dataChanged());
        headerSubscriptionRequestPage.clickStatus();
        popUpFull
                .changeRequestStatus(DONE.getValue())
                .clickButtonByName(SAVE);
        popUp.checkMessageText(popUpMessage.requestChanged());
        dateTime.waitLaunchTime(time);
        refresh();
        subscriptionProfilePage.clickOnMenu(VOLUME.getValue());
        subscriptionRequestPage
                .checkVolume(VCPU, data)
                .checkVolume(VRAM, data)
                .checkVolume(HDD_7K, data)
                .checkVolume(HDD_10K, data)
                .checkVolume(HDD_HYBRID, data)
                .checkVolume(SSD, data)
                .checkVolume(SSD_AF, data)
                .checkVolume(USB, data);
    }

    @TestType.e2e
    @DisplayName("Проверка выставления объемов для активной подписки Openstack")
    void checkVolumeActiveOpenstack() {
        mainPageProvider.adminLink(linkProvider.approveActiveSubscription());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.openstackName());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        SubscriptionRequestPage subscriptionRequestPage = subscriptionPageFactory.getRequestPage(OPENSTACK_CSS);
        subscriptionRequestPage
                .getVolume(OPENSTACK_VCPU, data)
                .getVolume(OPENSTACK_VRAM, data)
                .getVolume(OPENSTACK_HDD_7K, data)
                .getVolume(OPENSTACK_HDD_10K, data)
                .getVolume(OPENSTACK_SSD_SDS, data);
        headerSubscriptionRequestPage.clickLaunchDate();
        time = dateTime.getTimeToExecutionDate();
        popUpFull
                .setDate(date)
                .setTime(time)
                .clickButtonByName(SAVE);
        popUp.checkMessageText(popUpMessage.dataChanged());
        headerSubscriptionRequestPage.clickStatus();
        popUpFull
                .changeRequestStatus(DONE.getValue())
                .clickButtonByName(SAVE);
        dateTime.waitLaunchTime(time);
        refresh();
        subscriptionProfilePage.clickOnMenu(VOLUME.getValue());
        subscriptionRequestPage
                .checkVolume(OPENSTACK_VCPU, data)
                .checkVolume(OPENSTACK_VRAM, data)
                .checkVolume(OPENSTACK_HDD_7K, data)
                .checkVolume(OPENSTACK_HDD_10K, data)
                .checkVolume(OPENSTACK_SSD_SDS, data);
    }

    @TestType.e2e
    @DisplayName("Проверка выставления объемов для активной подписки Microsoft")
    void checkVolumeActiveMicrosoft() {
        mainPageProvider.adminLink(linkProvider.approveActiveSubscription());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.microsoftName());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        SubscriptionRequestPage subscriptionRequestPage = subscriptionPageFactory.getRequestPage(MICROSOFT_CSS);
        subscriptionRequestPage
                .getVolume(VISIO_PROFESSIONAL,data)
                .getVolume(SHAREPOINT_SERVER_STANDARD, data)
                .getVolume(SERVER_SUITE_STANDARD, data)
                .getVolume(SQL_SERVER_STANDARD_CORE, data)
                .getVolume(EXCHANGE_BASIC, data)
                .getVolume(EXCHANGE_ENTERPRISE_PLUS, data)
                .getVolume(VISIO_STANDARD, data)
                .getVolume(PROJECT_PROFESSIONAL, data)
                .getVolume(WINDOWS_SERVER_DATA_CENTER, data)
                .getVolume(SQL_SERVER_ENTERPRISE, data)
                .getVolume(OFFICE_STANDARD, data)
                .getVolume(EXCHANGE_ENTERPRISE, data)
                .getVolume(OFFICE_PROFESSIONAL_PLUS, data)
                .getVolume(PROJECT_SERVER, data)
                .getVolume(WINDOWS_SERVER_STANDARD, data)
                .getVolume(REMOTE_DESKTOP_SERVICE, data)
                .getVolume(SQL_SERVER_STANDARD, data)
                .getVolume(EXCHANGE_STANDARD_PLUS, data)
                .getVolume(PROJECT, data)
                .getVolume(SHAREPOINT_SERVER_PROFESSIONAL, data)
                .getVolume(SERVER_SUITE_DATA_CENTER, data)
                .getVolume(SQL_SERVER_WEB, data)
                .getVolume(EXCHANGE_STANDARD, data)
                .getVolume(WINDOWS_SERVER_STANDARD_VM, data)
                .getVolume(RIGHTS_MANAGEMENT_SERVICES, data)
                .getVolume(OFFICE_MULTI_LANGUAGE, data)
                .getVolume(VISUAL_STUDIO, data)
                .getVolume(VISUAL_STUDIO_BASIC, data)
                .getVolume(VISUAL_STUDIO_PROFESSIONAL, data)
                .getVolume(VISUAL_STUDIO_TEST_PROFESSIONAL, data)
                .getVolume(VISUAL_STUDIO_ENTERPRISE, data)
                .getVolume(SHAREPOINT_SERVER, data)
                .getVolume(SHAREPOINT_HOSTING, data);
        headerSubscriptionRequestPage.clickLaunchDate();
        time = dateTime.getTimeToExecutionDate();
        popUpFull
                .setDate(date)
                .setTime(time)
                .clickButtonByName(SAVE);
        popUp.checkMessageText(popUpMessage.dataChanged());
        headerSubscriptionRequestPage.clickStatus();
        popUpFull
                .changeRequestStatus(DONE.getValue())
                .clickButtonByName(SAVE);
        dateTime.waitLaunchTime(time);
        refresh();
        subscriptionProfilePage.clickOnMenu(VOLUME.getValue());
        subscriptionRequestPage
                .checkVolume(VISIO_PROFESSIONAL,data)
                .checkVolume(SHAREPOINT_SERVER_STANDARD, data)
                .checkVolume(SERVER_SUITE_STANDARD, data)
                .checkVolume(SQL_SERVER_STANDARD_CORE, data)
                .checkVolume(EXCHANGE_BASIC, data)
                .checkVolume(EXCHANGE_ENTERPRISE_PLUS, data)
                .checkVolume(VISIO_STANDARD, data)
                .checkVolume(PROJECT_PROFESSIONAL, data)
                .checkVolume(WINDOWS_SERVER_DATA_CENTER, data)
                .checkVolume(SQL_SERVER_ENTERPRISE, data)
                .checkVolume(OFFICE_STANDARD, data)
                .checkVolume(EXCHANGE_ENTERPRISE, data)
                .checkVolume(OFFICE_PROFESSIONAL_PLUS, data)
                .checkVolume(PROJECT_SERVER, data)
                .checkVolume(WINDOWS_SERVER_STANDARD, data)
                .checkVolume(REMOTE_DESKTOP_SERVICE, data)
                .checkVolume(SQL_SERVER_STANDARD, data)
                .checkVolume(EXCHANGE_STANDARD_PLUS, data)
                .checkVolume(PROJECT, data)
                .checkVolume(SHAREPOINT_SERVER_PROFESSIONAL, data)
                .checkVolume(SERVER_SUITE_DATA_CENTER, data)
                .checkVolume(SQL_SERVER_WEB, data)
                .checkVolume(EXCHANGE_STANDARD, data)
                .checkVolume(WINDOWS_SERVER_STANDARD_VM, data)
                .checkVolume(RIGHTS_MANAGEMENT_SERVICES, data)
                .checkVolume(OFFICE_MULTI_LANGUAGE, data)
                .checkVolume(VISUAL_STUDIO, data)
                .checkVolume(VISUAL_STUDIO_BASIC, data)
                .checkVolume(VISUAL_STUDIO_PROFESSIONAL, data)
                .checkVolume(VISUAL_STUDIO_TEST_PROFESSIONAL, data)
                .checkVolume(VISUAL_STUDIO_ENTERPRISE, data)
                .checkVolume(SHAREPOINT_SERVER, data)
                .checkVolume(SHAREPOINT_HOSTING, data);
    }

    @Disabled("Не закончен, баг на вкладке объемы")
    @TestType.e2e
    @DisplayName("Проверка выставления объемов для активной подписки доступ в Internet")
    void checkVolumeActiveInternet() {
        mainPageProvider.adminLink(linkProvider.approveActiveSubscription());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.accessInternetName());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        SubscriptionRequestPage subscriptionRequestPage = subscriptionPageFactory.getRequestPage(INTERNET_CSS);
        subscriptionRequestPage
                .getVolume(INTERNET_IP, data)
                .getVolume(INTERNET_SPEED, data);
        headerSubscriptionRequestPage.clickLaunchDate();
        time = dateTime.getTimeToExecutionDate();
        popUpFull
                .setDate(date)
                .setTime(time)
                .clickButtonByName(SAVE);
        popUp.checkMessageText(popUpMessage.dataChanged());
        headerSubscriptionRequestPage.clickStatus();
        popUpFull
                .changeRequestStatus(DONE.getValue())
                .clickButtonByName(SAVE);
        dateTime.waitLaunchTime(time);
        refresh();
        subscriptionProfilePage.clickOnMenu(VOLUME.getValue());
        subscriptionRequestPage
                .checkVolume(INTERNET_IP, data)
                .checkVolumeInternetSpeed(INTERNET_SPEED, data);
    }

    @TestType.e2e
    @DisplayName("Проверка выставления объемов для активной тестовой VMware защищенная")
    void checkVolumeActiveTestVMwareProtect() {
        mainPageProvider.adminLink(linkProvider.approveActiveTestSubscription());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.vmwareProtectName());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        SubscriptionRequestPage subscriptionRequestPage = subscriptionPageFactory.getRequestPage(PROTECT);
        subscriptionRequestPage
                .getVolume(VCPU, data)
                .getVolume(VRAM, data)
                .getVolume(HDD_7K, data)
                .getVolume(HDD_10K, data)
                .getVolume(HDD_HYBRID, data)
                .getVolume(SSD, data)
                .getVolume(SSD_AF, data)
                .getVolume(USB, data);
        headerSubscriptionRequestPage.clickStatus();
        popUpFull
                .changeRequestStatus(DONE.getValue())
                .clickButtonByName(SAVE);
        popUp.checkMessageText(popUpMessage.requestChanged());
        dateTime.waitLaunchTime(time);
        refresh();
        subscriptionProfilePage.clickOnMenu(VOLUME.getValue());
        subscriptionRequestPage
                .checkVolume(VCPU, data)
                .checkVolume(VRAM, data)
                .checkVolume(HDD_7K, data)
                .checkVolume(HDD_10K, data)
                .checkVolume(HDD_HYBRID, data)
                .checkVolume(SSD, data)
                .checkVolume(SSD_AF, data)
                .checkVolume(USB, data);
    }

    @TestType.e2e
    @DisplayName("Проверка выставления объемов для активной тестовой подписки IX VMware защищенная")
    void checkVolumeActiveTestVMwareIX() {
        mainPageProvider.adminLink(linkProvider.approveActiveTestSubscription());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.vmwareIXName());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        SubscriptionRequestPage subscriptionRequestPage = subscriptionPageFactory.getRequestPage(IX);
        subscriptionRequestPage
                .getVolume(VCPU, data)
                .getVolume(VRAM, data)
                .getVolume(HDD_7K, data)
                .getVolume(HDD_10K, data)
                .getVolume(HDD_HYBRID, data)
                .getVolume(SSD, data)
                .getVolume(SSD_AF, data)
                .getVolume(USB, data);
        headerSubscriptionRequestPage.clickStatus();
        popUpFull
                .changeRequestStatus(DONE.getValue())
                .clickButtonByName(SAVE);
        popUp.checkMessageText(popUpMessage.requestChanged());
        dateTime.waitLaunchTime(time);
        refresh();
        subscriptionProfilePage.clickOnMenu(VOLUME.getValue());
        subscriptionRequestPage
                .checkVolume(VCPU, data)
                .checkVolume(VRAM, data)
                .checkVolume(HDD_7K, data)
                .checkVolume(HDD_10K, data)
                .checkVolume(HDD_HYBRID, data)
                .checkVolume(SSD, data)
                .checkVolume(SSD_AF, data)
                .checkVolume(USB, data);
    }

    @TestType.e2e
    @DisplayName("Проверка выставления объемов для активной тестовой подписки Openstack")
    void checkVolumeActiveTestOpenstack() {
        mainPageProvider.adminLink(linkProvider.approveActiveTestSubscription());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.openstackName());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        SubscriptionRequestPage subscriptionRequestPage = subscriptionPageFactory.getRequestPage(OPENSTACK_CSS);
        subscriptionRequestPage
                .getVolume(OPENSTACK_VCPU, data)
                .getVolume(OPENSTACK_VRAM, data)
                .getVolume(OPENSTACK_HDD_7K, data)
                .getVolume(OPENSTACK_HDD_10K, data)
                .getVolume(OPENSTACK_SSD_SDS, data);
        headerSubscriptionRequestPage.clickStatus();
        popUpFull
                .changeRequestStatus(DONE.getValue())
                .clickButtonByName(SAVE);
        dateTime.waitLaunchTime(time);
        refresh();
        subscriptionProfilePage.clickOnMenu(VOLUME.getValue());
        subscriptionRequestPage
                .checkVolume(OPENSTACK_VCPU, data)
                .checkVolume(OPENSTACK_VRAM, data)
                .checkVolume(OPENSTACK_HDD_7K, data)
                .checkVolume(OPENSTACK_HDD_10K, data)
                .checkVolume(OPENSTACK_SSD_SDS, data);
    }

    @TestType.e2e
    @DisplayName("Проверка выставления объемов для активной тестовой подписки Microsoft")
    void checkVolumeActiveTestMicrosoft() {
        mainPageProvider.adminLink(linkProvider.approveActiveTestSubscription());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.microsoftName());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        SubscriptionRequestPage subscriptionRequestPage = subscriptionPageFactory.getRequestPage(MICROSOFT_CSS);
        subscriptionRequestPage
                .getVolume(VISIO_PROFESSIONAL,data)
                .getVolume(SHAREPOINT_SERVER_STANDARD, data)
                .getVolume(SERVER_SUITE_STANDARD, data)
                .getVolume(SQL_SERVER_STANDARD_CORE, data)
                .getVolume(EXCHANGE_BASIC, data)
                .getVolume(EXCHANGE_ENTERPRISE_PLUS, data)
                .getVolume(VISIO_STANDARD, data)
                .getVolume(PROJECT_PROFESSIONAL, data)
                .getVolume(WINDOWS_SERVER_DATA_CENTER, data)
                .getVolume(SQL_SERVER_ENTERPRISE, data)
                .getVolume(OFFICE_STANDARD, data)
                .getVolume(EXCHANGE_ENTERPRISE, data)
                .getVolume(OFFICE_PROFESSIONAL_PLUS, data)
                .getVolume(PROJECT_SERVER, data)
                .getVolume(WINDOWS_SERVER_STANDARD, data)
                .getVolume(REMOTE_DESKTOP_SERVICE, data)
                .getVolume(SQL_SERVER_STANDARD, data)
                .getVolume(EXCHANGE_STANDARD_PLUS, data)
                .getVolume(PROJECT, data)
                .getVolume(SHAREPOINT_SERVER_PROFESSIONAL, data)
                .getVolume(SERVER_SUITE_DATA_CENTER, data)
                .getVolume(SQL_SERVER_WEB, data)
                .getVolume(EXCHANGE_STANDARD, data)
                .getVolume(WINDOWS_SERVER_STANDARD_VM, data)
                .getVolume(RIGHTS_MANAGEMENT_SERVICES, data)
                .getVolume(OFFICE_MULTI_LANGUAGE, data)
                .getVolume(VISUAL_STUDIO, data)
                .getVolume(VISUAL_STUDIO_BASIC, data)
                .getVolume(VISUAL_STUDIO_PROFESSIONAL, data)
                .getVolume(VISUAL_STUDIO_TEST_PROFESSIONAL, data)
                .getVolume(VISUAL_STUDIO_ENTERPRISE, data)
                .getVolume(SHAREPOINT_SERVER, data)
                .getVolume(SHAREPOINT_HOSTING, data);
        headerSubscriptionRequestPage.clickStatus();
        popUpFull
                .changeRequestStatus(DONE.getValue())
                .clickButtonByName(SAVE);
        dateTime.waitLaunchTime(time);
        refresh();
        subscriptionProfilePage.clickOnMenu(VOLUME.getValue());
        subscriptionRequestPage
                .checkVolume(VISIO_PROFESSIONAL,data)
                .checkVolume(SHAREPOINT_SERVER_STANDARD, data)
                .checkVolume(SERVER_SUITE_STANDARD, data)
                .checkVolume(SQL_SERVER_STANDARD_CORE, data)
                .checkVolume(EXCHANGE_BASIC, data)
                .checkVolume(EXCHANGE_ENTERPRISE_PLUS, data)
                .checkVolume(VISIO_STANDARD, data)
                .checkVolume(PROJECT_PROFESSIONAL, data)
                .checkVolume(WINDOWS_SERVER_DATA_CENTER, data)
                .checkVolume(SQL_SERVER_ENTERPRISE, data)
                .checkVolume(OFFICE_STANDARD, data)
                .checkVolume(EXCHANGE_ENTERPRISE, data)
                .checkVolume(OFFICE_PROFESSIONAL_PLUS, data)
                .checkVolume(PROJECT_SERVER, data)
                .checkVolume(WINDOWS_SERVER_STANDARD, data)
                .checkVolume(REMOTE_DESKTOP_SERVICE, data)
                .checkVolume(SQL_SERVER_STANDARD, data)
                .checkVolume(EXCHANGE_STANDARD_PLUS, data)
                .checkVolume(PROJECT, data)
                .checkVolume(SHAREPOINT_SERVER_PROFESSIONAL, data)
                .checkVolume(SERVER_SUITE_DATA_CENTER, data)
                .checkVolume(SQL_SERVER_WEB, data)
                .checkVolume(EXCHANGE_STANDARD, data)
                .checkVolume(WINDOWS_SERVER_STANDARD_VM, data)
                .checkVolume(RIGHTS_MANAGEMENT_SERVICES, data)
                .checkVolume(OFFICE_MULTI_LANGUAGE, data)
                .checkVolume(VISUAL_STUDIO, data)
                .checkVolume(VISUAL_STUDIO_BASIC, data)
                .checkVolume(VISUAL_STUDIO_PROFESSIONAL, data)
                .checkVolume(VISUAL_STUDIO_TEST_PROFESSIONAL, data)
                .checkVolume(VISUAL_STUDIO_ENTERPRISE, data)
                .checkVolume(SHAREPOINT_SERVER, data)
                .checkVolume(SHAREPOINT_HOSTING, data);
    }

    @Disabled("Не закончен, баг на вкладке объемы")
    @TestType.e2e
    @DisplayName("Проверка выставления объемов для новой доступ в Internet")
    void checkVolumeActiveTestInternet() {
        mainPageProvider.adminLink(linkProvider.approveActiveTestSubscription());
        ordersSubscriptionsMainPage.clickSubscriptionByName(subscriptionData.accessInternetName());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        SubscriptionRequestPage subscriptionRequestPage = subscriptionPageFactory.getRequestPage(INTERNET_CSS);
        subscriptionRequestPage
                .getVolume(INTERNET_IP, data)
                .getVolume(INTERNET_SPEED, data);
        headerSubscriptionRequestPage.clickStatus();
        popUpFull
                .changeRequestStatus(DONE.getValue())
                .clickButtonByName(SAVE);
        dateTime.waitLaunchTime(time);
        refresh();
        subscriptionProfilePage.clickOnMenu(VOLUME.getValue());
        subscriptionRequestPage
                .checkVolume(INTERNET_IP, data)
                .checkVolumeInternetSpeed(INTERNET_SPEED, data);
    }
}
