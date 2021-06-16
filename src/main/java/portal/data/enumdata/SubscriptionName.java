package portal.data.enumdata;

public enum SubscriptionName {
    VMWARE_PROTECT("VMware защищенная"),
    VMWARE_3GHZ("VMware 3ГГц"),
    VMWARE_CLOSE("VMware закрытая"),
    VMWARE_IX("VMwareIX"),
    OPENSTACK("Openstack"),
    INTERNET("Доступ в интернет"),
    BACKUP("Резервное копирование"),
    MICROSOFT("Майкрософт"),
    CLOUD_STORAGE("Облачное хранилище");

    private String name;

    SubscriptionName(String name) {
        this.name = name;
    }

    public String getEditNote() {
        return "Редактируем заявку" + name;
    }

    public String getChangeRequestNote(){
        return "Заявка на изменение" + name;
    }

    public String getOrderNote(){
        return "Заказ " + name;
    }

    public String getTestNote(){
        return "Тестовая " + name;
    }

    public String getCommercialNote(){
        return "Коммерческая " + name;
    }
}
