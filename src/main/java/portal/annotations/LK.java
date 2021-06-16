package portal.annotations;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface LK {

    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Epic(value = "Личный кабинет")
    @Feature(value = "Заказ услуг")
    @interface createSubscription {
    }

    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Epic(value = "Личный кабинет")
    @Feature(value = "Создаем заявку на измененение")
    @interface changeRequestSubscription {
    }

    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Epic(value = "Личный кабинет")
    @Feature(value = "Вход в личный кабинет")
    @interface login {
    }

    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Epic(value = "Личный кабинет")
    @Feature(value = "Создание нового пользователя")
    @interface createNewCustomer {
    }

    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Epic(value = "Личный кабинет")
    @Feature(value = "Профиль пользователя")
    @interface customerProfile {
    }

    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Epic(value = "Личный кабинет")
    @Feature(value = "email")
    @interface email {
        @Target({ElementType.TYPE, ElementType.METHOD})
        @Retention(RetentionPolicy.RUNTIME)
        @Epic(value = "Личный кабинет")
        @Feature(value = "email")
        @Story(value = "Создаем нового заказчика")
        @interface createNewCustomer {
        }
    }

    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Epic(value = "Личный кабинет")
    @Feature(value = "Каталог услуг")
    @interface catalog {
    }

    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Epic(value = "Личный кабинет")
    @Feature(value = "Мои услуги")
    @interface myServices {
        @Target({ElementType.TYPE, ElementType.METHOD})
        @Retention(RetentionPolicy.RUNTIME)
        @Epic(value = "Личный кабинет")
        @Feature(value = "Мои услуги")
        @Story(value = "Новые")
        @interface newSubscription {
        }
    }
}

