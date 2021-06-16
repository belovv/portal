package portal.retrofit;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Headers;
import portal.config.WebConfig;

import java.io.IOException;

public class AdminClient {

    private final AdminController controller;

    private WebConfig webConfig = new WebConfig();

    public AdminClient() {
        controller = new RetrofitClient(webConfig.getUrlAdmin()).builder().create(AdminController.class);
    }

    public AdminClient(String url) {
        controller = new RetrofitClient(url).builder().create(AdminController.class);
    }

    public Headers getHeaderAdmin(String admin, String adminPassword) {
        try {
            return controller.loginAdmin(admin, adminPassword).execute().headers();
        } catch (IOException e) {
            return null;
        }
    }

    public JsonNode createCustomerAdmin(String token, String password, String notification, String name,
                                        String phone, String email, String inn) {
        try {
            return controller.sendNewCustomerDataFromAdmin(token, password, null, notification,
                    name, phone, email, inn).execute().body();
        } catch (IOException e) {
            return null;
        }
    }

    public JsonNode createManagerAdmin(String tokenAdmin, String typeManager, String emailManager, String userRole) {
        try {
            return controller.sendNewUserDataFromAdmin(tokenAdmin, typeManager, emailManager, userRole).execute().body();
        } catch (IOException e) {
            return null;
        }
    }

    public JsonNode setManagersToCustomer(String tokenAdmin, String customerId, String salesMangerId, String serviceMangerId) {
        try {
            return controller.setMangersToCustomer(customerId, tokenAdmin, salesMangerId, serviceMangerId).execute().body();
        } catch (IOException e) {
            return null;
        }
    }

    public JsonNode requestContract(String tokenAdmin, String customerId, String notification, String contractType) {
        try {
            return controller.requestContract(customerId, tokenAdmin, notification, contractType).execute().body();
        } catch (IOException e) {
            return null;
        }
    }

    public JsonNode approveContract(String tokenAdmin, String customerId, String notification) {
        try {
            return controller.approveContract(customerId, tokenAdmin, notification).execute().body();
        } catch (Exception e) {
            return null;
        }
    }

    public JsonNode createSubscription(JsonNode body, String adminToken) {
        try {
            return controller.createSubscription(body, adminToken).execute().body();
        } catch (IOException e) {
            return null;
        }
    }

    public JsonNode changeOrderStatus(String adminToken, String requestId, String notification, String orderStatus) {
        try {
            return controller.changeOrderStatus(requestId, adminToken, notification, orderStatus).execute().body();
        } catch (IOException e) {
            return null;
        }
    }


}
