package portal.admin.order_subscription_page.new_subscriptions_page;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import portal.data.enumdata.TypeSubscription;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class NewSubscriptionPageOne {

    @Step("Проверяем что мы на странице Новая подписка")
    public void checkNewSubscriptionTitle() {
        $(".bs-section-title").shouldHave(text("НОВАЯ ПОДПИСКА:"));
    }

    @Step("Выбираем заказчика {customer}")
    public NewSubscriptionPageOne setCustomer(String customer) {
        $("[data-field='customer'] span").click();
        $("[data-field='customer'] input[type='search']").setValue(customer);
        $("[data-field='customer'] [item='item']").click();
        return this;
    }

    @Step("Проверяем текст")
    public NewSubscriptionPageOne checkFieldCustomer() {
        $("[data-field='customer'] label").shouldHave(exactText("Заказчик"));
        return this;
    }

    @Step("Выбираем услугу {name}")
    public NewSubscriptionPageOne setSubscription(String name) {
        $("[data-field='group_id'] select").click();
        $("[data-field='group_id'] [label='" + name + "']").click();
        return this;
    }

    @Step("Проверяем текст")
    public NewSubscriptionPageOne checkFieldSubscription() {
        $("[data-field='group_id'] label").shouldHave(exactText("Тип услуги"));
        return this;
    }

    @Step("Выбираем режим услуги")
    public NewSubscriptionPageOne setTypeSubscription(TypeSubscription typeSubscription) {
        var type = typeSubscription.getValue();
        $("[data-field='is_trial'] select").click();
        $("[data-field='is_trial'] [label='" + type + "']").click();
        return this;
    }

    @Step("Проверяем текст")
    public NewSubscriptionPageOne checkTypeSubscription() {
        $("[data-field='is_trial'] label").shouldHave(exactText("Режим услуги"));
        return this;
    }

    @Step("Нажимаем кнопку 'Продолжить'")
    public void clickButton() {
        $x("//*[text()='Продолжить']").click();
    }

    @Step("Вводим новую дату и время")
    public NewSubscriptionPageOne setDate(String date, String time) {
        $("[data-field='launch_date'] [data-date-format='dd.MM.yyyy']")
                .setValue(date).pressEnter();
        $("[data-field='launch_date'] [data-time-format='HH:mm']")
                .setValue(time).pressEnter();
        return this;
    }

    @Step("Проверяем текст даты")
    public NewSubscriptionPageOne checkFieldDate() {
        $("[data-field='launch_date'] label").shouldBe(exactText("Дата начала оказания услуги"));
        return this;
    }

    @Step("Вводим номер {paperOrder} бумажного заказа")
    public NewSubscriptionPageOne setPaperOrder(String paperOrder) {
        $("[data-field='paper_number'] input").setValue(paperOrder);
        return this;
    }

    @Step("Вводим номер {paperOrder} бумажного заказа")
    public NewSubscriptionPageOne checkFieldPaperOrder() {
        $("[data-field='paper_number'] label")
                .shouldHave(exactText("Номер бумажного заказа"));
        return this;
    }

}
