package portal.data;


import org.aeonbits.owner.Config;

public interface SubscriptionData extends Config {

    @DefaultValue("Номер бумажного заказа")
    String paperOrderName();

    @DefaultValue("Схема тарификации")
    String tariffName();

    @DefaultValue("Оплата по факту")
    String tariffFact();

    @DefaultValue("Фиксированная оплата")
    String tariffFix();

    @DefaultValue("Заметка")
    String note();

    //***Название услуг checkBox*****
    @DefaultValue("Резервное копирование")
    String checkBoxBackup();

    @DefaultValue("Резервированный доступ в Интернет")
    String checkBoxInet();

    @DefaultValue("Программные услуги на базе ПО Microsoft")
    String checkBoxMicrosoft();

    //****Название услуг*******************
    @DefaultValue("IX Виртуальный Дата Центр \"Защищенный\" VMware")
    String vmwareIXName();

    @DefaultValue("IX VDC VMware")
    String vmwareIXShortName();

    @DefaultValue("IX Виртуальный дата-центр для бизнес-систем")
    String vmwareIXDescription();

    //--------------------------
    @DefaultValue("VDC 3Ghz+ VMware")
    String vmware3GHzShortName();

    @DefaultValue("Виртуальный Дата Центр 3+ ГГц VMware")
    String vmware3GHzName();

    @DefaultValue("Виртуальный дата-центр для высоконагруженных систем и разработки")
    String vmware3GHzDescription();

    //------------------------
    @DefaultValue("VDC VMware")
    String vmwareProtectShortName();

    @DefaultValue("Виртуальный Дата Центр \"Защищенный\" VMware")
    String vmwareProtectName();

    @DefaultValue("Виртуальный дата-центр для бизнес-систем. Сертифицирован по PCI DSS")
    String vmwareProtectDescription();

    //-----------------------
    @DefaultValue("VDC VMware 152-ФЗ")
    String vmwareCloseShortName();

    @DefaultValue("Виртуальный Дата Центр \"Закрытый\"")
    String vmwareCloseName();

    @DefaultValue("Виртуальный дата-центр, соответствующий 152-ФЗ и приказам ФСТЭК России.")
    String vmwareCloseDescription();

    //----------------------------
    @DefaultValue("VDC Openstack")
    String openstackShortName();

    @DefaultValue("Виртуальный Дата Центр \"Защищенный\" OpenStack")
    String openstackName();

    @DefaultValue("Экономически эффективный виртуальный дата-центр.")
    String openstackDescription();

    //----------------------------
    @DefaultValue("Backup")
    String backupShortName();

    @DefaultValue("Резервное копирование")
    String backupName();

    //---------------------------
    @DefaultValue("Internet")
    String accessInternetShortName();

    @DefaultValue("Резервированный доступ в Интернет")
    String accessInternetName();

    //---------------------------
    @DefaultValue("Microsoft Software")
    String microsoftShortName();

    @DefaultValue("Программные услуги на базе ПО Microsoft")
    String microsoftName();

    //-----------------------------
    @DefaultValue("Object Storage")
    String cloudStorageShortName();

    @DefaultValue("Облачное хранилище")
    String cloudStorageName();

    @DefaultValue("Облачное хранилище S3 – надежное и недорогое хранение и организация доступа к файлам.")
    String cloudStorageDescription();

    //--------------------------------

    @DefaultValue("Виртуальный процессор (vCPU)")
    String cpuName();

    @DefaultValue("Виртуальная оперативная память (vRAM)")
    String ramName();

    @DefaultValue("Дисковое пространство типа HDD 7K (Гб)")
    String hdd7KName();

    @DefaultValue("Дисковое пространство типа HDD 10K (Гб)")
    String hdd10KName();

    @DefaultValue("Дисковое пространство типа HDD Hybrid (Гб)")
    String hddHybridName();

    @DefaultValue("Дисковое пространство типа SSD (Гб)")
    String ssdName();

    @DefaultValue("Дисковое пространство типа SSD AF (Гб)")
    String ssdAFName();

    @DefaultValue("Предоставление порта для USB-носителя")
    String usbName();

    //******backup**************

    @DefaultValue("Минимальный гарантированный объем превалирующего(входящего/исходящего от канала заказчика) трафика Заказчика")
    String trafficMinName();

    @DefaultValue("Резервное копирование виртуальных машин")
    String backupVmName();

    @DefaultValue("Пространство для хранения резервных копий")
    String volumeName();

    @DefaultValue("Размещение в ts-cloud")
    String cloudName();

    @DefaultValue("Да")
    String setCloud();

    @DefaultValue("Емкость обычного порта доступа в интернет(емкость порта для доступа к каналу Заказчика)")
    String trafficSpeedName();

    //*************accessInternet*****************
    @DefaultValue("Скорость доступа в Интернет")
    String inetSpeedName();

    @DefaultValue("Публичные IP")
    String ipName();

    //*****************Microsoft*********************

    @DefaultValue("Visio Professional")
    String visioProfessionalName();

    @DefaultValue("SharePoint Server Standard")
    String sharePointServerStandardName();

    @DefaultValue("Core Infr. Server Suite Standard")
    String serverSuiteStandardName();

    @DefaultValue("SQL Server Standard 2 ядра")
    String sqlServerStandardCoreName();

    @DefaultValue("Exchange Basic, 1 пользователь")
    String exchangeBasicName();

    @DefaultValue("Exchange Enterprise Plus, 1 пользователь")
    String exchangeEnterprisePlusName();

    @DefaultValue("Visio Standard")
    String visioStandardName();

    @DefaultValue("Project Professional")
    String projectProfessionalName();

    @DefaultValue("Windows Server DataCenter")
    String windowsServerDataCenterName();

    @DefaultValue("SQL Server Enterprise 2 ядра")
    String sqlServerEnterpriseName();

    @DefaultValue("Microsoft Office Standard 1 пользователь")
    String officeStandardName();

    @DefaultValue("Exchange Enterprise, 1 пользователь")
    String exchangeEnterpriseName();

    @DefaultValue("Microsoft Office Professional Plus")
    String officeProfessionalPlusName();

    @DefaultValue("Project Server")
    String projectServerName();

    @DefaultValue("Windows Server Standard")
    String windowsServerStandardName();

    @DefaultValue("Служба удаленного монитора")
    String remoteDesktopServiceName();

    @DefaultValue("SQL Server Standard 1 пользователь")
    String sqlServerStandardName();

    @DefaultValue("Exchange Standard Plus, 1 пользователь")
    String exchangeStandardPlusName();

    @DefaultValue("Project")
    String projectName();

    @DefaultValue("SharePoint Server Professional")
    String sharePointServerProfessionalName();

    @DefaultValue("Core Infr. Server Suite DataCenter")
    String serverSuiteDataCenterName();

    @DefaultValue("SQL Server Web 2 ядра")
    String sqlServerWebName();

    @DefaultValue("Exchange Standard, 1 пользователь")
    String exchangeStandardName();

    @DefaultValue("Windows server standard, на виртуальную машину")
    String windowsServerStandardVirtualMachineName();

    @DefaultValue("Rights Management Services, 1 пользователь")
    String rightsManagementServicesName();

    @DefaultValue("Microsoft Office Multi Language Pack, 1 пользователь")
    String microsoftOfficeMultiLanguageName();

    @DefaultValue("VisualStudio, 1 пользователь")
    String visualStudioName();

    @DefaultValue("VisualStudio Basic, 1 пользователь")
    String visualStudioBasicName();

    @DefaultValue("VisualStudio Professional, 1 пользователь")
    String visualStudioProfessionalName();

    @DefaultValue("VisualStudio Test Professional , 1 пользователь")
    String visualStudioTestProfessionalName();

    @DefaultValue("VisualStudio Enterprise, 1 пользователь")
    String visualStudioEnterpriseName();

    @DefaultValue("Microsoft SharePoint Server, 1 пользователь")
    String microsoftSharePointServerName();

    @DefaultValue("SharePoint Hosting, 1 сервер")
    String microsoftSharePointHostingName();

    //***********Openstack****************
    @DefaultValue("Дисковое пространство типа HDD 7K SDS (Гб)")
    String hdd7KSDSName();

    @DefaultValue("Дисковое пространство типа HDD 10K SDS (Гб)")
    String hdd10KSDSName();

    @DefaultValue("Дисковое пространство типа SSD SDS (Гб)")
    String hddSSDSDSName();

    //***************CloudStorage****************
    @DefaultValue("Режим тарификации (клиентский Тариф)")
    String cloudStorageTariffName();

    @DefaultValue("Для разработчиков")
    String tariffDeveloper();

    @DefaultValue("Единый")
    String tariffGeneral();

    @DefaultValue("Нет")
    String setEditCloud();

    //тестовые подписки
    @DefaultValue("Перевод в коммерческий режим")
    String subscriptionTestComment();

    //тестовые подписки
    @DefaultValue("Продлеваем тестирование")
    String subscriptionExtendTestingComment();

}
