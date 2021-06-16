package portal.admin.order_subscription_page.subscription_profile_pages;

import io.qameta.allure.Step;
import portal.data.enumdata.SubscriptionIdName;

import java.util.Map;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exactValue;
import static com.codeborne.selenide.Selenide.$;


public class SubscriptionRequestPage {

    private final String vmware_name;

    public SubscriptionRequestPage(SubscriptionIdName vmware_name) {
        this.vmware_name = vmware_name.getValue();
    }

    @Step("Проверяем значение компонента {componentName}")
    public SubscriptionRequestPage checkValue(Enum componentName, String value) {
        var component = componentName.toString();
        $("[data-field='" + vmware_name + component + "'] [name='" + vmware_name + component + "']")
                .shouldHave(exactText(value));
        return this;
    }

    @Step("Вводим количество {value} для {componentName}")
    public SubscriptionRequestPage setValue(Enum componentName, String value) {
        var component = componentName.toString();
        $("[data-field='" + vmware_name + component + "'] [id='" + vmware_name + component + "']").clear();
        $("[data-field='" + vmware_name + component + "'] [id='" + vmware_name + component + "']").setValue(value);
        return this;
    }

    @Step("Получаем значения компонент на вкладке заявки")
    public SubscriptionRequestPage getVolume(Enum componentName, Map<Enum,String> data){
        var component = componentName.toString();
        var value = $("[data-field='" + vmware_name + component + "']" +
                " [name='" + vmware_name + component + "']").getText();
        //TODO убрать костыль когда на фронте поправят
        if (value.equals("")){
            value = "0";
        }
        data.put(componentName,value);
        return this;
    }

    @Step("Сверяем значения на вкладке заявки со значениями на вкладке объемы")
    public SubscriptionRequestPage checkVolume (Enum componentName, Map<Enum,String> data){
        var component = componentName.toString();
        $("[data-field='" + vmware_name  + component + "']" +
                " [name='" + vmware_name + component + "'] input").shouldHave(exactValue(data.get(componentName)));
        return this;
    }

    @Step("Сверяем значения на вкладке заявки со значениями на вкладке объемы")
    public SubscriptionRequestPage checkVolumeInternetSpeed (Enum componentName, Map<Enum,String> data){
        var component = componentName.toString();

        $("[data-field='" + vmware_name  + component + "']" +
                " [name='" + vmware_name + component + "'] input").shouldHave(exactValue(data.get(componentName)));
        return this;
    }

    @Step("Проверяем текст строки")
    public SubscriptionRequestPage checkField(Enum componentName, String name) {
        var component = componentName.toString();
        $("[data-field='" + vmware_name + component + "'] label").shouldHave(exactText(name));
        return this;
    }

    @Step("Проверяем текст строки и размещение в ts-cloud")
    public SubscriptionRequestPage checkCloud(Enum componentName, String setCloud) {
        var component = componentName.toString();
        $("[data-field='" + vmware_name + component + "'] span").shouldHave(exactText(setCloud));
        return this;
    }

    @Step("Проверяем текст строки, и выбираем размещение в ts-cloud")
    public SubscriptionRequestPage clickCloud(Enum componentName, String setCloud) {
        var component = componentName.toString();
        $("[data-field='" + vmware_name + component + "'] [label='" + setCloud + "']").click();
        return this;
    }
}
