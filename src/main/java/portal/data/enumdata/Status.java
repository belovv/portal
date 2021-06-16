package portal.data.enumdata;

import org.aeonbits.owner.Config;

public enum Status {

    SIGNING_CONTRACT("Заключение договора"),
    SIGNED_CONTRACT("Договор заключен"),
    MAIL_CHECKED ("Почта проверена"),
    NEW ("Новая"),
    TEST ("тестовая"),
    ACTIVE("Активная"),
    DONE("Выполнена");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getValue() {
        return status;
    }
}
