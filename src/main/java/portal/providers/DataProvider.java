package portal.providers;

import com.github.javafaker.Faker;
import org.aeonbits.owner.ConfigFactory;
import portal.data.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DataProvider {

    private final Faker faker = new Faker(new Locale("ru"));

    public String getFullName() {
        return faker.name().fullName();
    }

    public String getPhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }

    public String getValidEmailAddress() {
        return faker.bothify("test###@portal.cloud");
    }

    public String getUnValidEmailAddress() {
        return faker.bothify("unValid###@portal.cloud");
    }

    public Map<Enum, String> getComponentsValue(Enum[] componentsList) {
        Map<Enum, String> randomData = new HashMap<>();
        for (Enum component : componentsList) {
            randomData.put(component, String.valueOf(faker.number().numberBetween(10, 1000)));
        }
        return randomData;
    }

    public String getRandomNumber(){
        return String.valueOf(faker.number().numberBetween(10,1000));
    }


    public SubscriptionData subscriptionData() {
        return ConfigFactory.create(SubscriptionData.class);
    }

    public LinkProvider linkProvider() {
        return ConfigFactory.create(LinkProvider.class);
    }

    public PopUpMessage popUpMessage() {
        return ConfigFactory.create(PopUpMessage.class);
    }

    public EmailMessage emailMessage() {
        return ConfigFactory.create(EmailMessage.class);
    }

    public UserData userData() {
        return ConfigFactory.create(UserData.class);
    }

    public LkData lkData() {
        return ConfigFactory.create(LkData.class);
    }

}
