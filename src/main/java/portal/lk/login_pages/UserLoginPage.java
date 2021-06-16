package portal.lk.login_pages;


import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

public class UserLoginPage {


    @Step("Вводим почту и пароль")
    public void inputData(String email, String password) {
        $("#signin_email").setValue(email);
        $("#signin_password").setValue(password);
    }

    @Step("Кликаем на кнопку 'Войти")
    public void clickLoginButton() {
        $(".signin_submit-button").click();
    }


    @Step("Вводим почту {email}")
    public void inputEmail(String email) {
        $(".bs-control-text").setValue(email);
    }

    @Step("Нажимаем на 'Отправить'")
    public void clickSend() {
        $(".bs-button").click();
    }

    @Step("Проверяем текст предупреждения")
    public void checkWarning(String text) {
        $(".bs-control-hint").shouldHave(exactText(text));
    }

    @Step("Кликаем на 'Восстановить пароль'")
    public void clickRestorePassword() {
        $("[ui-sref='restorePassword']").click();
    }
}
