package portal.data.enumdata;

public enum TabName {

    INFORMATION("Информация"),
    ACTION("Действия"),
    STATUS("Статус"),
    REQUESTS("Заявки"),
    VOLUME("Объемы");

    private String name;

    TabName(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}

