package portal.data.enumdata.component;

public enum OpenstackComponent {
    OPENSTACK_VCPU("vcpu"),
    OPENSTACK_VRAM("vram"),
    OPENSTACK_HDD_7K("hdd_basic_sds"),
    OPENSTACK_HDD_10K("hdd_standard_sds"),
    OPENSTACK_SSD_SDS("hdd_fast_sds");

    private String component;

    OpenstackComponent(String component) {
        this.component = component;
    }

    @Override
    public String toString() {
        return component;
    }
}
