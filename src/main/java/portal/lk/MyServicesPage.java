package portal.lk;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MyServicesPage {
    @Step("Кликаем на вкладку 'Новые' ")
    public void clickNewServices() {
        $(byText("Новые")).click();
    }

    //Todo поставить Step
    @Step("Проверяем что сообщение содержит {text}")
    public void checkText(String text) {
        $(".ng-bs-my-services_informer").shouldHave(text(text));
    }

    @Step("Проверяем что подписка {subscriptionName} содержит следующий текст {longName}")
    public void checkTextSubscription(String subscriptionName, String longName) {
        $("[short-label='" + subscriptionName + "'] .bs-media-block_body-header").shouldHave(exactText(longName));
    }

    @Step("Кликаем на коммерческую заказанную подписку {subscriptionName}")
    public void clickOnCommercialSubscription(String subscriptionName) {
        $("[short-label='" + subscriptionName + "'] .bs-media-block-my-services_state:only-child").click();
    }

    @Step("Кликаем на тестовую заказанную подписку {subscriptionName}")
    public void clickOnTestSubscription(String subscriptionName) {
        $("[short-label='" + subscriptionName + "']  .bs-media-block-my-services_trial-state").click();
    }

    @Step("Кликаем на активную подписку {subscriptionName}")
    public void clickOnActiveSubscription(String subscriptionName) {
        $("[short-label='" + subscriptionName + "']").click();
    }


}
