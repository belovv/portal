package portal.admin.order_subscription_page.order_profile_page;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class OrderPage {

    @Step("Проверяем что мы на странице заказ")
    public void checkOrderPageTitle(){
        $x("//*[contains(@class,'bs-info-block_title')]").shouldHave(Condition.text("Заказ"));
    }

    @Step("Проверяем номер заказа")
    public void checkOrderPageTitleByNumber(String orderId){
        $(".bs-info-block_params-value").shouldHave(Condition.text(orderId));
    }
}
