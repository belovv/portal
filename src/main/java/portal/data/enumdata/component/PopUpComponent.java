package portal.data.enumdata.component;

public enum PopUpComponent {
    STATE("state"),
    MODE_CHARGE("mode_charge");

    private String component;
    PopUpComponent(String component) {
        this.component = component;
    }

    @Override
    public String toString() {
        return component;
    }
}
