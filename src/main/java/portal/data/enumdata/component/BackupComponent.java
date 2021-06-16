package portal.data.enumdata.component;

public enum BackupComponent {
    TRAFFIC_MIN("traffic_min"),
    VM_COUNT("vm_count"),
    BACKUP_VOLUME("volume"),
    TRAFFIC_SPEED("traffic_speed"),
    CLOUD_PLACEMENT("ts_cloud_placement");

    private String component;

    BackupComponent(String component) {
        this.component = component;
    }

    @Override
    public String toString() {
        return component;
    }
}
