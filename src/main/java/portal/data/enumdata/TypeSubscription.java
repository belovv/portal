package portal.data.enumdata;

public enum TypeSubscription {

    COMMERCIAL("Коммерческая услуга"),
    TESTING ("Тестовая услуга"),
    TYPE_COMMERCIAL("Коммерческий"),
    TYPE_TESTING("Тестовый");

    private String type;

    TypeSubscription(String type) {
        this.type = type;
    }

    public String getValue() {
        return type;
    }
}
