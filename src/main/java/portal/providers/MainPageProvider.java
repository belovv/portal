package portal.providers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import portal.config.WebConfig;
import portal.data.enumdata.TypeUser;
import portal.retrofit.RetrofitSteps;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static portal.data.enumdata.TypeUser.USER_ADMIN;

public class MainPageProvider {
    private final TypeUser user;
    private final RetrofitSteps retrofitSteps = new RetrofitSteps();
    private final WebConfig webConfig = new WebConfig();

    public MainPageProvider(TypeUser user) {
        this.user = user;
    }

    public MainPageProvider() {
        this.user = USER_ADMIN;
    }

    private Cookie getCookie() {

        return switch (user) {
            case USER_ADMIN -> retrofitSteps.getCookieAdmin(USER_ADMIN.getEmail(), USER_ADMIN.getPassword());
            default -> retrofitSteps.getCookieUser(user.getEmail(), user.getPassword());
        };
    }

    public void openLkLink(String link) {
        open(webConfig.getApiEnv());
        step("Добавляем куки в браузер", (step) -> {
            WebDriverRunner.getWebDriver().manage().addCookie(getCookie());
        });
        Configuration.baseUrl = webConfig.getUrlLk();
        open(link);
    }

    @Step("Входим с логином и паролем в Админку и идем по прямой ссылке")
    public void adminLink(String link) {
        open(webConfig.getApiEnv());
        step("Добавляем куки в браузер", (step) -> {
            WebDriverRunner.getWebDriver().manage().addCookie(getCookie());
        });
        Configuration.baseUrl = webConfig.getUrlAdmin();
        open(link);
    }
}
