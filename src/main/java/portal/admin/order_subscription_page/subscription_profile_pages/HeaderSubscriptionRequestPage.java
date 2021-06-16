package portal.admin.order_subscription_page.subscription_profile_pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import portal.data.SubscriptionData;
import portal.data.enumdata.ModeCharge;
import portal.providers.DataProvider;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class HeaderSubscriptionRequestPage {

    private SubscriptionData subscriptionData = new DataProvider().subscriptionData();

    @Step("проверяем метод начисления")
    public HeaderSubscriptionRequestPage checkTariffType(ModeCharge modeCharge) {
        var type = modeCharge.getValue();
        $("[name='mode_charge']").shouldHave(text(type));
        return this;
    }

    @Step("Проверяем текст и номер бумажного заказа")
    public HeaderSubscriptionRequestPage checkPaperOrder(String valuePaperOrder) {
        $("[data-field='paper_number'] label")
                .shouldBe(exactText(subscriptionData.paperOrderName()));
        $("[control-id='paper_number']").shouldHave(exactText(valuePaperOrder));
        return this;
    }

    @Step("Проверяем текст и вводим номер бумажного заказа")
    public HeaderSubscriptionRequestPage checkAndSetPaperOrder(String valuePaperOrder) {
        $("[data-field='paper_number'] label")
                .shouldBe(exactText(subscriptionData.paperOrderName()));
        $("[data-field='paper_number'] input").setValue(valuePaperOrder);
        return this;
    }

    @Step("Проверяем текст и вводим новую дату и время")
    public void checkAndSetStartDate(String newDate, String newTime) {
        $("[data-field='launch_date'] label").shouldBe(exactText("Дата начала оказания услуги"));
        $("[data-date-format='dd.MM.yyyy']").clear();
        $("[data-date-format='dd.MM.yyyy']").setValue(newDate).pressEnter();
        $("[data-time-format='HH:mm']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[data-time-format='HH:mm']").setValue(newTime).pressEnter();
    }

    @Step("Проверяем дату оказания")
    public void checkServiceDate(String date, String time) {
        $x("//*[contains(@class,'bs-date-format')]").shouldHave(text(date + " " + time));
    }

    @Step("Проверяем дату начала оказания услуги")
    public HeaderSubscriptionRequestPage checkStartDate(String date, String time) {
        $("[data-field='launch_date'] label").shouldBe(exactText("Дата начала оказания услуги"));
        $("[control-id=launch_date]").shouldHave(exactText(date + " " + time));
        return this;
    }

    @Step("Кликаем на значок даты оказания")
    public void clickLaunchDate() {
        $("[slot-key='launch_date'] button").click();
    }

    @Step("Кликаем на значок даты оказания")
    public void clickStatus() {
        $("[slot-key='state'] button").click();
    }

    @Step("Кликаем на Редактировать")
    public HeaderSubscriptionRequestPage clickEdit() {
        $(".bs-button--fill-blue-inverse").click();
        return this;
    }

    @Step("Кликаем на Сохранить")
    public void clickSave() {
        $("[title='Сохранить']").click();
    }

    @Step("Кликаем на Заявка на изменение")
    public HeaderSubscriptionRequestPage clickChange() {
        $(".bs-button--blue.bs-button--auto").click();
        return this;
    }

}
