package portal.data.enumdata;

public enum ContractType {

    INDIVIDUAL_CONTRACT("Индивидуальный договор"),
    OFFER_CONTRACT("Договор публичной оферты");

    private String name;

    ContractType(String status) {
        this.name = status;
    }

    public String getValue() {
        return name;
    }
}

