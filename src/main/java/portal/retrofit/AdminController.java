package portal.retrofit;

import com.fasterxml.jackson.databind.JsonNode;
import retrofit2.Call;
import retrofit2.http.*;

public interface AdminController {

    @FormUrlEncoded
    @Headers("Content-Type: application/json")
    @POST("api/0/auth/")
    Call<JsonNode> loginAdmin(
            @Field("email") String email,
            @Field("password") String password);


    @FormUrlEncoded
    @Headers({"Content-Type: application/json"})
    @POST("api/0/user/signup/")
    Call<JsonNode> sendNewCustomerDataFromAdmin(
            @Header("Cookie") String admin_token,
            @Field("password") String customerPassword,
            @Field("password_repeat") String repeatCustomerPassword,
            @Field("mark") String mark,
            @Field("name") String customerName,
            @Field("note") String note,
            @Field("email") String newEmail,
            @Field("itn_psrn") String newCustomerInnAPI);

    @FormUrlEncoded
    @Headers({"Content-Type: application/json"})
    @POST("api/0/user/")
    Call<JsonNode> sendNewUserDataFromAdmin(
            @Header("Cookie") String admin_token,
            @Field("name") String userName,
            @Field("email") String newUserEmail,
            @Field("view") String view);

    @FormUrlEncoded
    @Headers({"Content-Type: application/json"})
    @PUT("api/0/user/{userID}/")
    Call<JsonNode> setMangersToCustomer(
            @Path("userID") String customerId,
            @Header("Cookie") String admin_token,
            @Field("role") String role,
            @Field("type") String type);

    @FormUrlEncoded
    @Headers({"Content-Type: application/json"})
    @POST("api/0/user/{customerUserID}/contract/")
    Call<JsonNode> requestContract(
            @Path("customerUserID") String customerId,
            @Header("Cookie") String admin_token,
            @Field("mark") String mark,
            @Field("contract") String contractType);

    @FormUrlEncoded
    @Headers({"Content-Type: application/json"})
    @POST("api/0/user/{customerUserID}/production/")
    Call<JsonNode> approveContract(
            @Path("customerUserID") String customerId,
            @Header("Cookie") String admin_token,
            @Field("mark") String mark);

    @Headers({"Content-Type: application/json"})
    @POST("api/0/service_request/")
    Call<JsonNode> createSubscription(
            @Body JsonNode body,
            @Header("Cookie") String admin_token);

    @FormUrlEncoded
    @Headers({"Content-Type: application/json"})
    @PUT("api/0/service_request/{requestID}/state/")
    Call<JsonNode> changeOrderStatus(
            @Path("requestID") String requestId,
            @Header("Cookie") String admin_token,
            @Field("mark") String mark,
            // @Query("oldState") String oldState,
            @Field("state") String orderState);
}
