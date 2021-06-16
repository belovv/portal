package portal.lk.contract_page;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ContractPage {
    @Step("Выбираем индивидуальный договор , проверяем текст и жмем 'далее'")
    public void chooseContract(String text, String individual) {
        $(".bs-selection-of-contract_prompt").shouldHave(text(text));
        $$(".bs-control-radio_label").get(1).click();
        $("[slot-key='individual']").shouldHave(exactText(individual));
        $(byText("Далее")).click();
    }

    @Step("Проверяем текст сообщения")
    public void checkTextOfIndividual(String text) {
        $(".bs-common_container").shouldHave(text(text));
    }

    @Step("нажимаем на'Вернуться в мои услуги'")
    public void clickReturnToMyServices() {
        $(byText("Вернуться в Мои Услуги")).click();
    }

}
