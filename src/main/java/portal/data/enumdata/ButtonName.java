package portal.data.enumdata;

public enum ButtonName {

    APPROVE_CONTRACT("Договор заключен"),
    CHANGE("Изменить"),
    SAVE("Сохранить"),
    CANCEL("Отменить"),
    CHOOSE_TYPE_CONTRACT ("Выбрать тип договора"),
    EXTEND_TESTING ("Продлить тестирование на 14 дней");


    private String name;

    ButtonName(String status) {
        this.name = status;
    }

    public String getValue() {
        return name;
    }
}

