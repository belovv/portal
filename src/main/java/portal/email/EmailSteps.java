package portal.email;

import io.qameta.allure.Step;
import jakarta.mail.Message;
import portal.config.WebConfig;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;


public class EmailSteps {

    private HashMap<String, String> config = new WebConfig().getEmailConfig();
    private final Email email = new Email(
            config.get("username"),
            config.get("password"),
            config.get("from"),
            config.get("host"));

    @Step("Проверяем что заказанные услуги есть в тексте письма и помечаем письмо как прочитанное")
    public void checkTextOfMessageAndDoRead(String subject,
                                            String subscriptionNameOne,
                                            String subscriptionNameTwo,
                                            String subscriptionNameThree,
                                            String subscriptionNameFour,
                                            String paperOrder,
                                            String... emailRecipient) {
        var messages = email.waitAndGetMessageBySubjectAndRecipient(subject, emailRecipient);
        assertThat(messages.size())
                .as("должно быть одно письмо")
                .isEqualTo(1);
        var message = messages.get(0);
        var text = email.getMessageContent(message);
        assertThat(text)
                .contains(subscriptionNameOne)
                .contains(subscriptionNameTwo)
                .contains(subscriptionNameThree)
                .contains(subscriptionNameFour)
                .contains(paperOrder);
        email.changeStatusMessageToSeen(message);
    }

    @Step("Проверяем что заказанные услуги есть в тексте письма и помечаем письмо как прочитанное")
    public void checkTextOfMessageAndDoRead(String emailRecipient,
                                            String subject,
                                            String paperOrder) {
        var messages = email.waitAndGetMessageBySubjectAndRecipient(subject, emailRecipient);
        assertThat(messages.size()).isEqualTo(1);
        var message = messages.get(0);
        var text = email.getMessageContent(message);
        assertThat(text)
                .contains(paperOrder);
        email.changeStatusMessageToSeen(message);
    }

    @Step("Проверяем что писем для адресата {emailRecipient} нет")
    public void checkThatNoNewMessageTo(String emailRecipient) {
        email.waitAndCheckThatNoNewEmail(emailRecipient);
    }

    @Step("Получаем письмо с темой '{subject}' для получателя '{emailRecipient}'")
    public Message getMessageBySubjectAndRecipient(String subject, String emailRecipient) {
        var messages = email.waitAndGetMessageBySubjectAndRecipient(subject, emailRecipient);
        assertThat(messages.size()).as("Должно быть одно письмо").isEqualTo(1);
        return messages.get(0);
    }

    @Step("Помечаем все письма c темой {subject} для {emailRecipient} как прочитанные")
    public void changeStatusMessageToSeen(String subject, String emailRecipient) {
        var messages = email.getMessagesByRecipientAndSubject(subject, emailRecipient);
        for (Message message : messages) {
            email.changeStatusMessageToSeen(message);
        }
    }

    @Step("Помечаем все письма для {emailRecipient} как прочитанные")
    public void changeStatusMessageToSeen(String emailRecipient) {
        var messages = email.getMessageBySubject(emailRecipient);
        for (Message message : messages) {
            email.changeStatusMessageToSeen(message);
        }
    }

    @Step("Помечаем все письма как прочитанные")
    public void changeStatusAllMessagesToSeen() {
        var messages = email.getAllUnreadMessagesFrom();
        for (Message message : messages) {
            email.changeStatusMessageToSeen(message);
        }
    }

    @Step("Получаем ссылку из письма")
    public String getUrlFromMessage(Message message) {
        return email.getUrlsFromMessage(message).get(0);
    }


}
