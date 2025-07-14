import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void basicBrowserSettings() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://www.amazon.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.reopenBrowserOnFail = true;
        Configuration.holdBrowserOpen = false;
    }
}
