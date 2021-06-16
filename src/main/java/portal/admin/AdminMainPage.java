package portal.admin;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class AdminMainPage {

    @Step("Проверяем видимость логотипа, проверка загрузки страницы")
    public void checkLogoIsVisible() {
        $x("//*[@class='bs-layout-authorized_header-container']").should(Condition.visible);
    }

    @Step("Выход из профиля")
    public void clickQuitAdminProfile() {
        $x("//*[contains(@class,'bs-header-col-2_current-item') and contains(@class,'ng-binding')]").click();
        $x("//*[contains(@ui-sref,'signout')]").click();
    }

    @Step("кликаем на 'Заказчики'")
    public void clickCustomers() {
        $x("//*[contains(@class,'bs-sidemenu_menu-item')]//*[@href='/index']").click();
    }

    @Step("кликаем на 'Заказы/Подписки'")
    public void clickOrderSubscriptions() {
        $x("//*[@href='/subscriptions/list']").click();
    }

}