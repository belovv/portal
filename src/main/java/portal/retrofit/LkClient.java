package portal.retrofit;

import okhttp3.Headers;
import portal.config.WebConfig;

import java.io.IOException;


public class LkClient {

    private final LkController controller;

    private WebConfig webConfig = new WebConfig();

    public LkClient() {
        controller = new RetrofitClient(webConfig.getUrlLk()).builder().create(LkController.class);
    }

    public LkClient(String url) {
        controller = new RetrofitClient(url).builder().create(LkController.class);
    }

    public Headers getHeaderLk(String admin, String adminPassword) {
        try {
            return controller.loginUser(admin, adminPassword).execute().headers();
        } catch (IOException e) {
            return null;
        }
    }


}
