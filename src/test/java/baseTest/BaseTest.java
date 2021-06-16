package baseTest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.remote.DesiredCapabilities;
import portal.config.WebConfig;
import portal.email.EmailSteps;
import portal.listener.BaseTestExtension;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static portal.listener.AttachmentsHelper.*;

@ExtendWith(BaseTestExtension.class)
public class BaseTest {

    @BeforeAll
    public static void setupSelenideConfig() {
        Configuration.remote = new WebConfig().getCallistoUrl();
        //Configuration.remote ="http://localhost:4444/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        Configuration.reportsFolder = "build/selenide-screenshots";
        Configuration.downloadsFolder = "build/selenide-downloads";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1366x768";
        Configuration.headless = false;
        Configuration.fastSetValue = true;
        Configuration.timeout = 15000;
/*      //Configuration.fileDownload ="PROXY";
        //Configuration.proxyEnabled = true;    */
        new EmailSteps().changeStatusAllMessagesToSeen();
    }


    @AfterEach
    public void addAttachments() {
        attachAsText("Browser console logs", getBrowserConsoleLogs());
        attachScreenshot("Last screenshot");
        closeWebDriver();

    }
}

