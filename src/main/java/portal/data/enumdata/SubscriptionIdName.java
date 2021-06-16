package portal.data.enumdata;

public enum SubscriptionIdName {
    PROTECT("vdc_vmware_2."),
    GHZ("vdc_vmware_2_3ghz."),
    CLOSE("vdc_vmware_closed_2."),
    IX("vdc_vmware_ix."),
    OPENSTACK_CSS("vdc_openstack_3."),
    INTERNET_CSS("reserved_access."),
    BACKUP_CSS("backup."),
    MICROSOFT_CSS("microsoft_software."),
    CLOUD_STORAGE_CSS("cloud_storage_2.");

    private String name;

    SubscriptionIdName(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }

}
