package portal.utils;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class PopUp {
    @Step("Проверяем текст всплывающего сообщения")
    public void checkMessageText(String textMessage) {
        $(".toast-title").shouldHave(Condition.exactText(textMessage)).click();
    }
}
