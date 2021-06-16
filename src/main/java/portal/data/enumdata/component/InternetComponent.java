package portal.data.enumdata.component;

public enum InternetComponent {
    INTERNET_IP("public_ips"),
    INTERNET_SPEED("inet_access");

    private String component;

    InternetComponent(String component) {
        this.component = component;
    }

    @Override
    public String toString() {
        return component;
    }
}
