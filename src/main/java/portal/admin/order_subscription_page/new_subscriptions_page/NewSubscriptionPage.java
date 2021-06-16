package portal.admin.order_subscription_page.new_subscriptions_page;

import io.qameta.allure.Step;
import portal.data.enumdata.SubscriptionIdName;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class NewSubscriptionPage {

    private final String vmware_name;

    public NewSubscriptionPage(SubscriptionIdName vmware_name) {
        this.vmware_name = vmware_name.getValue();
    }

    @Step("Вводим значение {value} для {componentName}")
    public NewSubscriptionPage setValue(Enum componentName, String value) {
        var component = componentName.toString();
        $("[data-field='" + vmware_name + component + "'] [id='" + vmware_name + component + "']")
                .setValue(value);
        return this;
    }

    @Step("Вводим значение {value} ")
    public NewSubscriptionPage clickOn(Enum componentName, String value) {
        var component = componentName.toString();
        $("[data-field='" + vmware_name + component + "'] [id='" + vmware_name + component + "']" +
                " [label = '" + value +"']").click();
        return this;
    }


    @Step("Проверяем текст '{name}' строки компонента {componentName} ")
    public NewSubscriptionPage checkField(Enum componentName, String name) {
        var component = componentName.toString();
        $("[data-field='" + vmware_name + component + "'] label")
                .shouldHave(exactText(name));
        return this;
    }

    @Step("Кликаем на галочку {name}")
    public NewSubscriptionPage checkBox(String name) {
        $x("//*[text()=' " + name + " ']/../child::bs-checkbox").click();
        return this;
    }

    @Step("Кликаем создать")
    public void clickButton() {
        $(byText("Создать")).click();
    }


}
