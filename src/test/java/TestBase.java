import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TestBase {

    @BeforeAll
    static void basicBrowserSettings() {
        Configuration.browserSize = System.getProperty("browser_size", "1920x1080");
        Configuration.baseUrl = "https://www.amazon.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser =  System.getProperty ("browser","chrome");
        Configuration.browserVersion =  System.getProperty("browser_version", "127.0");
        Configuration.remote = "https://user1:1234@" + System.getProperty("selenoid_url","selenoid.autotests.cloud/wd/hub");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }
}
