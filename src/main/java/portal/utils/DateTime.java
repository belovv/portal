package portal.utils;

import io.qameta.allure.Step;
import org.awaitility.core.ConditionTimeoutException;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class DateTime {

    @Step("Получаем текущую дату")
    public String getTodayDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Step("Получаем текущую дату")
    public String getTomorrowDate() {
        return LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Step("Получаем время UTC для активации заявки")
    public String getTimeToExecutionDate() {
        var requestTime = LocalTime.now(ZoneId.of("Z"));
        int seconds = Integer.valueOf(requestTime.format(DateTimeFormatter.ofPattern("ss")));
        if (seconds>45){
            return requestTime.plusMinutes(2).format(DateTimeFormatter.ofPattern("kk:mm"));
        } else {
            return requestTime.plusMinutes(1).format(DateTimeFormatter.ofPattern("kk:mm"));
        }
    }



    private String getCurrentTimeUTC() {
        return LocalTime.now(ZoneId.of("Z")).minusSeconds(3).format(DateTimeFormatter.ofPattern("kk:mm"));
    }


    @Step("Увеличиваем полученную дату на 14 дней")
    public String getIncreasedDateBy14Days(String date) {
        var localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy")).plusDays(14);
        return localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    @Step("Получаем время на 2 минуты больше")
    public String getNext2Minutes() {
        return LocalTime.now().plusMinutes(2).format(DateTimeFormatter.ofPattern("kk:mm"));
    }

    @Step("Получаем дату вчера")
    public String getYesterdayDate() {
        return LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "T21:00:00";
    }

    @Step("Получаем прошедший месяц в текстовом формате")
    public String getPastMonth() {
        var locale = new Locale("ru");
        var localDate = LocalDate.now().minusMonths(1);
        var pastMonth = localDate.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, locale);
        var year = localDate.getYear();
        return String.format("%s %s", pastMonth, year);
    }

    @Step("Ждем наступления времени {time} даты оказания ")
    public void waitLaunchTime (String time) {
        try {
            await()
                    .atMost(78, TimeUnit.SECONDS)                   // max wait
                    .pollDelay(0, TimeUnit.SECONDS)                   //  do not check immediately - wait for 2 seconds for the first time
                    .pollInterval(1, TimeUnit.SECONDS)            // check every 1 seconds
                    .until(() -> getCurrentTimeUTC().equals(time));         // until
        } catch (ConditionTimeoutException e) {
            throw new ConditionTimeoutException("время вышло, time = " + getCurrentTimeUTC());

        }

    }


}

