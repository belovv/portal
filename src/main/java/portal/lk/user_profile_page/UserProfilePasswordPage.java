package portal.lk.user_profile_page;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class UserProfilePasswordPage {

    @Step("Вводим и подтверждаем пароль и нажимаем кнопку")
    public void changePassword(String newPassword) {
        $("#bsControlPasswordAndConfirm-4").setValue(newPassword);
        $("#bsControlPasswordAndConfirm-4_confirm").setValue(newPassword);
        $("[ng-mousedown='$ctrl.onMousedown()']").click();
    }
}
