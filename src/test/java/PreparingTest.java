import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PreparingTest {
    void closePopupIfVisible() {
        if ($("body").has(text("Continue shopping"))) {
            $(byText("Continue shopping")).shouldBe(visible).click();
        }
        if ($("div.modal").isDisplayed()) {
            $("div.modal button.close").click();
        }
        if ($(byText("No thanks")).exists()) {
            $(byText("No thanks")).click();
        }
    }
}
