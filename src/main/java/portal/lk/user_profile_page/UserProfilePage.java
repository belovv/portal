package portal.lk.user_profile_page;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class UserProfilePage {


    @Step("Кликаем на вкладку'Пароль'")
    public void clickPassword() {
        $(byText("Пароль")).click();
    }
}
