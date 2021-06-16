package portal.data;

import portal.data.enumdata.SubscriptionIdName;
import portal.providers.DataProvider;

import static portal.data.enumdata.SubscriptionIdName.*;

public class ApiSubscriptionData {
    private final DataProvider provider = new DataProvider();

    public String getDate(SubscriptionIdName type) {
        return switch (type) {
            case PROTECT -> vmwareProtected();
            case GHZ -> vmware3GHz();
            case CLOSE -> vmwareClosed();
            case IX -> vmwareIX();
            case OPENSTACK_CSS -> openstack();
            case INTERNET_CSS -> internet();
            case BACKUP_CSS -> backup();
            case MICROSOFT_CSS -> microsoft();
            case CLOUD_STORAGE_CSS -> cloudStorage();
            default -> throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        };
    }

    public String vmwareIX() {

        return "json";
    }

    public String vmware3GHz() {
        return "json";
    }

    public String vmwareProtected() {
        return "json";
    }

    public String vmwareClosed() {
        return "json";
    }

    public String openstack() {
        return "json";
    }

    public String internet() {
        return "json";
    }

    public String backup() {
        return "json";
    }

    public String microsoft() {
        return "json";
    }

    public String cloudStorage() {
        return "json";

    }
}
