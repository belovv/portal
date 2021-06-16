package portal.utils;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class Status {

    @Step("Получаем статус")
    public Status checkStatus(String status) {
        $(".bs-state-mark").shouldHave(Condition.exactText(status));
        return this;
    }

    @Step("Проверяем что услуга тестовая")
     public void checkTestSubscription(String testStatus){
        $(".bs-info-block_mark--native").shouldHave(Condition.exactText(testStatus));
    }
}
