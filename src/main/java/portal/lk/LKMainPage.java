package portal.lk;


import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class LKMainPage {

    @Step("Кликаем на 'Каталог услуг'")
    public void clickCatalog() {
        $("[href='/catalog']").click();
    }

    @Step("Проверяем видимость логотипа, проверка загрузки страницы")
    public void checkLogoIsVisible() {
        $("a.bs-sidemenu_link.bs-sidemenu_link-main").should(Condition.visible);
    }

    @Step("Кликаем на пользователя чтобы попасть в профиль пользователя")
    public void clickUserProfile() {
        $("[is-open='isOpen[2]']").click();
        $("[href='/settings']").click();
    }

    @Step("Кликаем на выход из кабинета")
    public void clickQuitUserProfile() {
        $("[is-open='isOpen[2]']").click();
        $("[href='/signout']").click();
    }

    @Step("Кликаем на 'Мои услуги'")
    public void clickMyServicesPage() {
        $(byText("Мои Услуги")).click();
    }

}

