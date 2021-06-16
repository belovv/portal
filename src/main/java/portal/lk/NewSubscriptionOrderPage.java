package portal.lk;

import io.qameta.allure.Step;
import portal.data.enumdata.SubscriptionIdName;
import portal.data.enumdata.TypeSubscription;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class NewSubscriptionOrderPage {

    private final String vmware_name;

    public NewSubscriptionOrderPage(SubscriptionIdName vmware_name) {
        this.vmware_name = vmware_name.getValue();
    }

    @Step("Нажимаем кнопку '{typeSubscription}'")
    public NewSubscriptionOrderPage clickOrderType(TypeSubscription typeSubscription) {
        String type = typeSubscription.getValue();
        $x("//*[text()='" + type + "']").click();
        return this;
    }

    @Step("Нажимаем кнопку 'Заказать'")
    public void clickOrder() {
        $(byText("Заказать")).click();
    }


    @Step("Выставляем тариф")
    public NewSubscriptionOrderPage setTariff(String setTariffType) {
        $("[data-field='" + vmware_name + "scheme_pricing'] [id='bsSubscriptionOrder_" + vmware_name + "scheme_pricing']")
                .$(byText(setTariffType)).click();
        return this;
    }

    @Step("Вводим количество {value} для {componentName}")
    public NewSubscriptionOrderPage setValue(Enum componentName, String value) {
        var component = componentName.toString();
        $("[data-field='" + vmware_name + component + "'] [id='bsSubscriptionOrder_" + vmware_name + component + "']")
                .setValue(value);
        return this;
    }

    @Step("Проверяем текст {name} для {componentName}")
    public NewSubscriptionOrderPage checkField(Enum componentName, String name) {
        var component = componentName.toString();
        $("[data-field='" + vmware_name + component + "'] .bs-control-grid-new_label").shouldHave(exactText(name));
        return this;
    }

    @Step("Выбираем {value}")
    public NewSubscriptionOrderPage clickCloud(Enum componentName, String value) {
        var component = componentName.toString();
        $("[data-field='" + vmware_name + component + "'] [label='" + value + "']").click();
        return this;
    }

    @Step("Убираем галочку {name}")
    public NewSubscriptionOrderPage checkBox(String name) {
        $x("//*[text()=' " + name + " ']/../child::bs-checkbox").click();
        return this;
    }


}