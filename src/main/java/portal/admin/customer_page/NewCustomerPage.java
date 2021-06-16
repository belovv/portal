package portal.admin.customer_page;


import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$x;

public class NewCustomerPage {
    @Step("Вводим имя заказчика")
    public NewCustomerPage customerName(String name) {
        $x("//*[@data-field='name']").shouldHave(exactText("Представитель заказчика"));
        $x("//*[@data-field='name']//input[@type='text']").setValue(name);
        return this;
    }

    @Step("Вводим номер телефона")
    public NewCustomerPage customerPhone(String phone) {
        $x("//*[@data-field='phone']").shouldHave(exactText("Телефон"));
        $x("//*[@data-field='phone']//input[@type='tel']").setValue(phone);
        return this;
    }

    @Step("Вводим почту")
    public NewCustomerPage customerEmail(String email) {
        $x("//*[@data-field='email']").shouldHave(exactText("Email"));
        $x("//*[@data-field='email']//input[@name='email']").setValue(email);
        return this;
    }

    @Step("Вводим ИНН")
    public NewCustomerPage customerINN(String inn) {
        $x("//*[@data-field='itn_psrn']").shouldHave(exactText("ИНН или ОГРН"));
        $x("//*[@data-field='itn_psrn']//input[@name='itn_psrn']").setValue(inn);
        return this;
    }

    @Step("Кликаем на 'Не отправлять письмо о регистрации'")
    public NewCustomerPage clickNoEmail() {
        $x("//bs-checkbox").click();
        return this;
    }

    @Step("кликаем 'Продолжить'")
    public NewCustomerPage clickButtonNext() {
        $x("//*[text()='Продолжить']").click();
        return this;
    }

    @Step("Получаем короткое название компании")
    public String getShortName() {
        return $x("//*[contains(@ng-href,'https://focus.kontur.ru')]").getText();
    }


    @Step("Выбираем 'Менеджера продаж'")
    public NewCustomerPage chooseSalesManager(String salesManager) {
        $x("//*[@name='sales_manager']//option[@label='" + salesManager + "']").click();
        return this;
    }

    @Step("Кликаем 'Сохранить'")
    public void clickSaveButton() {
        $x("//*[text()='Сохранить']").click();
    }

}
