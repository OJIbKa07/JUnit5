import data.Language;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ParametrizedTests extends TestBase {
    PreparingTest preparingPages = new PreparingTest();

    @Tags({
            @Tag("SMOKE"),
            @Tag("WEB"),
    })
    @EnumSource(Language.class)
    @ParameterizedTest
    void amazonSiteShouldDisplayCorrectText(Language language) {
        open("/");
        preparingPages.closePopupIfVisible();
        $("button.nav-flyout-button").shouldBe(visible).click();
        $("div#nav-flyout-icp").should(appear)
                .$$(byText(language.name())).find(visible)
                .shouldBe(enabled)
                .click();
        $("#nav-link-accountList").shouldHave(text(language.description));;
    }

    static Stream<Arguments> amazonSiteShouldDisplayCorrectButtons() {
        return Stream.of(
                Arguments.of(
                        Language.ES,
                        List.of("Ofertas del Día", "Listas", "Prime Video", "Tarjetas de Regalo", "Servicio al Cliente", "Vender")
                ),
                Arguments.of(
                        Language.DE,
                        List.of("Angebote des Tages", "Wunschlisten", "Prime Video", "Geschenkkarten", "Kundenservice", "Verkaufen bei Amazon")
                )
        );
    }

    @Tags({
            @Tag("SMOKE"),
            @Tag("WEB"),
    })
    @MethodSource
    @ParameterizedTest
    void amazonSiteShouldDisplayCorrectButtons(Language language, List<String> expectedButtons) {
        open("/");
        preparingPages.closePopupIfVisible();
        $("button.nav-flyout-button").shouldBe(visible).click();
        $("div#nav-flyout-icp").should(appear)
                .$$(byText(language.name())).find(visible)
                .shouldBe(enabled)
                .click();
        $$("#nav-xshop a").filter(visible).shouldHave(texts(expectedButtons));
    }

    @Tags({
            @Tag("SMOKE"),
            @Tag("WEB"),
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен отдавать не пустой список карточек")
    @ValueSource(strings = {
            "cup", "bag", "duck"
    })
    void amazonSiteSuccesfullSearchTest(String searchQuery) {
        open("/");
        preparingPages.closePopupIfVisible();
        $("input#twotabsearchtextbox").setValue(searchQuery).pressEnter();
        $$("div.s-main-slot div[data-component-type='s-search-result']")
                .shouldHave(sizeGreaterThan(0));

    }






}
