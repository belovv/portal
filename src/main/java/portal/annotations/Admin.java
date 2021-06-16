package portal.annotations;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface Admin {

    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Epic(value = "Админка")
    @Feature(value = "Заказчики")
    @interface customers {
        @Target({ElementType.TYPE, ElementType.METHOD})
        @Retention(RetentionPolicy.RUNTIME)
        @Epic(value = "Админка")
        @Feature(value = "Заказчики")
        @Story(value = "Профиль заказчика")
        @interface customerProfile {
        }
    }

    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Epic(value = "Админка")
    @Feature(value = "Добавить нового заказчика")
    @interface createNewCustomer {
    }


    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Epic(value = "Админка")
    @Feature(value = "Заказы/Подписки")
    @interface ordersSubscriptions {
        @Target({ElementType.TYPE, ElementType.METHOD})
        @Retention(RetentionPolicy.RUNTIME)
        @Epic(value = "Админка")
        @Feature(value = "Заказы/Подписки")
        @Story(value = "Профиль подписок")
        @interface subscriptionsProfile {
        }

        @Target({ElementType.TYPE, ElementType.METHOD})
        @Retention(RetentionPolicy.RUNTIME)
        @Epic(value = "Админка")
        @Feature(value = "Заказы/Подписки")
        @Story(value = "Подписки")
        @interface subscriptions {
        }

        @Target({ElementType.TYPE, ElementType.METHOD})
        @Retention(RetentionPolicy.RUNTIME)
        @Epic(value = "Админка")
        @Feature(value = "Заказы/Подписки")
        @Story(value = "Заказы")
        @interface orders {
        }

        @Target({ElementType.TYPE, ElementType.METHOD})
        @Retention(RetentionPolicy.RUNTIME)
        @Epic(value = "Админка")
        @Feature(value = "Заказы/Подписки")
        @Story(value = "Заявки")
        @interface requests {
        }
    }



    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Epic(value = "Админка")
    @Feature(value = "Тестирование входа в админку")
    @interface login {
    }

    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Epic(value = "Админка")
    @Feature(value = "Создаем новую подписку")
    @interface newSubscription {
    }

    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Epic(value = "Админка")
    @Feature(value = "Редактирование заявки подписки")
    @interface editSubscription {
    }

    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Epic(value = "Админка")
    @Feature(value = "Заявка на изменение подписки")
    @interface changeSubscription {
    }

    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Epic(value = "Админка")
    @Feature(value = "Проверка объемов подписки")
    @interface checkVolumeSubscription {
    }
}

