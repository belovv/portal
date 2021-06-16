package portal.providers;

import portal.admin.order_subscription_page.new_subscriptions_page.NewSubscriptionPage;
import portal.admin.order_subscription_page.subscription_profile_pages.SubscriptionRequestPage;
import portal.data.SubscriptionData;
import portal.data.enumdata.SubscriptionIdName;
import portal.lk.NewSubscriptionOrderPage;

import static portal.data.enumdata.SubscriptionIdName.*;

public class SubscriptionPageFactory {

    private SubscriptionData subscriptionData = new DataProvider().subscriptionData();

    public NewSubscriptionPage getNewSubscriptionPage(SubscriptionIdName type) {
        return switch (type) {
            case IX -> new NewSubscriptionPage(IX);
            case GHZ -> new NewSubscriptionPage(GHZ);
            case CLOSE -> new NewSubscriptionPage(CLOSE);
            case PROTECT -> new NewSubscriptionPage(PROTECT);
            case OPENSTACK_CSS -> new NewSubscriptionPage(OPENSTACK_CSS);
            case CLOUD_STORAGE_CSS -> new NewSubscriptionPage(CLOUD_STORAGE_CSS);
            case INTERNET_CSS -> new NewSubscriptionPage(INTERNET_CSS);
            case MICROSOFT_CSS -> new NewSubscriptionPage(MICROSOFT_CSS);
            case BACKUP_CSS -> new NewSubscriptionPage(BACKUP_CSS);
            default -> throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        };
    }

    public SubscriptionRequestPage getRequestPage(SubscriptionIdName type) {
        return switch (type) {
            case IX -> new SubscriptionRequestPage(IX);
            case GHZ -> new SubscriptionRequestPage(GHZ);
            case CLOSE -> new SubscriptionRequestPage(CLOSE);
            case PROTECT -> new SubscriptionRequestPage(PROTECT);
            case OPENSTACK_CSS -> new SubscriptionRequestPage(OPENSTACK_CSS);
            case CLOUD_STORAGE_CSS -> new SubscriptionRequestPage(CLOUD_STORAGE_CSS);
            case INTERNET_CSS -> new SubscriptionRequestPage(INTERNET_CSS);
            case MICROSOFT_CSS -> new SubscriptionRequestPage(MICROSOFT_CSS);
            case BACKUP_CSS -> new SubscriptionRequestPage(BACKUP_CSS);
            default -> throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        };
    }

    public NewSubscriptionOrderPage getCustomerOrderPage(SubscriptionIdName type) {
        return switch (type) {
            case IX -> new NewSubscriptionOrderPage(IX);
            case GHZ -> new NewSubscriptionOrderPage(GHZ);
            case CLOSE -> new NewSubscriptionOrderPage(CLOSE);
            case PROTECT -> new NewSubscriptionOrderPage(PROTECT);
            case OPENSTACK_CSS -> new NewSubscriptionOrderPage(OPENSTACK_CSS);
            case CLOUD_STORAGE_CSS -> new NewSubscriptionOrderPage(CLOUD_STORAGE_CSS);
            case INTERNET_CSS -> new NewSubscriptionOrderPage(INTERNET_CSS);
            case MICROSOFT_CSS -> new NewSubscriptionOrderPage(MICROSOFT_CSS);
            case BACKUP_CSS -> new NewSubscriptionOrderPage(BACKUP_CSS);
            default -> throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        };
    }
}
