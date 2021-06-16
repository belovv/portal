package portal.admin.order_subscription_page.subscription_profile_pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SubscriptionProfilePage {

    @Step("Кликаем на вкладку {menuName}")
    public SubscriptionProfilePage clickOnMenu(String menuName) {
        $x("//*[text()=' " + menuName + " ']").click();
        return this;
    }

    @Step("Проверяем что подписка+id присутствует на странице")
    public void checkSubscriptionPageTitleById(String subscriptionId) {
        $(".bs-info-block_title").shouldHave(text("Подписка " + subscriptionId));
    }

    @Step("Кликаем на 'Изменить статус'")
    public void clickChangeStatus() {
        $x("//*[text()='Изменить статус']").click();
    }

    @Step("Кликаем на 'Перевести в коммерческий режим'")
    public void clickChangeModeToCommercial() {
        $x("//*[text()='Перевести в коммерческий режим']").click();
    }

    @Step("Кликаем на 'Продлить тестирование'")
    public void clickExtendTesting() {
        $x("//*[text()='Продлить тестирование']").click();
    }

    @Step("Получаем текущую дату тестирования")
    public String getCurrentTestingDate() {
        return $x("//*[contains(@class,'ng-bs-subscription-status_right-auto')]").text().split(" ")[0];
    }

}
