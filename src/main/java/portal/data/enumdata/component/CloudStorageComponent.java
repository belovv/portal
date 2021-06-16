package portal.data.enumdata.component;

public enum CloudStorageComponent {
    STORAGE("billing_type");

    private String component;

    CloudStorageComponent(String component) {
        this.component = component;
    }

    @Override
    public String toString() {
        return component;
    }
}
