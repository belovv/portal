package portal.retrofit;


import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import portal.config.WebConfig;

import java.net.HttpCookie;

public class RetrofitSteps {

    private final AdminClient adminClient = new AdminClient(new WebConfig().getApiEnv());
    private final LkClient lkClient = new LkClient(new WebConfig().getApiEnv());

    @Step("Создаем куки из cabinet_token")
    public Cookie getCookieUser(String user, String password) {
        return new Cookie("cabinet_token", getTokenLk(user, password));
    }

    @Step("Создаем куки из admin_token")
    public Cookie getCookieAdmin(String user, String password) {
        return new Cookie("admin_token", getTokenAdmin(user, password));
    }

    @Step("Получаем admin_token")
    public String getTokenAdmin(String user, String password) {
        try {
            var headers = adminClient.getHeaderAdmin(user, password);
            return HttpCookie.parse(headers.get("Set-Cookie")).get(0).toString().split("=")[1];
        } catch (Exception e) {
            return null;
        }
    }

    protected String getTokenLk(String user, String password) {
        try {
            var headers = lkClient.getHeaderLk(user, password);
            return HttpCookie.parse(headers.get("Set-Cookie")).get(0).toString().split("=")[1];
        } catch (Exception e) {
            return null;
        }
    }

}
