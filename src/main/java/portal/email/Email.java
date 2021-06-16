package portal.email;


import io.qameta.allure.Step;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.search.FlagTerm;
import org.awaitility.core.ConditionTimeoutException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static org.awaitility.Awaitility.await;

public class Email {

    private final String from;
    private final int numUnreadEmails = 60;
    private Store store;

    Email(String username, String password, String from, String host) {
        this.from = from;

        var props = new Properties();
        //props.put("mail.debug", "true"); // логи в терминале
        var session = Session.getInstance(props);

        try {
            store = session.getStore("imaps");
        } catch (NoSuchProviderException e) {
            System.out.println("Error:" + e.getMessage());
        }
        try {
            store.connect(host, username, password);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
    }

    //************* EMAIL ACTIONS *******************

    /**
     * Получаем непрочитанные письма
     */
    public Message[] getAllUnreadMessagesFrom() {
        try {
            var folder = store.getFolder("inbox");
            folder.open(Folder.READ_WRITE);
            var unReadMessage = folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
            List<Message> fromMessages = new ArrayList<>();
            for (Message message : unReadMessage) {
                if (((InternetAddress) message.getFrom()[0]).getAddress().equals(from)) {
                    fromMessages.add(message);
                }
            }
            return fromMessages.toArray(new Message[]{});
        } catch (MessagingException e) {
            throw new NullPointerException(e.getLocalizedMessage());
        }
    }

    /**
     * Получаем последние {numberEmail} писем
     */
    private Message[] getUnreadMessages(int numberEmail) {
        try {
            var folder = store.getFolder("inbox");
            folder.open(Folder.READ_WRITE);
            var unReadMessage = folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
            List<Message> fromMessages = new ArrayList<>();
            var number = Math.min(unReadMessage.length, numberEmail);
            for (int i = 1; i <= number; i++) {
                final var message = unReadMessage[unReadMessage.length - i];
                if (((InternetAddress) message.getFrom()[0]).getAddress().equals(from)) {
                    fromMessages.add(message);
                }

            }
            return fromMessages.toArray(new Message[]{});
        } catch (MessagingException e) {
            throw new NullPointerException(e.getLocalizedMessage());
        }
    }

    /**
     * Получаем письма с темой {subject}"
     */
    List<Message> getMessageBySubject(String subject) {
        try {
            final var unreadMessage = getUnreadMessages(numUnreadEmails);
            List<Message> messageBySubject = new ArrayList<>();
            for (Message message : unreadMessage) {
                if (message.getSubject().contains(subject)) {
                    messageBySubject.add(message);
                }
            }
            return messageBySubject;
        } catch (MessagingException e) {
            throw new NullPointerException(e.getLocalizedMessage());
        }
    }

    /**
     * Получаем текст письма"
     */
    String getMessageContent(Message message) {
        try {
            final var mp = (Multipart) message.getContent();
            return mp.getBodyPart(0).getContent().toString();
        } catch (Exception e) {
            throw new NullPointerException("нет письма для проверки текста");
        }
    }

    /**
     * Получаем список получателей письма"
     */
    List<String> getAddressOfRecipients(Message message) {
        try {
            List<String> addresses = new ArrayList<>();
            for (Address recipient : message.getRecipients(Message.RecipientType.TO)) {
                addresses.add(recipient.toString());
            }
            return addresses;
        } catch (MessagingException e) {
            return null;
        }
    }


    /**
     * Получаем письма для получателя"
     */
    private List<Message> getMessagesByRecipient(String recipient) {
        try {
            List<Message> messageList = new ArrayList<>();
            for (Message message : getUnreadMessages(numUnreadEmails)) {
                for (Address recipients : message.getRecipients(Message.RecipientType.TO)) {
                    if (recipients.equals(recipient)) {
                        messageList.add(message);
                        break;
                    }
                }
            }
            return messageList;
        } catch (MessagingException e) {
            throw new NullPointerException(e.getLocalizedMessage());
        }
    }

    /**
     * Фильтруем письма по количеству получателей
     */
    private List<Message> getMessagesByNumberRecipient(List<Message> messages, int recipientNumber) {
        List<Message> messageList = new ArrayList<>();
        for (Message message : messages) {
            if (getAddressOfRecipients(message).size() == recipientNumber) {
                messageList.add(message);
            }
        }
        return messageList;
    }


    /**
     * Извлекаем URL из письма"
     */
    List<String> getUrlsFromMessage(Message message) {
        var content = getMessageContent(message);
        List<String> containedUrls = new ArrayList<>();
        var urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        var pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        var urlMatcher = pattern.matcher(content);
        while (urlMatcher.find()) {
            containedUrls.add(content.substring(urlMatcher.start(0),
                    urlMatcher.end(0)));
        }
        return containedUrls;
    }

    /**
     * Ждем и проверяем что писем нет
     */
    void waitAndCheckThatNoNewEmail(String recipient) {
        try {
            await()
                    .atMost(20, TimeUnit.SECONDS)                   // max wait
                    .pollDelay(2, TimeUnit.SECONDS)                   //  do not check immediately - wait for 2 seconds for the first time
                    .pollInterval(1, TimeUnit.SECONDS)            // check every 1 seconds
                    .until(() -> getMessagesByRecipient(recipient).size() == 0);         // until email is received
        } catch (ConditionTimeoutException e) {
            throw new ConditionTimeoutException("есть письма для получателя =" + getMessagesByRecipient(recipient).size());
        }
    }


    @Step("Получаем письма с темой {subject} для {emailAddress}")
    List<Message> getMessagesByRecipientAndSubject(String subject, String... emailAddress) {
        List<Message> messagesBySubjectAndRecipient = new ArrayList<>();
        var messageSubject = getMessagesByNumberRecipient(getMessageBySubject(subject), emailAddress.length);
        for (Message message : messageSubject) {
            var emailRecipients = getAddressOfRecipients(message);
            if (emailRecipients.size() >= emailAddress.length) {
                var checkRecipients = -1;
                for (String email : emailAddress) {
                    checkRecipients = emailRecipients.indexOf(email);
                }
                if (checkRecipients > -1) {
                    messagesBySubjectAndRecipient.add(message);
                }
            }
        }
        return messagesBySubjectAndRecipient;
    }

    /**
     * Ждем пока появятся письма с темой {subject} и получателем {emailAddress}
     */
    List<Message> waitAndGetMessageBySubjectAndRecipient(String subject, String... emailAddress) {
        try {
            await()
                    .atMost(20, TimeUnit.SECONDS)                   // max wait
                    .pollDelay(2, TimeUnit.SECONDS)                   //  do not check immediately - wait for 2 seconds for the first time
                    .pollInterval(1, TimeUnit.SECONDS)            // check every 1 seconds
                    .until(() -> getMessagesByRecipientAndSubject(subject, emailAddress).size() > 0);         // until email is received
        } catch (ConditionTimeoutException e) {
            throw new ConditionTimeoutException("нет новых писем с темой письма '" + subject + "' и получателем '" + Arrays.toString(emailAddress) + "'");
        }
        return getMessagesByRecipientAndSubject(subject, emailAddress);
    }

    /**
     * Помечаем письмо как прочитанное
     */
    void changeStatusMessageToSeen(Message message) {
        try {
            message.setFlag(Flags.Flag.SEEN, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}


