package portal.data.enumdata;

public enum TypeUser {
    CUSTOMER_NEW_SUBSCRIPTION("No Name", "test8992@portal.cloud", "7720288603", "U&*HFD&HU"),
    CUSTOMER_CHECK_NAME_SUBSCRIPTION("No name", "test1045@portal.cloud", "7606069927", "U&*HFD&HU"),
    CUSTOMER_NEW_SUBSCRIPTION_EMAIL("ТПФ \"ДИ-МЕР", "test2035@portal.cloud","7610071510", "U&*HFD&HU"),
    CUSTOMER_CHANGE_SUBSCRIPTION("No name", "test1026@portal.cloud", "6674354092", "U&*HFD&HU"),
    NEW_CUSTOMER("No name", "test1145@portal.cloud", "6154064527","U&*HFD&HU"),
    CUSTOMER_INDIVIDUAL("No name", "test1146@portal.cloud","1646039822","U&*HFD&HU"),
    CUSTOMER_OFFER("No name","test1147@portal.cloud","4345130777","U&*HFD&HU"),
    CUSTOMER_CHANGE_REQUEST("No name", "test1026@portal.cloud", "6674354092","6N7SsuwRE#"),
    USER_ADMIN("Test", "robot@portal.cloud", "freeInn","68&&@h*PQQw)$M");

    private String name;
    private String email;
    private String inn;
    private String password;


    TypeUser(
            String name,
            String email,
            String inn,
            String password){
        this.name = name;
        this.email = email;
        this.inn = inn;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getInn() {
        return inn;
    }

    public String getPassword() {
        return password;
    }

}
