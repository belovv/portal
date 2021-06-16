package portal.admin;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

public class CustomersMainPage {

    @Step("Проверяем что мы на странице заказчики")
    public void getCustomerPageTitle() {
        $(".bs-section-title").shouldHave(exactText("Заказчики"));
    }

    @Step("Кликаем на строку поиска и вводим {customerName}")
    public CustomersMainPage searchCustomer(String customerName) {
        $(".bs-table-filter_input").setValue(customerName).pressEnter();
        return this;
    }

    @Step("Проверяем название {shortName} заказчика")
    public CustomersMainPage checkShortName(String shortName) {
        $(".bs-table-new_row--selectable [slot-key='detailed_info.name']").shouldHave(exactText(shortName));
        return this;
    }

    @Step("Проверяем почту {email} заказчика")
    public CustomersMainPage checkEmail(String email) {
        $(".bs-table-new_row--selectable [slot-key='email']").shouldHave(exactText(email));
        return this;
    }

    @Step("Кликаем на строку поиска")
    public void clickSearch() {
        $(".bs-table-filter_tags").click();
    }

    @Step("кликаем на первого заказчика из списка")
    public void clickFirstCustomerFromList() {
        $(".bs-table-new_row--selectable").click();
    }
}
