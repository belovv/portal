package portal.admin;

import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Step;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;


public class OrdersSubscriptionsMainPage {

    @Step("Проверяем что мы на странице заказы/подписки")
    public void checkOrdersSubscriptionPageTitle() {
        $(".bs-section-title").shouldHave(exactText("Заказы / подписки"));
    }

    @Step("Кликаем на {linkText}")
    public void clickOn(String linkText) {
        $x("//*[contains(@class,'bs-link-set')]//*[text()=' " + linkText + " ']").click();
    }

    @Step("Проверяем что мы на странице {pageName}")
    public void checkPageTitle(String pageName) {
        $(".bs-breadcrumbs_link--current").shouldHave(exactText(pageName));
    }

    @Step("находим {nameSubscription} из списка и кликаем на нее")
    public void clickSubscriptionByName(String nameSubscription) {
        $x("//td-content[text()=' " + nameSubscription + " ']").click();
    }

    @Step("кликаем на первую строку из списка подписок и получаем instance_id")
    public String clickOnFirstSubscription() {
        final var subscriptionId = $("td[slot-key='instance_id']").text();
        $("td[slot-key='instance_id']").click();
        return subscriptionId;
    }

    @Step("кликаем на заказ подписки и получаем order_id")
    public String clickOnOrder() {
        final var path = "//td-content[contains(@slot-key,'order')]";
        final var orderId = $x(path).text().split("/")[0];
        $x(path).click();
        return orderId;
    }


    @Step("кликаем на номер заказчика и получаем customer_id")
    public String clickOnCustomer() {
        final var id = $x("//*[contains(@href,'/index/')]").text();
        $x("//*[contains(@href,'/index/')]").click();
        return id;
    }

    @Step("скачиваем файл кликая на экспорт Excel")
    public File clickExportExcel() {

        File file = null;

        try {
            file = $(".bs-link--export").download();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Step("кликаем на последнюю страницу списка")
    public void clickLastPage() {
        $$(".bs-table-pagination-new_page-button").last().click();
    }

    @Step("Проверяем что количество строк в диапазоне 1-100")
    public void checkRangeValueStrings(int from, int to) {
        $$(".bs-table-new_usual-row").shouldHave(CollectionCondition.sizeGreaterThan(from));
        $$(".bs-table-new_usual-row").shouldHave(CollectionCondition.sizeLessThanOrEqual(to));
    }

    @Step("Проверяем что количество строк {number}")
    public void checkValueStringsIs(int number) {
        $$(".bs-table-new_usual-row").shouldHave(CollectionCondition.size(number));
    }
}
