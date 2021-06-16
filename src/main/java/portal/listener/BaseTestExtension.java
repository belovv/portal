package portal.listener;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.remote.DesiredCapabilities;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import portal.config.WebConfig;
import portal.email.EmailSteps;
import portal.retrofit.RetrofitErrorInterceptor;


import static com.codeborne.selenide.Selenide.closeWebDriver;
import static portal.listener.AttachmentsHelper.*;

public class BaseTestExtension implements AfterEachCallback, BeforeAllCallback, BeforeEachCallback {
    private static final String SUCCESS = "Success";
    private static final String FAILED = "Failed";
    private final ListAppender<ILoggingEvent> listAppender = new ListAppender<>();


    @Override
    public void afterEach(ExtensionContext context) {
        if (getTestStatus(context).equals(FAILED)) {
            if(listAppender.list.size()>0){
                attachAsText("Logger", listAppender.list.get(0).getMessage());
            }
            attachAsText("Browser console logs", getBrowserConsoleLogs());
        } else {
            attachAsText("Browser console logs", getBrowserConsoleLogs());
            attachScreenshot("Last screenshot");
        }
        closeWebDriver();
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        Logger logger = (Logger) LoggerFactory.getLogger(RetrofitErrorInterceptor.class);

        // create and start a ListAppender
        listAppender.start();

        // add the appender to the logger
        logger.addAppender(listAppender);
    }

    private String getTestStatus(ExtensionContext context) {
        return context.getExecutionException().isPresent() ? FAILED : SUCCESS;
    }

    @Override
    public void beforeAll(ExtensionContext context) {
        Configuration.remote = new WebConfig().getCallistoUrl();
//        Configuration.remote  ="http://localhost:4444/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        Configuration.reportsFolder = "build/selenide-screenshots";
        Configuration.downloadsFolder = "build/selenide-downloads";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.fastSetValue = true;
        Configuration.timeout = 20000;
//        Configuration.fileDownload ="PROXY";
//        Configuration.proxyEnabled = true;
        new EmailSteps().changeStatusAllMessagesToSeen();

    }
}
