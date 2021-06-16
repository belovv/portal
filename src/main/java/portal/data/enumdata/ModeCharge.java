package portal.data.enumdata;

public enum ModeCharge {
    FIX("Фиксированный"),
    FACT("По фактическому потреблению");
    private String mode;

    ModeCharge(String mode) {
        this.mode = mode;
    }

    public String getValue() {
        return mode;
    }
}

