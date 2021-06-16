package portal.lk;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CatalogPage {


    @Step("Находим услугу и нажимаем заказать ")
    public void clickOrderSubscription(String subscriptionName) {
        $("[label='" + subscriptionName + "'] button").click();
    }

    @Step("Проверяем количество услуг для заказа")
    public void checkValueSubscriptions(int value) {
        $$(".ng-bs-main_block").shouldHave(CollectionCondition.size(value));
    }

    @Step("Проверяем текст подписки")
    public void checkTextSubscription(String subscriptionName, String longName, String description) {
        $("[short-label='" + subscriptionName + "'] .bs-media-block_body-header")
                .shouldHave(Condition.exactText(longName));
        $("[short-label='" + subscriptionName + "'] .bs-media-block_body-paragraph")
                .shouldHave(Condition.exactText(description));
    }

}

