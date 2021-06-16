package portal.lk.login_pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class UserRegistrationPage {
    @Step("Шаг 1, проверяем текст и нажимаем 'далее'")
    public void stepOneCheckText() {
        var link = ".signup-step0_paragraph";
        $$(link).get(0).shouldHave(exactText("Для доступа к личному кабинету T1 Cloud нужно зарегистрироваться."));
        $$(link).get(1).shouldHave(exactText("В личном кабинете вы сможете управлять заказами, демо-версиями и услугами, выбрать тариф и тип договора."));
        $$(link).get(2).shouldHave(exactText("Для регистрации потребуются данные о вашей организации."));
        $$(link).get(3).shouldHave(exactText("Регистрация займет 3 - 4 минуты."));
        $(".signup-step0_button-container button").click();
    }

    @Step("Шаг 2: заполняем поля данными заказчика и нажимаем 'далее' ")
    public void stepTwoSetNewUser(String name, String tel, String email, String inn, String password) {
        $("input[name='name']").sendKeys(name);
        $("input[name='phone']").setValue(tel);
        $("input[name='email']").setValue(email);
        $("input[name='itn_psrn']").setValue(inn);
        $("input[name='password']").setValue(password);
        $(".ng-valid.ng-scope.ng-empty").setValue(password);
        $(byText("Далее")).click();
    }

    @Step("Шаг 3: проверяем текст и нажимаем 'далее'")
    public void stepThreeCheckText() {
        $(".signup-step2_prompt.ng-binding").shouldHave(exactText("Пожалуйста, подтвердите введенные данные:"));
        $(byText("Далее")).click();
    }

    @Step("Шаг 4: проверяем текст")
    public void stepFour() {
        $$(".signup-step3_paragraph").get(0).shouldHave(exactText("Для начала работы осталось подтвердить адрес электронной почты. Для этого перейдите по ссылке из письма."));
        $$(".signup-step3_paragraph").get(1).shouldHave(exactText("Если письма нет - свяжитесь с нами."));
    }

    @Step("Шаг 5: проверяем текст и нажимаем кнопку 'В личный кабинет'")
    public void stepFive() {
        $(".signup-step4_header").shouldHave(exactText("Поздравляем!"));
        $(".signup-step4_paragraph").shouldHave(exactText("Ваш email успешно подтвержден, теперь вы можете приступить к работе в своем аккаунте."));
        $(byText("В личный кабинет")).click();
    }

}
