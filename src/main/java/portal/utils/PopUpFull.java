package portal.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import portal.data.enumdata.ButtonName;
import portal.data.enumdata.component.PopUpComponent;

import static com.codeborne.selenide.Selenide.*;

public class PopUpFull {

    @Step("Кликаем на кнопку {buttonName}")
    public void clickButtonByName(ButtonName buttonName) {
        var button = buttonName.getValue();
        $x("//*[contains(@class,'bs-modal_container')]//*[text()='" + button + "']").click();
    }

    @Step("Меняем статус")
    public PopUpFull changeItem(PopUpComponent popUpComponent, String item) {
        var component = popUpComponent.toString();
        $("[name='" + component + "'] div").click();
        $x("//*[text()='" + item + "']").click();
        return this;
    }

    @Step("Меняем статус заявки на ")
    public PopUpFull changeRequestStatus(String status) {
        $("[name='state'] div").click();
        $x("//p[text()='" + status + "']").click();
        return this;
    }

    @Step("Оставляем комментарий")
    public PopUpFull setComment(String comment) {
        $x("//*[@placeholder='Введите ваше сообщение...']").setValue(comment);
        return this;
    }

    @Step("Кликаем и вводим дату {date}")
    public PopUpFull setDate(String date) {
        $("[data-date-format='dd.MM.yyyy']").clear();
        $("[data-date-format='dd.MM.yyyy']").setValue(date).pressEnter();
        return this;
    }

    @Step("Кликаем и вводим время {time}")
    public PopUpFull setTime(String time) {
        $("[data-time-format='HH:mm']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[data-time-format='HH:mm']").setValue(time).pressEnter();
        return this;
    }

    @Step("Получаем текущую дату тестирования")
    public String getCurrentTestingDate() {
        return $$x("//*[contains(@class,'bs-unit_transclude--no-edit')]").get(1).text();
    }

}
