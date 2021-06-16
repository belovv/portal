package portal.data.enumdata.component;

public enum VMwareComponent {
    VCPU("vcpu"),
    VRAM("vram"),
    CPU("vmware.cpu"),
    RAM("vmware.ram"),
    HDD_7K("hdd_basic"),
    HDD_10K("hdd_standard"),
    HDD_HYBRID("hdd_medium"),
    SSD("hdd_high"),
    SSD_AF("ssd_high"),
    USB("usb_port"),
    NOTE("note"),
    SCHEME_PRICING("scheme_pricing");

    private String component;
    VMwareComponent(String component) {
        this.component = component;
    }

    @Override
    public String toString() {
        return component;
    }
}
