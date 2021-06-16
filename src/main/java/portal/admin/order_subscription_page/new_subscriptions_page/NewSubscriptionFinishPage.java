package portal.admin.order_subscription_page.new_subscriptions_page;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$$;

public class NewSubscriptionFinishPage {
    @Step("Проверяем название услуги")
    public NewSubscriptionFinishPage checkNameOfSubscription(int numberPosition, String name) {
        $$(".bs-subscription-new_word-break-content").get(numberPosition).shouldHave(Condition.exactText(name));
        return this;
    }

    @Step("Количество заказанных услуг должно быть не больше указанного")
    public NewSubscriptionFinishPage checkNumberOfSubscription(int number) {
        $$(".bs-table-new_row.bs-table-new_usual-row").shouldHave(size(number));
        return this;
    }

    @Step("Кликаем на номеру подписки")
    public void clickNumberOfSubscription(int number) {
        $$(".bs-table-new_usual-row").get(number).$("[target='_blank']").click();
    }
}
