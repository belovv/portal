package portal.admin.customer_page;


import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import portal.data.enumdata.ContractType;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CustomerProfilePage {
    @Step("Кликаем на вкладку {menuName}")
    public CustomerProfilePage clickOnMenu(String menuName) {
        $x("//*[text()=' " + menuName + " ']").click();
        return this;
    }

    @Step("Выбираем тип договора ")
    public CustomerProfilePage chooseContract(ContractType contractName){
        var name = contractName.getValue();
        $(".bs-customer-actions_registration-container bs-control-select").click();
        $("option[label='" + name + "']").click();
        return this;
    }

    @Step("Кликаем на {buttonName}")
    public CustomerProfilePage clickOnButton(String buttonName) {
        $x("//span[text()='" + buttonName + "']/..").click();
        return this;
    }

    @Step("Проверяем что мы на странице заказчика")
    public void checkCustomerPageTitleById(String id) {
        $x("//*[contains(@class,'bs-info-block_params-value')]").shouldHave(Condition.exactText(id));
    }

    @Step("Кликаем на чекбокс 'Не уведомлять заказчика'")
    public CustomerProfilePage clickOnCheckBox() {
        $x("//*[text()=' Не уведомлять заказчика ']").click();
        return this;
    }
}
