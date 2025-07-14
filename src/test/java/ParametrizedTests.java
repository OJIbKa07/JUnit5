import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ParametrizedTests extends TestBase {

    static Stream<Arguments> amazonSiteShouldDisplayCorrectButtons() {
        return Stream.of(
                Arguments.of(
                        "ES",
                        List.of("Ofertas del Día", "Listas", "Prime Video", "Tarjetas de Regalo", "Servicio al Cliente", "Vender")
                ),
                Arguments.of(
                        "DE",
                        List.of("Angebote des Tages", "Wunschlisten", "Prime Video", "Geschenkkarten", "Kundenservice", "Verkaufen bei Amazon")
                )
        );
    }

    @MethodSource
    @ParameterizedTest
    void amazonSiteShouldDisplayCorrectButtons(String language, List<String> expectedButtons) {
        open("/");
        if ($("body").has(text("Continue shopping"))) {
            $(byText("Continue shopping")).shouldBe(visible).click();
        }
        $("button.nav-flyout-button").shouldBe(visible, Duration.ofSeconds(5)).click();
        //$("button.nav-flyout-button").click();

        $("div#nav-flyout-icp").should(appear)
                .$$(byText(language)).find(visible)
                .shouldBe(enabled)
                .click();

        //$("div#nav-flyout-icp").should(appear).$(byText(language)).click();
        $$("#nav-xshop a").filter(visible).shouldHave(texts(expectedButtons));
    }

}
