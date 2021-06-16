package customerTest;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import portal.admin.order_subscription_page.subscription_profile_pages.SubscriptionRequestPage;
import portal.annotations.LK;
import portal.annotations.TestType;
import portal.config.WebConfig;
import portal.data.*;
import portal.data.enumdata.TypeUser;
import portal.data.enumdata.component.*;
import portal.email.EmailSteps;
import portal.listener.BaseTestExtension;
import portal.lk.CatalogPage;
import portal.lk.MyServicesPage;
import portal.lk.NewSubscriptionOrderPage;
import portal.lk.NewSubscriptionRequestPage;
import portal.providers.DataProvider;
import portal.providers.MainPageProvider;
import portal.providers.SubscriptionPageFactory;
import portal.utils.PopUp;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static portal.data.enumdata.SubscriptionIdName.*;
import static portal.data.enumdata.SubscriptionName.*;
import static portal.data.enumdata.TypeSubscription.COMMERCIAL;
import static portal.data.enumdata.TypeSubscription.TESTING;
import static portal.data.enumdata.component.BackupComponent.*;
import static portal.data.enumdata.component.CloudStorageComponent.STORAGE;
import static portal.data.enumdata.component.InternetComponent.INTERNET_IP;
import static portal.data.enumdata.component.InternetComponent.INTERNET_SPEED;
import static portal.data.enumdata.component.MicrosoftComponent.*;
import static portal.data.enumdata.component.OpenstackComponent.*;
import static portal.data.enumdata.component.VMwareComponent.*;

@LK.createSubscription
@ExtendWith(BaseTestExtension.class)
public class CustomerNewSubscriptionTest {

    private static final String VALUE_CPU = "8";
    private static final String VALUE_RAM = "15";
    private static final String VALUE_HDD7K = "50";
    private static final String VALUE_INET = "100";
    private static final String VALUE_IP = "1";
    private static final String VALUE_ZERO = "0";

    private final PopUp popUp = new PopUp();
    private final CatalogPage catalogPage = new CatalogPage();
    private final MyServicesPage myServicesPage = new MyServicesPage();
    private final SubscriptionPageFactory subscriptionPageFactory = new SubscriptionPageFactory();
    private final NewSubscriptionOrderPage vmwareProtectOrder = subscriptionPageFactory.getCustomerOrderPage(PROTECT);
    private final NewSubscriptionOrderPage vmware3GHzOrder = subscriptionPageFactory.getCustomerOrderPage(GHZ);
    private final NewSubscriptionOrderPage vmwareIXOrder = subscriptionPageFactory.getCustomerOrderPage(IX);
    private final NewSubscriptionOrderPage vmwareCloseOrder = subscriptionPageFactory.getCustomerOrderPage(CLOSE);
    private final NewSubscriptionOrderPage openstackOrder = subscriptionPageFactory.getCustomerOrderPage(OPENSTACK_CSS);
    private final NewSubscriptionOrderPage cloudStorage = subscriptionPageFactory.getCustomerOrderPage(CLOUD_STORAGE_CSS);
    private final NewSubscriptionOrderPage internet = subscriptionPageFactory.getCustomerOrderPage(INTERNET_CSS);
    private final NewSubscriptionOrderPage backup = subscriptionPageFactory.getCustomerOrderPage(BACKUP_CSS);
    private final NewSubscriptionOrderPage microsoft = subscriptionPageFactory.getCustomerOrderPage(MICROSOFT_CSS);
    private final NewSubscriptionRequestPage newSubscriptionRequestPage = new NewSubscriptionRequestPage();
    private final DataProvider dataProvider = new DataProvider();
    private final PopUpMessage popUpMessage = dataProvider.popUpMessage();
    private final SubscriptionData subscriptionData = dataProvider.subscriptionData();
    private final LinkProvider linkProvider = dataProvider.linkProvider();

    private final UserData userData = dataProvider.userData();
    private final EmailMessage emailMessage = dataProvider.emailMessage();
    private final MainPageProvider mainPageProvider = new MainPageProvider(TypeUser.CUSTOMER_NEW_SUBSCRIPTION);
    private final MainPageProvider mainPageProviderEmail = new MainPageProvider(TypeUser.CUSTOMER_NEW_SUBSCRIPTION_EMAIL);

    private final MainPageProvider adminPageProvider = new MainPageProvider(TypeUser.USER_ADMIN);
    private final SubscriptionRequestPage vmwareProtectRequestPage = subscriptionPageFactory.getRequestPage(PROTECT);
    private final SubscriptionRequestPage vmware3GHzRequestPage = subscriptionPageFactory.getRequestPage(GHZ);
    private final SubscriptionRequestPage vmwareIXRequestPage = subscriptionPageFactory.getRequestPage(IX);
    private final SubscriptionRequestPage vmwareCloseRequestPage = subscriptionPageFactory.getRequestPage(CLOSE);
    private final SubscriptionRequestPage openstackRequestPage = subscriptionPageFactory.getRequestPage(OPENSTACK_CSS);
    private final SubscriptionRequestPage internetRequestPage = subscriptionPageFactory.getRequestPage(INTERNET_CSS);
    private final SubscriptionRequestPage backupRequestPage = subscriptionPageFactory.getRequestPage(BACKUP_CSS);
    private final SubscriptionRequestPage cloudStorageRequestPage = subscriptionPageFactory.getRequestPage(CLOUD_STORAGE_CSS);
    private final EmailSteps emailSteps = new EmailSteps();
    private final WebConfig webConfig = new WebConfig();
    private final String myServiceLink = webConfig.getUrlLk() + linkProvider.myServiceNewSubscriptionsLinkLK();

    private final Map<Enum, String> vmwareData = dataProvider.getComponentsValue(VMwareComponent.values());
    private final Map<Enum, String> openstackData = dataProvider.getComponentsValue(OpenstackComponent.values());
    private final Map<Enum, String> internetData = dataProvider.getComponentsValue(InternetComponent.values());
    private final Map<Enum, String> backupData = dataProvider.getComponentsValue(BackupComponent.values());
    private final Map<Enum, String> microsoftData = dataProvider.getComponentsValue(MicrosoftComponent.values());


    @Severity(value = SeverityLevel.CRITICAL)
    @TestType.e2e
    @DisplayName("Заказ VMware защищенная коммерческая")
    void createProtectVMwareCommercial() {
        mainPageProvider.openLkLink(linkProvider.catalogLinkLK());
        catalogPage.clickOrderSubscription(subscriptionData.vmwareProtectName());
        vmwareProtectOrder
                .clickOrderType(COMMERCIAL)
                .setTariff(subscriptionData.tariffFact())
                .setValue(VCPU, vmwareData.get(VCPU))
                .setValue(VRAM, vmwareData.get(VRAM))
                .setValue(HDD_7K, vmwareData.get(HDD_7K))
                .setValue(HDD_10K, vmwareData.get(HDD_10K))
                .setValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .setValue(SSD, vmwareData.get(SSD))
                .setValue(SSD_AF, vmwareData.get(SSD_AF))
                .setValue(USB, vmwareData.get(USB))
                .setValue(NOTE, VMWARE_PROTECT.getCommercialNote())
                .checkBox(subscriptionData.checkBoxInet())
                .checkBox(subscriptionData.checkBoxBackup())
                .clickOrder();
        popUp.checkMessageText(popUpMessage.orderCreate());
        open(myServiceLink);
        //*******************Проверяем заказанную WMWare******************
        myServicesPage.clickOnCommercialSubscription(subscriptionData.vmwareProtectShortName());
        var vmwareLink = newSubscriptionRequestPage.getOrderAdminLink();
        adminPageProvider.adminLink(vmwareLink);
        vmwareProtectRequestPage
                .checkValue(VCPU, vmwareData.get(VCPU))
                .checkValue(VRAM, vmwareData.get(VRAM))
                .checkValue(HDD_7K, vmwareData.get(HDD_7K))
                .checkValue(HDD_10K, vmwareData.get(HDD_10K))
                .checkValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .checkValue(SSD, vmwareData.get(SSD))
                .checkValue(SSD_AF, vmwareData.get(SSD_AF))
                .checkValue(USB, vmwareData.get(USB));
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @TestType.e2e
    @DisplayName("Заказываем услуги и проверяем наличие письма")
    void createSubscriptionAndCheckMail() {
        mainPageProviderEmail.openLkLink(linkProvider.catalogLinkLK());
        catalogPage.clickOrderSubscription(subscriptionData.vmwareProtectName());
        vmwareProtectOrder
                .clickOrderType(COMMERCIAL)
                .setValue(VCPU, vmwareData.get(VCPU))
                .setValue(VRAM, vmwareData.get(VRAM))
                .setValue(SSD, vmwareData.get(SSD));
        backup
                .setValue(BACKUP_VOLUME, backupData.get(BACKUP_VOLUME))
                .setValue(VM_COUNT, backupData.get(VM_COUNT))
                .clickCloud(CLOUD_PLACEMENT, subscriptionData.setCloud());
        internet
                .setValue(INTERNET_SPEED, internetData.get(INTERNET_SPEED))
                .checkBox(subscriptionData.checkBoxMicrosoft());
        microsoft
                .setValue(SQL_SERVER_ENTERPRISE, microsoftData.get(SQL_SERVER_ENTERPRISE))
                .clickOrder();
        popUp.checkMessageText(popUpMessage.orderCreate());
        emailSteps.checkTextOfMessageAndDoRead(
                emailMessage.newSubscriptionEmailSubject(),
                subscriptionData.vmwareProtectName(),
                subscriptionData.backupName(),
                subscriptionData.accessInternetName(),
                subscriptionData.microsoftName(),
                "",
                userData.emailSalesManagerForEmail(),
                userData.emailServiceManagerForEmail());
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @TestType.e2e
    @DisplayName("Заказ VMware защищенная тестовая")
    void createProtectVMwareTest() {
        mainPageProvider.openLkLink(linkProvider.catalogLinkLK());
        catalogPage.clickOrderSubscription(subscriptionData.vmwareProtectName());
        vmwareProtectOrder
                .clickOrderType(TESTING)
                .setValue(NOTE, VMWARE_PROTECT.getTestNote())
                .setValue(NOTE, INTERNET.getTestNote())
                .clickOrder();
        popUp.checkMessageText(popUpMessage.orderCreate());
        open(myServiceLink);
        myServicesPage.clickOnTestSubscription(subscriptionData.vmwareProtectShortName());
        var vmwareLink = newSubscriptionRequestPage.getOrderAdminLink();
        open(myServiceLink);
        myServicesPage.clickOnTestSubscription(subscriptionData.accessInternetShortName());
        var inetLink = newSubscriptionRequestPage.getOrderAdminLink();
        adminPageProvider.adminLink(vmwareLink);
        vmwareProtectRequestPage
                .checkValue(VCPU, VALUE_CPU)
                .checkValue(VRAM, VALUE_RAM)
                .checkValue(HDD_7K, VALUE_HDD7K)
                .checkValue(HDD_10K, VALUE_ZERO)
                .checkValue(HDD_HYBRID, VALUE_ZERO)
                .checkValue(SSD, VALUE_ZERO)
                .checkValue(SSD_AF, VALUE_ZERO)
                .checkValue(USB, VALUE_ZERO);
        open(webConfig.getUrlAdmin() + inetLink);
        internetRequestPage
                .checkValue(INTERNET_SPEED, VALUE_INET)
                .checkValue(INTERNET_IP, VALUE_IP);
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @TestType.e2e
    @DisplayName("Заказ VMware закрытая коммерческая+Программы Microsoft")
    void createCloseVMwareCommercial() {
        mainPageProvider.openLkLink(linkProvider.catalogLinkLK());
        catalogPage.clickOrderSubscription(subscriptionData.vmwareCloseName());
        vmwareCloseOrder
                .clickOrderType(COMMERCIAL)
                .setTariff(subscriptionData.tariffFact())
                .setValue(VCPU, vmwareData.get(VCPU))
                .setValue(VRAM, vmwareData.get(VRAM))
                .setValue(HDD_7K, vmwareData.get(HDD_7K))
                .setValue(HDD_10K, vmwareData.get(HDD_10K))
                .setValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .setValue(SSD, vmwareData.get(SSD))
                .setValue(SSD_AF, vmwareData.get(SSD_AF))
                .setValue(NOTE, VMWARE_CLOSE.getCommercialNote())
                .checkBox(subscriptionData.checkBoxBackup())
                .checkBox(subscriptionData.checkBoxInet())
                .checkBox(subscriptionData.checkBoxMicrosoft());
        microsoft
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
                .setValue(SHAREPOINT_HOSTING, microsoftData.get(SHAREPOINT_HOSTING))
                .setValue(NOTE, MICROSOFT.getCommercialNote())
                .clickOrder();
        popUp.checkMessageText(popUpMessage.orderCreate());
        open(myServiceLink);
        //*******************Проверяем заказанную WMWare******************
        myServicesPage.clickOnCommercialSubscription(subscriptionData.vmwareCloseShortName());
        var vmwareLink = newSubscriptionRequestPage.getOrderAdminLink();
        open(myServiceLink);
        myServicesPage.clickOnCommercialSubscription(subscriptionData.microsoftShortName());
        var microsoftLink = newSubscriptionRequestPage.getOrderAdminLink();
        adminPageProvider.adminLink(vmwareLink);
        vmwareCloseRequestPage
                .checkValue(VCPU, vmwareData.get(VCPU))
                .checkValue(VRAM, vmwareData.get(VRAM))
                .checkValue(HDD_7K, vmwareData.get(HDD_7K))
                .checkValue(HDD_10K, vmwareData.get(HDD_10K))
                .checkValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .checkValue(SSD, vmwareData.get(SSD))
                .checkValue(SSD_AF, vmwareData.get(SSD_AF));
        open(webConfig.getUrlAdmin() + microsoftLink);
        subscriptionPageFactory.getRequestPage(MICROSOFT_CSS)
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

    @Severity(value = SeverityLevel.CRITICAL)
    @TestType.e2e
    @DisplayName("Заказ VMware закрытая тестовая")
    void createCloseVMwareTest() {
        mainPageProvider.openLkLink(linkProvider.catalogLinkLK());
        catalogPage.clickOrderSubscription(subscriptionData.vmwareCloseName());
        vmwareCloseOrder
                .clickOrderType(TESTING)
                .setValue(NOTE, VMWARE_CLOSE.getTestNote())
                .setValue(NOTE, INTERNET.getTestNote())
                .clickOrder();
        popUp.checkMessageText(popUpMessage.orderCreate());
        open(myServiceLink);
        myServicesPage.clickOnTestSubscription(subscriptionData.vmwareCloseShortName());
        var vmwareLink = newSubscriptionRequestPage.getOrderAdminLink();
        open(myServiceLink);
        myServicesPage.clickOnTestSubscription(subscriptionData.accessInternetShortName());
        var inetLink = newSubscriptionRequestPage.getOrderAdminLink();
        adminPageProvider.adminLink(vmwareLink);
        vmwareCloseRequestPage
                .checkValue(VCPU, VALUE_CPU)
                .checkValue(VRAM, VALUE_RAM)
                .checkValue(HDD_7K, VALUE_HDD7K)
                .checkValue(HDD_10K, VALUE_ZERO)
                .checkValue(HDD_HYBRID, VALUE_ZERO)
                .checkValue(SSD, VALUE_ZERO)
                .checkValue(SSD_AF, VALUE_ZERO);
        open(webConfig.getUrlAdmin() + inetLink);
        internetRequestPage
                .checkValue(INTERNET_SPEED, VALUE_INET)
                .checkValue(INTERNET_IP, VALUE_IP);
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @TestType.e2e
    @DisplayName("Заказ 3ГГц VMware  коммерческая + резервное копирование")
    void create3GHzVMwareCommercial() {
        mainPageProvider.openLkLink(linkProvider.catalogLinkLK());
        catalogPage.clickOrderSubscription(subscriptionData.vmware3GHzName());
        vmware3GHzOrder
                .clickOrderType(COMMERCIAL)
                .setTariff(subscriptionData.tariffFact())
                .setValue(VCPU, vmwareData.get(VCPU))
                .setValue(VRAM, vmwareData.get(VRAM))
                .setValue(HDD_7K, vmwareData.get(HDD_7K))
                .setValue(HDD_10K, vmwareData.get(HDD_10K))
                .setValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .setValue(SSD, vmwareData.get(SSD))
                .setValue(SSD_AF, vmwareData.get(SSD_AF))
                .setValue(USB, vmwareData.get(USB))
                .setValue(NOTE, VMWARE_3GHZ.getCommercialNote())
                .checkBox(subscriptionData.checkBoxInet());
        backup
                .setValue(BACKUP_VOLUME, backupData.get(BACKUP_VOLUME))
                .setValue(VM_COUNT, backupData.get(VM_COUNT))
                .setValue(TRAFFIC_MIN, backupData.get(TRAFFIC_MIN))
                .setValue(TRAFFIC_SPEED, backupData.get(TRAFFIC_SPEED))
                .clickCloud(CLOUD_PLACEMENT, subscriptionData.setCloud())
                .setValue(NOTE, BACKUP.getCommercialNote())
                .clickOrder();
        popUp.checkMessageText(popUpMessage.orderCreate());
        open(myServiceLink);
        myServicesPage.clickOnCommercialSubscription(subscriptionData.vmware3GHzShortName());
        var vmwareLink = newSubscriptionRequestPage.getOrderAdminLink();
        open(myServiceLink);
        myServicesPage.clickOnCommercialSubscription(subscriptionData.backupShortName());
        var backupLink = newSubscriptionRequestPage.getOrderAdminLink();
        adminPageProvider.adminLink(vmwareLink);
        vmware3GHzRequestPage
                .checkValue(VCPU, vmwareData.get(VCPU))
                .checkValue(VRAM, vmwareData.get(VRAM))
                .checkValue(HDD_7K, vmwareData.get(HDD_7K))
                .checkValue(HDD_10K, vmwareData.get(HDD_10K))
                .checkValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .checkValue(SSD, vmwareData.get(SSD))
                .checkValue(SSD_AF, vmwareData.get(SSD_AF))
                .checkValue(USB, vmwareData.get(USB));
        open(webConfig.getUrlAdmin() + backupLink);
        backupRequestPage
                .checkValue(BACKUP_VOLUME, backupData.get(BACKUP_VOLUME))
                .checkValue(VM_COUNT, backupData.get(VM_COUNT))
                .checkValue(TRAFFIC_MIN, backupData.get(TRAFFIC_MIN))
                .checkValue(TRAFFIC_SPEED, backupData.get(TRAFFIC_SPEED))
                .checkValue(CLOUD_PLACEMENT, subscriptionData.setCloud());

    }

    @Severity(value = SeverityLevel.CRITICAL)
    @TestType.e2e
    @DisplayName("Заказ VMware 3ГГц тестовая")
    void create3GHzVMwareTest() {
        mainPageProvider.openLkLink(linkProvider.catalogLinkLK());
        catalogPage.clickOrderSubscription(subscriptionData.vmware3GHzName());
        vmware3GHzOrder
                .clickOrderType(TESTING)
                .setValue(NOTE, VMWARE_3GHZ.getTestNote())
                .setValue(NOTE, INTERNET.getTestNote())
                .clickOrder();
        popUp.checkMessageText(popUpMessage.orderCreate());
        open(myServiceLink);
        myServicesPage.clickOnTestSubscription(subscriptionData.vmware3GHzShortName());
        var vmwareLink = newSubscriptionRequestPage.getOrderAdminLink();
        open(myServiceLink);
        myServicesPage.clickOnTestSubscription(subscriptionData.accessInternetShortName());
        var inetLink = newSubscriptionRequestPage.getOrderAdminLink();
        adminPageProvider.adminLink(vmwareLink);
        vmware3GHzRequestPage
                .checkValue(VCPU, VALUE_CPU)
                .checkValue(VRAM, VALUE_RAM)
                .checkValue(HDD_7K, VALUE_HDD7K)
                .checkValue(HDD_10K, VALUE_ZERO)
                .checkValue(HDD_HYBRID, VALUE_ZERO)
                .checkValue(SSD, VALUE_ZERO)
                .checkValue(SSD_AF, VALUE_ZERO)
                .checkValue(USB, VALUE_ZERO);
        open(webConfig.getUrlAdmin() + inetLink);
        internetRequestPage
                .checkValue(INTERNET_SPEED, VALUE_INET)
                .checkValue(INTERNET_IP, VALUE_IP);
    }



    @Severity(value = SeverityLevel.CRITICAL)
    @TestType.e2e
    @DisplayName("Заказ VMware IX коммерческая + доступ в интернет")
    void createIXVMwareCommercial() {
        mainPageProvider.openLkLink(linkProvider.catalogLinkLK());
        catalogPage.clickOrderSubscription(subscriptionData.vmwareIXName());
        vmwareIXOrder
                .clickOrderType(COMMERCIAL)
                .setTariff(subscriptionData.tariffFact())
                .setValue(VCPU, vmwareData.get(VCPU))
                .setValue(VRAM, vmwareData.get(VRAM))
                .setValue(HDD_7K, vmwareData.get(HDD_7K))
                .setValue(HDD_10K, vmwareData.get(HDD_10K))
                .setValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .setValue(SSD, vmwareData.get(SSD))
                .setValue(SSD_AF, vmwareData.get(SSD_AF))
                .setValue(USB, vmwareData.get(USB))
                .setValue(NOTE, VMWARE_IX.getCommercialNote())
                .checkBox(subscriptionData.checkBoxBackup());
        internet
                .setValue(INTERNET_SPEED, internetData.get(INTERNET_SPEED))
                .setValue(INTERNET_IP, internetData.get(INTERNET_IP))
                .setValue(NOTE, INTERNET.getCommercialNote())
                .clickOrder();
        popUp.checkMessageText(popUpMessage.orderCreate());
        open(myServiceLink);
        myServicesPage.clickOnCommercialSubscription(subscriptionData.vmwareIXShortName());
        var vmwareLink = newSubscriptionRequestPage.getOrderAdminLink();
        open(myServiceLink);
        myServicesPage.clickOnCommercialSubscription(subscriptionData.accessInternetShortName());
        var inetLink = newSubscriptionRequestPage.getOrderAdminLink();
        adminPageProvider.adminLink(vmwareLink);
        vmwareIXRequestPage
                .checkValue(VCPU, vmwareData.get(VCPU))
                .checkValue(VRAM, vmwareData.get(VRAM))
                .checkValue(HDD_7K, vmwareData.get(HDD_7K))
                .checkValue(HDD_10K, vmwareData.get(HDD_10K))
                .checkValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .checkValue(SSD, vmwareData.get(SSD))
                .checkValue(SSD_AF, vmwareData.get(SSD_AF))
                .checkValue(USB, vmwareData.get(USB));
        open(webConfig.getUrlAdmin() + inetLink);
        internetRequestPage
                .checkValue(INTERNET_SPEED, internetData.get(INTERNET_SPEED))
                .checkValue(INTERNET_IP, internetData.get(INTERNET_IP));
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @TestType.e2e
    @DisplayName("Заказ VMware IX тестовая")
    void createIXVMwareTest() {
        mainPageProvider.openLkLink(linkProvider.catalogLinkLK());
        catalogPage.clickOrderSubscription(subscriptionData.vmwareIXName());
        vmwareIXOrder
                .clickOrderType(TESTING)
                .setValue(NOTE, VMWARE_IX.getTestNote())
                .setValue(NOTE, INTERNET.getTestNote())
                .clickOrder();
        popUp.checkMessageText(popUpMessage.orderCreate());
        open(myServiceLink);
        myServicesPage.clickOnTestSubscription(subscriptionData.vmwareIXShortName());
        var vmwareLink = newSubscriptionRequestPage.getOrderAdminLink();
        open(myServiceLink);
        myServicesPage.clickOnTestSubscription(subscriptionData.accessInternetShortName());
        var inetLink = newSubscriptionRequestPage.getOrderAdminLink();
        adminPageProvider.adminLink(vmwareLink);
        vmwareIXRequestPage
                .checkValue(VCPU, VALUE_CPU)
                .checkValue(VRAM, VALUE_RAM)
                .checkValue(HDD_7K, VALUE_HDD7K)
                .checkValue(HDD_10K, VALUE_ZERO)
                .checkValue(HDD_HYBRID, VALUE_ZERO)
                .checkValue(SSD, VALUE_ZERO)
                .checkValue(SSD_AF, VALUE_ZERO)
                .checkValue(USB, VALUE_ZERO);
        open(webConfig.getUrlAdmin() + inetLink);
        internetRequestPage
                .checkValue(INTERNET_SPEED, VALUE_INET)
                .checkValue(INTERNET_IP, VALUE_IP);
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @TestType.e2e
    @DisplayName("Заказ Openstack коммерческая")
    void createOpenstackCommercial() {
        mainPageProvider.openLkLink(linkProvider.catalogLinkLK());
        catalogPage.clickOrderSubscription(subscriptionData.openstackName());
        openstackOrder
                .clickOrderType(COMMERCIAL)
                .setTariff(subscriptionData.tariffFix())
                .setValue(OPENSTACK_VCPU, openstackData.get(OPENSTACK_VCPU))
                .setValue(OPENSTACK_VRAM, openstackData.get(OPENSTACK_VRAM))
                .setValue(OPENSTACK_HDD_7K, openstackData.get(OPENSTACK_HDD_7K))
                .setValue(OPENSTACK_HDD_10K, openstackData.get(OPENSTACK_HDD_10K))
                .setValue(OPENSTACK_SSD_SDS, openstackData.get(OPENSTACK_SSD_SDS))
                .setValue(NOTE, OPENSTACK.getCommercialNote())
                .checkBox(subscriptionData.checkBoxBackup())
                .checkBox(subscriptionData.checkBoxInet())
                .clickOrder();
        popUp.checkMessageText(popUpMessage.orderCreate());
        open(myServiceLink);
        myServicesPage.clickOnCommercialSubscription(subscriptionData.openstackShortName());
        var openstackLink = newSubscriptionRequestPage.getOrderAdminLink();
        adminPageProvider.adminLink(openstackLink);
        openstackRequestPage
                .checkValue(OPENSTACK_VCPU, openstackData.get(OPENSTACK_VCPU))
                .checkValue(OPENSTACK_VRAM, openstackData.get(OPENSTACK_VRAM))
                .checkValue(OPENSTACK_HDD_7K, openstackData.get(OPENSTACK_HDD_7K))
                .checkValue(OPENSTACK_HDD_10K, openstackData.get(OPENSTACK_HDD_10K))
                .checkValue(OPENSTACK_SSD_SDS, openstackData.get(OPENSTACK_SSD_SDS));
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @TestType.e2e
    @DisplayName("Заказ Openstack тестовая")
    void createOpenstackTest() {
        mainPageProvider.openLkLink(linkProvider.catalogLinkLK());
        catalogPage.clickOrderSubscription(subscriptionData.openstackName());
        openstackOrder
                .clickOrderType(TESTING)
                .setValue(NOTE, OPENSTACK.getTestNote())
                .clickOrder();
        popUp.checkMessageText(popUpMessage.orderCreate());
        open(myServiceLink);
        myServicesPage.clickOnTestSubscription(subscriptionData.openstackShortName());
        var openstackLink = newSubscriptionRequestPage.getOrderAdminLink();
        open(myServiceLink);
        myServicesPage.clickOnTestSubscription(subscriptionData.accessInternetShortName());
        var inetLink = newSubscriptionRequestPage.getOrderAdminLink();
        adminPageProvider.adminLink(openstackLink);
        openstackRequestPage
                .checkValue(OPENSTACK_VCPU, VALUE_CPU)
                .checkValue(OPENSTACK_VRAM, VALUE_RAM)
                .checkValue(OPENSTACK_HDD_7K, VALUE_HDD7K)
                .checkValue(OPENSTACK_HDD_10K, VALUE_ZERO)
                .checkValue(OPENSTACK_SSD_SDS, VALUE_ZERO);
        open(webConfig.getUrlAdmin() + inetLink);
        internetRequestPage
                .checkValue(INTERNET_SPEED, VALUE_INET)
                .checkValue(INTERNET_IP, VALUE_IP);
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @TestType.e2e
    @DisplayName("Заказ Облачное хранилище коммерческая")
    void createCloudStorageCommercial() {
        mainPageProvider.openLkLink(linkProvider.catalogLinkLK());
        catalogPage.clickOrderSubscription(subscriptionData.cloudStorageName());
        cloudStorage
                .clickOrderType(COMMERCIAL)
                .clickCloud(STORAGE, subscriptionData.tariffGeneral())
                .setValue(NOTE, CLOUD_STORAGE.getCommercialNote())
                .clickOrder();
        popUp.checkMessageText(popUpMessage.orderCreate());
        open(myServiceLink);
        myServicesPage.clickOnCommercialSubscription(subscriptionData.cloudStorageShortName());
        var cloudStorageLink = newSubscriptionRequestPage.getOrderAdminLink();
        adminPageProvider.adminLink(cloudStorageLink);
        cloudStorageRequestPage
                .checkValue(STORAGE, subscriptionData.tariffGeneral());

    }

    @Severity(value = SeverityLevel.CRITICAL)
    @TestType.e2e
    @DisplayName("Заказ Облачное хранилище тестовая")
    void createCloudStorageTest() {
        mainPageProvider.openLkLink(linkProvider.catalogLinkLK());
        catalogPage.clickOrderSubscription(subscriptionData.cloudStorageName());
        cloudStorage
                .clickOrderType(TESTING)
                .setValue(NOTE, CLOUD_STORAGE.getTestNote())
                .clickOrder();
        popUp.checkMessageText(popUpMessage.orderCreate());
        open(myServiceLink);
        myServicesPage.clickOnTestSubscription(subscriptionData.cloudStorageShortName());
        var cloudStorageLink = newSubscriptionRequestPage.getOrderAdminLink();
        adminPageProvider.adminLink(cloudStorageLink);
        cloudStorageRequestPage
                .checkValue(STORAGE, subscriptionData.tariffDeveloper());

    }

}
