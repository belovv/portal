package adminTest.subscriptionTest;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import portal.admin.order_subscription_page.new_subscriptions_page.NewSubscriptionFinishPage;
import portal.admin.order_subscription_page.new_subscriptions_page.NewSubscriptionPage;
import portal.admin.order_subscription_page.new_subscriptions_page.NewSubscriptionPageOne;
import portal.admin.order_subscription_page.subscription_profile_pages.HeaderSubscriptionRequestPage;
import portal.admin.order_subscription_page.subscription_profile_pages.SubscriptionProfilePage;
import portal.admin.order_subscription_page.subscription_profile_pages.SubscriptionRequestPage;
import portal.annotations.Admin;
import portal.annotations.TestType;
import portal.data.EmailMessage;
import portal.data.LinkProvider;
import portal.data.SubscriptionData;
import portal.data.UserData;
import portal.data.enumdata.component.*;
import portal.email.EmailSteps;
import portal.listener.BaseTestExtension;
import portal.providers.DataProvider;
import portal.providers.MainPageProvider;
import portal.providers.SubscriptionPageFactory;
import portal.utils.DateTime;
import portal.utils.Status;

import java.util.Map;

import static com.codeborne.selenide.Selenide.switchTo;
import static portal.data.enumdata.ModeCharge.FACT;
import static portal.data.enumdata.ModeCharge.FIX;
import static portal.data.enumdata.Status.NEW;
import static portal.data.enumdata.Status.TEST;
import static portal.data.enumdata.SubscriptionIdName.*;
import static portal.data.enumdata.SubscriptionName.*;
import static portal.data.enumdata.TabName.REQUESTS;
import static portal.data.enumdata.TypeSubscription.TYPE_COMMERCIAL;
import static portal.data.enumdata.TypeSubscription.TYPE_TESTING;
import static portal.data.enumdata.component.BackupComponent.*;
import static portal.data.enumdata.component.CloudStorageComponent.STORAGE;
import static portal.data.enumdata.component.InternetComponent.INTERNET_IP;
import static portal.data.enumdata.component.InternetComponent.INTERNET_SPEED;
import static portal.data.enumdata.component.MicrosoftComponent.*;
import static portal.data.enumdata.component.OpenstackComponent.*;
import static portal.data.enumdata.component.VMwareComponent.*;

@Admin.newSubscription
@ExtendWith(BaseTestExtension.class)
public class AdminNewSubscriptionTest {

    private final DataProvider dataProvider = new DataProvider();
    private final SubscriptionData subscriptionData = dataProvider.subscriptionData();
    private final LinkProvider linkProvider = dataProvider.linkProvider();
    private final UserData userData = dataProvider.userData();
    private final EmailMessage emailMessage = dataProvider.emailMessage();
    private final EmailSteps emailSteps = new EmailSteps();
    private final Status status = new Status();
    private final DateTime dateTime = new DateTime();
    private final MainPageProvider mainPageProvider = new MainPageProvider();
    private final NewSubscriptionPageOne newSubscriptionPageOne = new NewSubscriptionPageOne();
    private final SubscriptionPageFactory subscriptionPageFactory = new SubscriptionPageFactory();
    private final NewSubscriptionPage newVMwareProtectPage = subscriptionPageFactory.getNewSubscriptionPage(PROTECT);
    private final NewSubscriptionPage newVMware3GHzPage = subscriptionPageFactory.getNewSubscriptionPage(GHZ);
    private final NewSubscriptionPage newVMwareClosePage = subscriptionPageFactory.getNewSubscriptionPage(CLOSE);
    private final NewSubscriptionPage newVMwareIXPage = subscriptionPageFactory.getNewSubscriptionPage(IX);
    private final NewSubscriptionPage newOpenstackPage = subscriptionPageFactory.getNewSubscriptionPage(OPENSTACK_CSS);
    private final NewSubscriptionPage newInternetPage = subscriptionPageFactory.getNewSubscriptionPage(INTERNET_CSS);
    private final NewSubscriptionPage newMicrosoftPage = subscriptionPageFactory.getNewSubscriptionPage(MICROSOFT_CSS);
    private final NewSubscriptionPage newBackupPage = subscriptionPageFactory.getNewSubscriptionPage(BACKUP_CSS);
    private final NewSubscriptionPage newCloudStoragePage = subscriptionPageFactory.getNewSubscriptionPage(CLOUD_STORAGE_CSS);
    private final SubscriptionRequestPage vmwareRequestProtectPage = subscriptionPageFactory.getRequestPage(PROTECT);
    private final SubscriptionRequestPage vMware3GHzRequestPage = subscriptionPageFactory.getRequestPage(GHZ);
    private final SubscriptionRequestPage vMwareCloseRequestPage = subscriptionPageFactory.getRequestPage(CLOSE);
    private final SubscriptionRequestPage vMwareIXRequestPage = subscriptionPageFactory.getRequestPage(IX);
    private final SubscriptionRequestPage openstackRequestPage = subscriptionPageFactory.getRequestPage(OPENSTACK_CSS);
    private final SubscriptionRequestPage backupRequestPage = subscriptionPageFactory.getRequestPage(BACKUP_CSS);
    private final SubscriptionRequestPage internetRequestPage = subscriptionPageFactory.getRequestPage(INTERNET_CSS);
    private final SubscriptionRequestPage microsoftRequestPage = subscriptionPageFactory.getRequestPage(MICROSOFT_CSS);
    private final SubscriptionRequestPage cloudStorageRequestPage = subscriptionPageFactory.getRequestPage(CLOUD_STORAGE_CSS);
    private final NewSubscriptionFinishPage newSubscriptionFinishPage = new NewSubscriptionFinishPage();
    private final SubscriptionProfilePage subscriptionProfilePage = new SubscriptionProfilePage();
    private final HeaderSubscriptionRequestPage headerSubscriptionRequestPage = new HeaderSubscriptionRequestPage();
    private final String date = dateTime.getTodayDate();
    private String time;
    private final Map<Enum, String> vmwareData = dataProvider.getComponentsValue(VMwareComponent.values());
    private final Map<Enum, String> openstackData = dataProvider.getComponentsValue(OpenstackComponent.values());
    private final Map<Enum, String> internetData = dataProvider.getComponentsValue(InternetComponent.values());
    private final Map<Enum, String> backupData = dataProvider.getComponentsValue(BackupComponent.values());
    private final Map<Enum, String> microsoftData = dataProvider.getComponentsValue(MicrosoftComponent.values());

    @Severity(value = SeverityLevel.BLOCKER)
    @DisplayName("Заказ VMware защищенная коммерческая")
    @TestType.e2e
    void createVMwareCommercial() {
        time = dateTime.getNext2Minutes();
        mainPageProvider.adminLink(linkProvider.newSubscriptionLink());
        newSubscriptionPageOne
                .setCustomer(userData.customerNewSubscriptionAdmin())
                .setSubscription(subscriptionData.vmwareProtectName())
                .setTypeSubscription(TYPE_COMMERCIAL)
                .setDate(date, time)
                .setPaperOrder(VMWARE_PROTECT.getOrderNote())
                .clickButton();
        //*************Step 2**********************
        newVMwareProtectPage
                .clickOn(SCHEME_PRICING, subscriptionData.tariffFact())
                .setValue(VCPU, vmwareData.get(VCPU))
                .setValue(VRAM, vmwareData.get(VRAM))
                .setValue(HDD_7K, vmwareData.get(HDD_7K))
                .setValue(HDD_10K, vmwareData.get(HDD_10K))
                .setValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .setValue(SSD, vmwareData.get(SSD))
                .setValue(SSD_AF, vmwareData.get(SSD_AF))
                .setValue(USB, vmwareData.get(USB))
                .setValue(NOTE, VMWARE_PROTECT.getCommercialNote())
                .checkBox(subscriptionData.checkBoxBackup())
                .checkBox(subscriptionData.checkBoxInet())
                .clickButton();
        //***************Проверяем правильность заказанных услуг**************************
        newSubscriptionFinishPage
                .checkNumberOfSubscription(1)
                .checkNameOfSubscription(0, subscriptionData.vmwareProtectName())
                .clickNumberOfSubscription(0);
        switchTo().window(1);
        status.checkStatus(NEW.getValue());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage
                .checkPaperOrder(VMWARE_PROTECT.getOrderNote())
                .checkTariffType(FACT)
                .checkStartDate(date, time);
        vmwareRequestProtectPage
                .checkValue(VCPU, vmwareData.get(VCPU))
                .checkValue(VRAM, vmwareData.get(VRAM))
                .checkValue(HDD_7K, vmwareData.get(HDD_7K))
                .checkValue(HDD_10K, vmwareData.get(HDD_10K))
                .checkValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .checkValue(SSD, vmwareData.get(SSD))
                .checkValue(SSD_AF, vmwareData.get(SSD_AF))
                .checkValue(USB, vmwareData.get(USB));
        Selenide.closeWindow();
        switchTo().window(0);
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Заказ VMware защищенная коммерческая + Резервное копирование + Резервированный доступ в Интернет + Программы Microsoft")
    @TestType.e2e
    void createVMwareCommercialPlus() {
        mainPageProvider.adminLink(linkProvider.newSubscriptionLink());
        newSubscriptionPageOne
                .setCustomer(userData.customerCheckEmail())
                .setSubscription(subscriptionData.vmwareProtectName())
                .setTypeSubscription(TYPE_COMMERCIAL)
                .setPaperOrder(VMWARE_PROTECT.getOrderNote())
                .clickButton();
        //*************Step 2**********************
        newVMwareProtectPage
                .setValue(SSD, vmwareData.get(SSD))
                .setValue(NOTE, VMWARE_PROTECT.getCommercialNote());
        newInternetPage
                .setValue(INTERNET_SPEED, internetData.get(INTERNET_SPEED))
                .setValue(INTERNET_IP, internetData.get(INTERNET_IP))
                .setValue(NOTE, INTERNET.getCommercialNote());
        newBackupPage
                .setValue(BACKUP_VOLUME, backupData.get(BACKUP_VOLUME))
                .setValue(VM_COUNT, backupData.get(VM_COUNT))
                .setValue(TRAFFIC_MIN, backupData.get(TRAFFIC_MIN))
                .setValue(TRAFFIC_SPEED, backupData.get(TRAFFIC_SPEED))
                .clickOn(CLOUD_PLACEMENT, subscriptionData.setCloud())
                .setValue(NOTE, BACKUP.getCommercialNote());
        newVMwareProtectPage.checkBox(subscriptionData.checkBoxMicrosoft());
        newMicrosoftPage
                .setValue(PROJECT_SERVER, microsoftData.get(PROJECT_SERVER))
                .setValue(NOTE, MICROSOFT.getCommercialNote());
        newVMwareProtectPage.clickButton();
        newSubscriptionFinishPage
                .checkNumberOfSubscription(4)
                .checkNameOfSubscription(0, subscriptionData.vmwareProtectName())
                .checkNameOfSubscription(1, subscriptionData.backupName())
                .checkNameOfSubscription(2, subscriptionData.accessInternetName())
                .checkNameOfSubscription(3, subscriptionData.microsoftName());
        emailSteps.checkTextOfMessageAndDoRead(
                emailMessage.newSubscriptionEmailSubject(),
                subscriptionData.vmwareProtectName(),
                subscriptionData.backupName(),
                subscriptionData.accessInternetName(),
                subscriptionData.microsoftName(),
                VMWARE_PROTECT.getOrderNote(),
                userData.emailSalesManagerForEmail());

        //todo добавить клик на номер бумажного заказа
    }

    @Severity(value = SeverityLevel.BLOCKER)
    @DisplayName("Заказ VMware защищенная тестовая")
    @TestType.e2e
    void createVMwareTest() {
        mainPageProvider.adminLink(linkProvider.newSubscriptionLink());
        newSubscriptionPageOne
                .setCustomer(userData.customerNewSubscriptionAdmin())
                .setSubscription(subscriptionData.vmwareProtectName())
                .setTypeSubscription(TYPE_TESTING)
                .setPaperOrder(VMWARE_PROTECT.getOrderNote())
                .clickButton();
        //*************Step 2**********************
        newVMwareProtectPage
                .clickOn(SCHEME_PRICING, subscriptionData.tariffFix())
                .setValue(VCPU, vmwareData.get(VCPU))
                .setValue(VRAM, vmwareData.get(VRAM))
                .setValue(HDD_7K, vmwareData.get(HDD_7K))
                .setValue(HDD_10K, vmwareData.get(HDD_10K))
                .setValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .setValue(SSD, vmwareData.get(SSD))
                .setValue(SSD_AF, vmwareData.get(SSD_AF))
                .setValue(USB, vmwareData.get(USB))
                .setValue(NOTE, VMWARE_PROTECT.getTestNote())
                .checkBox(subscriptionData.checkBoxBackup())
                .checkBox(subscriptionData.checkBoxInet())
                .clickButton();
        //***************Проверяем правильность заказанных услуг**************************
        newSubscriptionFinishPage
                .checkNumberOfSubscription(1)
                .checkNameOfSubscription(0, subscriptionData.vmwareProtectName())
                .clickNumberOfSubscription(0);
        switchTo().window(1);
        status
                .checkStatus(NEW.getValue())
                .checkTestSubscription(TEST.getValue());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage
                .checkPaperOrder(VMWARE_PROTECT.getOrderNote())
                .checkTariffType(FIX);
        vmwareRequestProtectPage
                .checkValue(VCPU, vmwareData.get(VCPU))
                .checkValue(VRAM, vmwareData.get(VRAM))
                .checkValue(HDD_7K, vmwareData.get(HDD_7K))
                .checkValue(HDD_10K, vmwareData.get(HDD_10K))
                .checkValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .checkValue(SSD, vmwareData.get(SSD))
                .checkValue(SSD_AF, vmwareData.get(SSD_AF))
                .checkValue(USB, vmwareData.get(USB));
        Selenide.closeWindow();
        switchTo().window(0);
    }

    @Severity(value = SeverityLevel.BLOCKER)
    @DisplayName("Заказ VMware 3Ггц коммерческая")
    @TestType.e2e
    void createVMware3GHzCommercial() {
        time = dateTime.getNext2Minutes();
        mainPageProvider.adminLink(linkProvider.newSubscriptionLink());
        newSubscriptionPageOne
                .setCustomer(userData.customerNewSubscriptionAdmin())
                .setSubscription(subscriptionData.vmware3GHzName())
                .setTypeSubscription(TYPE_COMMERCIAL)
                .setDate(date, time)
                .setPaperOrder(VMWARE_3GHZ.getOrderNote())
                .clickButton();
        //*************Step 2**********************
        newVMware3GHzPage
                .clickOn(SCHEME_PRICING, subscriptionData.tariffFact())
                .setValue(VCPU, vmwareData.get(VCPU))
                .setValue(VRAM, vmwareData.get(VRAM))
                .setValue(HDD_7K, vmwareData.get(HDD_7K))
                .setValue(HDD_10K, vmwareData.get(HDD_10K))
                .setValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .setValue(SSD, vmwareData.get(SSD))
                .setValue(SSD_AF, vmwareData.get(SSD_AF))
                .setValue(USB, vmwareData.get(USB))
                .setValue(NOTE, VMWARE_3GHZ.getCommercialNote())
                .checkBox(subscriptionData.checkBoxBackup())
                .checkBox(subscriptionData.checkBoxInet())
                .clickButton();
        //***************Проверяем правильность заказанных услуг**************************
        newSubscriptionFinishPage
                .checkNumberOfSubscription(1)
                .checkNameOfSubscription(0, subscriptionData.vmware3GHzName())
                .clickNumberOfSubscription(0);
        switchTo().window(1);
        status.checkStatus(NEW.getValue());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage
                .checkPaperOrder(VMWARE_3GHZ.getOrderNote())
                .checkTariffType(FACT)
                .checkStartDate(date, time);
        vMware3GHzRequestPage
                .checkValue(VCPU, vmwareData.get(VCPU))
                .checkValue(VRAM, vmwareData.get(VRAM))
                .checkValue(HDD_7K, vmwareData.get(HDD_7K))
                .checkValue(HDD_10K, vmwareData.get(HDD_10K))
                .checkValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .checkValue(SSD, vmwareData.get(SSD))
                .checkValue(SSD_AF, vmwareData.get(SSD_AF))
                .checkValue(USB, vmwareData.get(USB));
        Selenide.closeWindow();
        switchTo().window(0);
    }

    @Severity(value = SeverityLevel.BLOCKER)
    @DisplayName("Заказ VMware 3Ггц тестовая")
    @TestType.e2e
    void createVMware3GHzTest() {
        mainPageProvider.adminLink(linkProvider.newSubscriptionLink());
        newSubscriptionPageOne
                .setCustomer(userData.customerNewSubscriptionAdmin())
                .setSubscription(subscriptionData.vmware3GHzName())
                .setTypeSubscription(TYPE_TESTING)
                .setPaperOrder(VMWARE_3GHZ.getOrderNote())
                .clickButton();
        //*************Step 2**********************
        newVMware3GHzPage
                .clickOn(SCHEME_PRICING, subscriptionData.tariffFix())
                .setValue(VCPU, vmwareData.get(VCPU))
                .setValue(VRAM, vmwareData.get(VRAM))
                .setValue(HDD_7K, vmwareData.get(HDD_7K))
                .setValue(HDD_10K, vmwareData.get(HDD_10K))
                .setValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .setValue(SSD, vmwareData.get(SSD))
                .setValue(SSD_AF, vmwareData.get(SSD_AF))
                .setValue(USB, vmwareData.get(USB))
                .setValue(NOTE, VMWARE_3GHZ.getTestNote())
                .checkBox(subscriptionData.checkBoxBackup())
                .checkBox(subscriptionData.checkBoxInet())
                .clickButton();
        //***************Проверяем правильность заказанных услуг**************************
        newSubscriptionFinishPage
                .checkNumberOfSubscription(1)
                .checkNameOfSubscription(0, subscriptionData.vmware3GHzName())
                .clickNumberOfSubscription(0);
        switchTo().window(1);
        status
                .checkStatus(NEW.getValue())
                .checkTestSubscription(TEST.getValue());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage
                .checkPaperOrder(VMWARE_3GHZ.getOrderNote())
                .checkTariffType(FIX);
        vMware3GHzRequestPage
                .checkValue(VCPU, vmwareData.get(VCPU))
                .checkValue(VRAM, vmwareData.get(VRAM))
                .checkValue(HDD_7K, vmwareData.get(HDD_7K))
                .checkValue(HDD_10K, vmwareData.get(HDD_10K))
                .checkValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .checkValue(SSD, vmwareData.get(SSD))
                .checkValue(SSD_AF, vmwareData.get(SSD_AF))
                .checkValue(USB, vmwareData.get(USB));
        Selenide.closeWindow();
        switchTo().window(0);
    }

    @Severity(value = SeverityLevel.BLOCKER)
    @DisplayName("Заказ VMware IX коммерческая")
    @TestType.e2e
    void createVMwareIXCommercial() {
        time = dateTime.getNext2Minutes();
        mainPageProvider.adminLink(linkProvider.newSubscriptionLink());
        newSubscriptionPageOne
                .setCustomer(userData.customerNewSubscriptionAdmin())
                .setSubscription(subscriptionData.vmwareIXName())
                .setTypeSubscription(TYPE_COMMERCIAL)
                .setDate(date, time)
                .setPaperOrder(VMWARE_IX.getOrderNote())
                .clickButton();
        //*************Step 2**********************
        newVMwareIXPage
                .clickOn(SCHEME_PRICING, subscriptionData.tariffFact())
                .setValue(VCPU, vmwareData.get(VCPU))
                .setValue(VRAM, vmwareData.get(VRAM))
                .setValue(HDD_7K, vmwareData.get(HDD_7K))
                .setValue(HDD_10K, vmwareData.get(HDD_10K))
                .setValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .setValue(SSD, vmwareData.get(SSD))
                .setValue(SSD_AF, vmwareData.get(SSD_AF))
                .setValue(USB, vmwareData.get(USB))
                .setValue(NOTE, VMWARE_IX.getCommercialNote())
                .checkBox(subscriptionData.checkBoxBackup())
                .checkBox(subscriptionData.checkBoxInet())
                .clickButton();
        //***************Проверяем правильность заказанных услуг**************************
        newSubscriptionFinishPage
                .checkNumberOfSubscription(1)
                .checkNameOfSubscription(0, subscriptionData.vmwareIXName())
                .clickNumberOfSubscription(0);
        switchTo().window(1);
        status.checkStatus(NEW.getValue());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage
                .checkPaperOrder(VMWARE_IX.getOrderNote())
                .checkTariffType(FACT)
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
        Selenide.closeWindow();
        switchTo().window(0);
    }

    @Severity(value = SeverityLevel.BLOCKER)
    @DisplayName("Заказ VMware IX тестовая")
    @TestType.e2e
    void createVMwareIXTest() {
        mainPageProvider.adminLink(linkProvider.newSubscriptionLink());
        newSubscriptionPageOne
                .setCustomer(userData.customerNewSubscriptionAdmin())
                .setSubscription(subscriptionData.vmwareIXName())
                .setTypeSubscription(TYPE_TESTING)
                .setPaperOrder(VMWARE_IX.getOrderNote())
                .clickButton();
        //*************Step 2**********************
        newVMwareIXPage
                .clickOn(SCHEME_PRICING, subscriptionData.tariffFix())
                .setValue(VCPU, vmwareData.get(VCPU))
                .setValue(VRAM, vmwareData.get(VRAM))
                .setValue(HDD_7K, vmwareData.get(HDD_7K))
                .setValue(HDD_10K, vmwareData.get(HDD_10K))
                .setValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .setValue(SSD, vmwareData.get(SSD))
                .setValue(SSD_AF, vmwareData.get(SSD_AF))
                .setValue(USB, vmwareData.get(USB))
                .setValue(NOTE,VMWARE_IX.getTestNote())
                .checkBox(subscriptionData.checkBoxBackup())
                .checkBox(subscriptionData.checkBoxInet())
                .clickButton();
        //***************Проверяем правильность заказанных услуг**************************
        newSubscriptionFinishPage
                .checkNumberOfSubscription(1)
                .checkNameOfSubscription(0, subscriptionData.vmwareIXName())
                .clickNumberOfSubscription(0);
        switchTo().window(1);
        status
                .checkStatus(NEW.getValue())
                .checkTestSubscription(TEST.getValue());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage
                .checkPaperOrder(VMWARE_IX.getOrderNote())
                .checkTariffType(FIX);
        vMwareIXRequestPage
                .checkValue(VCPU, vmwareData.get(VCPU))
                .checkValue(VRAM, vmwareData.get(VRAM))
                .checkValue(HDD_7K, vmwareData.get(HDD_7K))
                .checkValue(HDD_10K, vmwareData.get(HDD_10K))
                .checkValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .checkValue(SSD, vmwareData.get(SSD))
                .checkValue(SSD_AF, vmwareData.get(SSD_AF))
                .checkValue(USB, vmwareData.get(USB));
        Selenide.closeWindow();
        switchTo().window(0);
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Заказ VMware закрытая коммерческая")
    @TestType.e2e
    void createVMwareCloseCommercial() {
        time = dateTime.getNext2Minutes();
        mainPageProvider.adminLink(linkProvider.newSubscriptionLink());
        newSubscriptionPageOne
                .setCustomer(userData.customerNewSubscriptionAdmin())
                .setSubscription(subscriptionData.vmwareCloseName())
                .setTypeSubscription(TYPE_COMMERCIAL)
                .setDate(date, time)
                .setPaperOrder(VMWARE_CLOSE.getOrderNote())
                .clickButton();
        //*************Step 2**********************
        newVMwareClosePage
                .clickOn(SCHEME_PRICING, subscriptionData.tariffFact())
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
                .clickButton();
        //***************Проверяем правильность заказанных услуг**************************
        newSubscriptionFinishPage
                .checkNumberOfSubscription(1)
                .checkNameOfSubscription(0, subscriptionData.vmwareCloseName())
                .clickNumberOfSubscription(0);
        switchTo().window(1);
        status.checkStatus(NEW.getValue());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage
                .checkPaperOrder(VMWARE_CLOSE.getOrderNote())
                .checkTariffType(FACT)
                .checkStartDate(date, time);
        vMwareCloseRequestPage
                .checkValue(VCPU, vmwareData.get(VCPU))
                .checkValue(VRAM, vmwareData.get(VRAM))
                .checkValue(HDD_7K, vmwareData.get(HDD_7K))
                .checkValue(HDD_10K, vmwareData.get(HDD_10K))
                .checkValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .checkValue(SSD, vmwareData.get(SSD))
                .checkValue(SSD_AF, vmwareData.get(SSD_AF));
        Selenide.closeWindow();
        switchTo().window(0);
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Заказ VMware закрытая тестовая")
    @TestType.e2e
    void createVMwareCloseTest() {
        mainPageProvider.adminLink(linkProvider.newSubscriptionLink());
        newSubscriptionPageOne
                .setCustomer(userData.customerNewSubscriptionAdmin())
                .setSubscription(subscriptionData.vmwareCloseName())
                .setTypeSubscription(TYPE_TESTING)
                .setPaperOrder(VMWARE_CLOSE.getOrderNote())
                .clickButton();
        //*************Step 2**********************
        newVMwareClosePage
                .clickOn(SCHEME_PRICING, subscriptionData.tariffFix())
                .setValue(VCPU, vmwareData.get(VCPU))
                .setValue(VRAM, vmwareData.get(VRAM))
                .setValue(HDD_7K, vmwareData.get(HDD_7K))
                .setValue(HDD_10K, vmwareData.get(HDD_10K))
                .setValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .setValue(SSD, vmwareData.get(SSD))
                .setValue(SSD_AF, vmwareData.get(SSD_AF))
                .setValue(NOTE, VMWARE_CLOSE.getTestNote())
                .checkBox(subscriptionData.checkBoxBackup())
                .checkBox(subscriptionData.checkBoxInet())
                .clickButton();
        //***************Проверяем правильность заказанных услуг**************************
        newSubscriptionFinishPage
                .checkNumberOfSubscription(1)
                .checkNameOfSubscription(0, subscriptionData.vmwareCloseName())
                .clickNumberOfSubscription(0);
        switchTo().window(1);
        status
                .checkStatus(NEW.getValue())
                .checkTestSubscription(TEST.getValue());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage
                .checkPaperOrder(VMWARE_CLOSE.getOrderNote())
                .checkTariffType(FIX);
        vMwareCloseRequestPage
                .checkValue(VCPU, vmwareData.get(VCPU))
                .checkValue(VRAM, vmwareData.get(VRAM))
                .checkValue(HDD_7K, vmwareData.get(HDD_7K))
                .checkValue(HDD_10K, vmwareData.get(HDD_10K))
                .checkValue(HDD_HYBRID, vmwareData.get(HDD_HYBRID))
                .checkValue(SSD, vmwareData.get(SSD))
                .checkValue(SSD_AF, vmwareData.get(SSD_AF));
        Selenide.closeWindow();
        switchTo().window(0);

    }

    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Заказ Openstack V3, коммерческая")
    @TestType.e2e
    void createOpenstackCommercial() {
        time = dateTime.getNext2Minutes();
        mainPageProvider.adminLink(linkProvider.newSubscriptionLink());
        newSubscriptionPageOne
                .setCustomer(userData.customerNewSubscriptionAdmin())
                .setSubscription(subscriptionData.openstackName())
                .setTypeSubscription(TYPE_COMMERCIAL)
                .setDate(date, time)
                .setPaperOrder(OPENSTACK.getOrderNote())
                .clickButton();
        //*************Step 2**********************
        newOpenstackPage
                .clickOn(SCHEME_PRICING, subscriptionData.tariffFact())
                .setValue(OPENSTACK_VCPU, openstackData.get(OPENSTACK_VCPU))
                .setValue(OPENSTACK_VRAM, openstackData.get(OPENSTACK_VRAM))
                .setValue(OPENSTACK_HDD_7K, openstackData.get(OPENSTACK_HDD_7K))
                .setValue(OPENSTACK_HDD_10K, openstackData.get(OPENSTACK_HDD_10K))
                .setValue(OPENSTACK_SSD_SDS, openstackData.get(OPENSTACK_SSD_SDS))
                .setValue(NOTE, OPENSTACK.getCommercialNote())
                .checkBox(subscriptionData.checkBoxBackup())
                .checkBox(subscriptionData.checkBoxInet())
                .clickButton();
        //***************Проверяем правильность заказанных услуг**************************
        newSubscriptionFinishPage
                .checkNumberOfSubscription(1)
                .checkNameOfSubscription(0, subscriptionData.openstackName())
                .clickNumberOfSubscription(0);
        switchTo().window(1);
        status.checkStatus(NEW.getValue());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage
                .checkPaperOrder(OPENSTACK.getOrderNote())
                .checkTariffType(FACT)
                .checkStartDate(date, time);
        openstackRequestPage
                .checkValue(OPENSTACK_VCPU, openstackData.get(OPENSTACK_VCPU))
                .checkValue(OPENSTACK_VRAM, openstackData.get(OPENSTACK_VRAM))
                .checkValue(OPENSTACK_HDD_7K, openstackData.get(OPENSTACK_HDD_7K))
                .checkValue(OPENSTACK_HDD_10K, openstackData.get(OPENSTACK_HDD_10K))
                .checkValue(OPENSTACK_SSD_SDS, openstackData.get(OPENSTACK_SSD_SDS));
        Selenide.closeWindow();
        switchTo().window(0);
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Заказ Openstack V3, тестовая")
    @TestType.e2e
    void createOpenstackTest() {
        mainPageProvider.adminLink(linkProvider.newSubscriptionLink());
        newSubscriptionPageOne
                .setCustomer(userData.customerNewSubscriptionAdmin())
                .setSubscription(subscriptionData.openstackName())
                .setTypeSubscription(TYPE_TESTING)
                .setPaperOrder(OPENSTACK.getOrderNote())
                .clickButton();
        //*************Step 2**********************
        newOpenstackPage
                .clickOn(SCHEME_PRICING, subscriptionData.tariffFact())
                .setValue(OPENSTACK_VCPU, openstackData.get(OPENSTACK_VCPU))
                .setValue(OPENSTACK_VRAM, openstackData.get(OPENSTACK_VRAM))
                .setValue(OPENSTACK_HDD_7K, openstackData.get(OPENSTACK_HDD_7K))
                .setValue(OPENSTACK_HDD_10K, openstackData.get(OPENSTACK_HDD_10K))
                .setValue(OPENSTACK_SSD_SDS, openstackData.get(OPENSTACK_SSD_SDS))
                .setValue(NOTE, OPENSTACK.getTestNote())
                .checkBox(subscriptionData.checkBoxBackup())
                .checkBox(subscriptionData.checkBoxInet())
                .clickButton();
        //***************Проверяем правильность заказанных услуг**************************
        newSubscriptionFinishPage
                .checkNumberOfSubscription(1)
                .checkNameOfSubscription(0, subscriptionData.openstackName())
                .clickNumberOfSubscription(0);
        switchTo().window(1);
        status
                .checkStatus(NEW.getValue())
                .checkTestSubscription(TEST.getValue());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage
                .checkPaperOrder(OPENSTACK.getOrderNote())
                .checkTariffType(FACT);
        openstackRequestPage
                .checkValue(OPENSTACK_VCPU, openstackData.get(OPENSTACK_VCPU))
                .checkValue(OPENSTACK_VRAM, openstackData.get(OPENSTACK_VRAM))
                .checkValue(OPENSTACK_HDD_7K, openstackData.get(OPENSTACK_HDD_7K))
                .checkValue(OPENSTACK_HDD_10K, openstackData.get(OPENSTACK_HDD_10K))
                .checkValue(OPENSTACK_SSD_SDS, openstackData.get(OPENSTACK_SSD_SDS));
        Selenide.closeWindow();
        switchTo().window(0);
    }

    @Severity(value = SeverityLevel.BLOCKER)
    @DisplayName("Заказ резервное копирование, коммерческая")
    @TestType.e2e
    void createBackupCommercial() {
        time = dateTime.getNext2Minutes();
        mainPageProvider.adminLink(linkProvider.newSubscriptionLink());
        newSubscriptionPageOne
                .setCustomer(userData.customerNewSubscriptionAdmin())
                .setSubscription(subscriptionData.backupName())
                .setTypeSubscription(TYPE_COMMERCIAL)
                .setDate(date, time)
                .setPaperOrder(BACKUP.getOrderNote())
                .clickButton();
        //*************Step 2**********************
        newBackupPage
                .setValue(BACKUP_VOLUME, backupData.get(BACKUP_VOLUME))
                .setValue(VM_COUNT, backupData.get(VM_COUNT))
                .setValue(TRAFFIC_MIN, backupData.get(TRAFFIC_MIN))
                .setValue(TRAFFIC_SPEED, backupData.get(TRAFFIC_SPEED))
                .clickOn(CLOUD_PLACEMENT, subscriptionData.setCloud())
                .setValue(NOTE, BACKUP.getCommercialNote())
                .clickButton();
        //***************Проверяем правильность заказанных услуг**************************
        newSubscriptionFinishPage
                .checkNumberOfSubscription(1)
                .checkNameOfSubscription(0, subscriptionData.backupName())
                .clickNumberOfSubscription(0);
        switchTo().window(1);
        status.checkStatus(NEW.getValue());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage
                .checkPaperOrder(BACKUP.getOrderNote())
                .checkStartDate(date, time);
        backupRequestPage
                .checkValue(BACKUP_VOLUME, backupData.get(BACKUP_VOLUME))
                .checkValue(VM_COUNT, backupData.get(VM_COUNT))
                .checkValue(TRAFFIC_MIN, backupData.get(TRAFFIC_MIN))
                .checkValue(TRAFFIC_SPEED, backupData.get(TRAFFIC_SPEED))
                .checkValue(CLOUD_PLACEMENT, subscriptionData.setCloud());
        Selenide.closeWindow();
        switchTo().window(0);
    }

    @Severity(value = SeverityLevel.BLOCKER)
    @DisplayName("Заказ резервное копирование, тестовая")
    @TestType.e2e
    void createBackupTest() {
        mainPageProvider.adminLink(linkProvider.newSubscriptionLink());
        newSubscriptionPageOne
                .setCustomer(userData.customerNewSubscriptionAdmin())
                .setSubscription(subscriptionData.backupName())
                .setTypeSubscription(TYPE_TESTING)
                .setPaperOrder(BACKUP.getOrderNote())
                .clickButton();
        //*************Step 2**********************
        newBackupPage
                .setValue(BACKUP_VOLUME, backupData.get(BACKUP_VOLUME))
                .setValue(VM_COUNT, backupData.get(VM_COUNT))
                .setValue(TRAFFIC_MIN, backupData.get(TRAFFIC_MIN))
                .setValue(TRAFFIC_SPEED, backupData.get(TRAFFIC_SPEED))
                .clickOn(CLOUD_PLACEMENT, subscriptionData.setCloud())
                .setValue(NOTE, BACKUP.getTestNote())
                .clickButton();
        //***************Проверяем правильность заказанных услуг**************************
        newSubscriptionFinishPage
                .checkNumberOfSubscription(1)
                .checkNameOfSubscription(0, subscriptionData.backupName())
                .clickNumberOfSubscription(0);
        switchTo().window(1);
        status
                .checkStatus(NEW.getValue())
                .checkTestSubscription(TEST.getValue());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage.checkPaperOrder(BACKUP.getOrderNote());
        backupRequestPage
                .checkValue(BACKUP_VOLUME, backupData.get(BACKUP_VOLUME))
                .checkValue(VM_COUNT, backupData.get(VM_COUNT))
                .checkValue(TRAFFIC_MIN, backupData.get(TRAFFIC_MIN))
                .checkValue(TRAFFIC_SPEED, backupData.get(TRAFFIC_SPEED))
                .checkValue(CLOUD_PLACEMENT, subscriptionData.setCloud());
        Selenide.closeWindow();
        switchTo().window(0);
    }

    @Severity(value = SeverityLevel.BLOCKER)
    @DisplayName("Заказ резервированный доступ в интернет, коммерческая")
    @TestType.e2e
    void createAccessInternetCommercial() {
        time = dateTime.getNext2Minutes();
        mainPageProvider.adminLink(linkProvider.newSubscriptionLink());
        newSubscriptionPageOne
                .setCustomer(userData.customerNewSubscriptionAdmin())
                .setSubscription(subscriptionData.accessInternetName())
                .setTypeSubscription(TYPE_COMMERCIAL)
                .setDate(date, time)
                .setPaperOrder(INTERNET.getOrderNote())
                .clickButton();
        //*************Step 2**********************
        newInternetPage
                .setValue(INTERNET_SPEED, internetData.get(INTERNET_SPEED))
                .setValue(INTERNET_IP, internetData.get(INTERNET_IP))
                .setValue(NOTE, INTERNET.getCommercialNote())
                .clickButton();
        //***************Проверяем правильность заказанных услуг**************************
        newSubscriptionFinishPage
                .checkNumberOfSubscription(1)
                .checkNameOfSubscription(0, subscriptionData.accessInternetName())
                .clickNumberOfSubscription(0);
        switchTo().window(1);
        status.checkStatus(NEW.getValue());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage
                .checkStartDate(date, time)
                .checkPaperOrder(INTERNET.getOrderNote());
        internetRequestPage
                .checkValue(INTERNET_SPEED, internetData.get(INTERNET_SPEED))
                .checkValue(INTERNET_IP, internetData.get(INTERNET_IP));
        Selenide.closeWindow();
        switchTo().window(0);
    }

    @Severity(value = SeverityLevel.BLOCKER)
    @DisplayName("Заказ резервированный доступ в интернет, тестовая")
    @TestType.e2e
    void createAccessInternetTest() {
        mainPageProvider.adminLink(linkProvider.newSubscriptionLink());
        newSubscriptionPageOne
                .setCustomer(userData.customerNewSubscriptionAdmin())
                .setSubscription(subscriptionData.accessInternetName())
                .setTypeSubscription(TYPE_TESTING)
                .setPaperOrder(INTERNET.getOrderNote())
                .clickButton();
        //*************Step 2**********************
        newInternetPage
                .setValue(INTERNET_SPEED, internetData.get(INTERNET_SPEED))
                .setValue(INTERNET_IP, internetData.get(INTERNET_IP))
                .setValue(NOTE, INTERNET.getTestNote())
                .clickButton();
        //***************Проверяем правильность заказанных услуг**************************
        newSubscriptionFinishPage
                .checkNumberOfSubscription(1)
                .checkNameOfSubscription(0, subscriptionData.accessInternetName())
                .clickNumberOfSubscription(0);
        switchTo().window(1);
        status
                .checkStatus(NEW.getValue())
                .checkTestSubscription(TEST.getValue());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage.checkPaperOrder(INTERNET.getOrderNote());
        internetRequestPage
                .checkValue(INTERNET_SPEED, internetData.get(INTERNET_SPEED))
                .checkValue(INTERNET_IP, internetData.get(INTERNET_IP));
        Selenide.closeWindow();
        switchTo().window(0);

    }

    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Заказ Microsoft, коммерческая")
    @TestType.e2e
    void createMicrosoftCommercial() {
        time = dateTime.getNext2Minutes();
        mainPageProvider.adminLink(linkProvider.newSubscriptionLink());
        newSubscriptionPageOne
                .setCustomer(userData.customerNewSubscriptionAdmin())
                .setSubscription(subscriptionData.microsoftName())
                .setTypeSubscription(TYPE_COMMERCIAL)
                .setDate(date, time)
                .setPaperOrder(MICROSOFT.getOrderNote())
                .clickButton();
        //*************Step 2**********************
        newMicrosoftPage
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
                .clickButton();
        //***************Проверяем правильность заказанных услуг**************************
        newSubscriptionFinishPage
                .checkNumberOfSubscription(1)
                .checkNameOfSubscription(0, subscriptionData.microsoftName())
                .clickNumberOfSubscription(0);
        switchTo().window(1);
        status.checkStatus(NEW.getValue());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage
                .checkPaperOrder(MICROSOFT.getOrderNote())
                .checkTariffType(FIX)
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
        Selenide.closeWindow();
        switchTo().window(0);
    }

    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Заказ Microsoft, тестовая")
    @TestType.e2e
    void createMicrosoftTest() {
        mainPageProvider.adminLink(linkProvider.newSubscriptionLink());
        newSubscriptionPageOne
                .setCustomer(userData.customerNewSubscriptionAdmin())
                .setSubscription(subscriptionData.microsoftName())
                .setTypeSubscription(TYPE_TESTING)
                .setPaperOrder(MICROSOFT.getOrderNote())
                .clickButton();
        //*************Step 2**********************
        newMicrosoftPage
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
                .setValue(NOTE, MICROSOFT.getTestNote())
                .clickButton();
        //***************Проверяем правильность заказанных услуг**************************
        newSubscriptionFinishPage
                .checkNumberOfSubscription(1)
                .checkNameOfSubscription(0, subscriptionData.microsoftName())
                .clickNumberOfSubscription(0);
        switchTo().window(1);
        status
                .checkStatus(NEW.getValue())
                .checkTestSubscription(TEST.getValue());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage
                .checkPaperOrder(MICROSOFT.getOrderNote())
                .checkTariffType(FIX);
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
        Selenide.closeWindow();
        switchTo().window(0);
    }

    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Заказ Облачное хранилище, коммерческое")
    @TestType.e2e
    void createCloudStorageCommercial() {
        time = dateTime.getNext2Minutes();
        mainPageProvider.adminLink(linkProvider.newSubscriptionLink());
        newSubscriptionPageOne
                .setCustomer(userData.customerNewSubscriptionAdmin())
                .setSubscription(subscriptionData.cloudStorageName())
                .setTypeSubscription(TYPE_COMMERCIAL)
                .setDate(date, time)
                .setPaperOrder(CLOUD_STORAGE.getOrderNote())
                .clickButton();
        //*************Step 2**********************
        newCloudStoragePage
                .clickOn(STORAGE, subscriptionData.tariffDeveloper())
                .setValue(NOTE, CLOUD_STORAGE.getCommercialNote())
                .clickButton();
        //***************Проверяем правильность заказанных услуг**************************
        newSubscriptionFinishPage
                .checkNumberOfSubscription(1)
                .checkNameOfSubscription(0, subscriptionData.cloudStorageName())
                .clickNumberOfSubscription(0);
        switchTo().window(1);
        status.checkStatus(NEW.getValue());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage
                .checkPaperOrder(CLOUD_STORAGE.getOrderNote())
                .checkTariffType(FACT)
                .checkStartDate(date, time);
        cloudStorageRequestPage.checkValue(STORAGE, subscriptionData.tariffDeveloper());
        Selenide.closeWindow();
        switchTo().window(0);
    }

    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Заказ Облачное хранилище, тестовое")
    @TestType.e2e
    void createCloudStorageTest() {
        mainPageProvider.adminLink(linkProvider.newSubscriptionLink());
        newSubscriptionPageOne
                .setCustomer(userData.customerNewSubscriptionAdmin())
                .setSubscription(subscriptionData.cloudStorageName())
                .setTypeSubscription(TYPE_TESTING)
                .setPaperOrder(CLOUD_STORAGE.getOrderNote())
                .clickButton();
        //*************Step 2**********************
        newCloudStoragePage
                .clickOn(STORAGE, subscriptionData.tariffGeneral())
                .setValue(NOTE, CLOUD_STORAGE.getTestNote())
                .clickButton();
        //***************Проверяем правильность заказанных услуг**************************
        newSubscriptionFinishPage
                .checkNumberOfSubscription(1)
                .checkNameOfSubscription(0, subscriptionData.cloudStorageName())
                .clickNumberOfSubscription(0);
        switchTo().window(1);
        status
                .checkStatus(NEW.getValue())
                .checkTestSubscription(TEST.getValue());
        subscriptionProfilePage.clickOnMenu(REQUESTS.getValue());
        headerSubscriptionRequestPage
                .checkPaperOrder(CLOUD_STORAGE.getOrderNote())
                .checkTariffType(FACT);
        cloudStorageRequestPage.checkValue(STORAGE, subscriptionData.tariffGeneral());
        Selenide.closeWindow();
        switchTo().window(0);
    }
}

