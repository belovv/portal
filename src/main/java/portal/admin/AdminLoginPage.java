package portal.admin;


import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class AdminLoginPage {

    @Step("Вводим почту и пароль")
    public AdminLoginPage inputData(String email, String password) {
        $x("//input[@name='email']").setValue(email);
        $x("//input[@name='password']").setValue(password);
        return this;
    }

    @Step("Проверяем сообщение о ошибке")
    public void checkErrorMessage(String errorMessages) {
        $x("//*[contains(@class,'bs-signin_error-message')]").shouldHave(Condition.exactText(errorMessages));
    }

    @Step("Кликаем на кнопку 'Войти'")
    public void clickLoginButton() {
        $x("//button[@type='submit']").click();
    }


}
