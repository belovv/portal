package portal.lk;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class NewSubscriptionRequestPage {

    @Step("Проверяем текст и количество ")
    public NewSubscriptionRequestPage check(String name, String value){
        $x("//*[text()=' " + name + " ']/../.. //span").shouldHave(exactText(value));
        return this;
    }

    @Step("получаем номер подписки")
    public String getOrderAdminLink(){
        String order =  $(".bs-page-header").getText().split("подписка ")[1];
        return "/subscriptions/list/" + order + "/request?request_sort=-created";
    }

}
