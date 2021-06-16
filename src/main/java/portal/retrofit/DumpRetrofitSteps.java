package portal.retrofit;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import okhttp3.Headers;
import portal.config.WebConfig;
import portal.data.UserData;
import portal.providers.DataProvider;

import java.net.HttpCookie;

import static portal.data.enumdata.TypeUser.NEW_CUSTOMER;

public class DumpRetrofitSteps {

    private final AdminClient adminClient = new AdminClient(new WebConfig().getApiKitchen());
    private DataProvider dataProvider = new DataProvider();
    private UserData userData = dataProvider.userData();

    @Step("Получаем admin_token")
    public String getTokenAdmin(String user, String password) {
        try {
            Headers headers = adminClient.getHeaderAdmin(user, password);
            return HttpCookie.parse(headers.get("Set-Cookie")).get(0).toString();
        } catch (Exception e) {
            return null;
        }
    }

    @Step("Создаем менеджера {typeManager} из админки")
    public String createManagerAdmin(String tokenAdmin, String typeManager, String emailManager, String userRole) {

        var sendCustomerData = adminClient.createManagerAdmin(tokenAdmin, typeManager, emailManager, userRole);
        return sendCustomerData.get("user_info").get("user_id").asText();
    }

    @Step("Создаем заказчика из админки")
    public String createCustomerAdmin(String tokenAdmin, String customerEmail, String customerInn) {
        var sendCustomerData = adminClient.createCustomerAdmin(
                tokenAdmin,
                NEW_CUSTOMER.getPassword(),
                userData.disableNotificationTrue(),
                dataProvider.getFullName(),
                dataProvider.getPhoneNumber(),
                customerEmail,
                customerInn);
        return sendCustomerData.get("customer_info").get("customer_id").asText();
    }

    @Step("Присваиваем менеджеров заказчику")
    public void setManagersToCustomer(String tokenAdmin, String customerId, String salesMangerId, String serviceMangerId) {
        adminClient.setManagersToCustomer(tokenAdmin, customerId, salesMangerId, serviceMangerId);
    }

    @Step("Запрашиваем договор")
    public void requestContract(String tokenAdmin, String customerId, String notification, String contractType) {
        adminClient.requestContract(tokenAdmin, customerId, notification, contractType);
    }

    @Step("Одобряем договор")
    public void approveContract(String tokenAdmin, String customerId, String notification) {
        adminClient.approveContract(tokenAdmin, customerId, notification);
    }

    @Step("Создаем подписку для {customerId}")
    public String createSubscription(String tokenAdmin, String services, String customerId, boolean trial, String launchDate) {
        var jsonString = "{" + services +
                "json" +
                "json " + customerId + "," +
                "json: " + trial + "," +
                "json" +
                "\"date\":\"" + launchDate + "\"}";
        var mapper = new ObjectMapper();
        JsonNode json = null;
        try {
            json = mapper.readTree(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        var infoSubscription = adminClient.createSubscription(json, tokenAdmin);
        return infoSubscription.get("service_requests").findValue("request_id").asText();
    }

    @Step("Изменяем статус заказа")
    public void changeOrderStatus(String adminToken, String requestId, String notification, String orderStatus) {
        adminClient.changeOrderStatus(adminToken, requestId, notification, orderStatus);
    }
}
