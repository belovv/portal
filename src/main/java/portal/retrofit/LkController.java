package portal.retrofit;

import com.fasterxml.jackson.databind.JsonNode;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LkController {

    @FormUrlEncoded
    @Headers("Content-Type: application/json")
    @POST("lk_api/0/auth/")
    Call<JsonNode> loginUser(
            @Field("email") String email,
            @Field("password") String password);


}

