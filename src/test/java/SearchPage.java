import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class SearchPage extends BaseTest {

    private final SelenideElement
            brand = $(byText("бренды")),
            searchBrandInput = $x("//input[@placeholder='найти бренд']"),
            brandTitle = $("#page-title-heading"),
            searchLoopButton=$("[data-role=header-tab-button-search]");


    @Test
    @Story("Проверка поиска по части теста")
    @DisplayName("поиск по части текста")
    public void testBrandSearchList() {

        step("Открываем главную страницу магазина", () ->
                open(""));

        step("Открываем страницу с бренды", () ->
                brand.click());

        step("Вводим бренд", () ->
                searchBrandInput.sendKeys("adi"));

        step("Переходим на страницу бренда", () ->
                $(byLinkText("Adidas")).click());

        step("Проверяем, что на странице выбранным брендом", () ->
                brandTitle.shouldHave(text("Adidas")));

    }


    @CsvSource(value = {
            "Adria, Adria",
            "Guerlain, Guerlain",
    })
    @Story("Checking a brand search")
    @DisplayName("search by name brand")
    @ParameterizedTest(name = "Search brand \"{0}\"")
    public void testBrandSearch (String testData, String expectedTex){
        step("Open a shop main page", () ->
                open(""));
        step("Open search", ()->
                searchLoopButton.click());
        step("Enter brand names to search for", ()->{
            $x("//textarea[@id='search_multiline']")
                    .setValue(testData)
                    .pressEnter();
        });
        step("checking page name", () -> {
            brandTitle.shouldHave(text(expectedTex)).shouldBe(visible);
        });

    }
    @Test
    @Story("alphabetical search")
    @DisplayName("Checking alphabetical search")
    public void alphabetBrandSearch(){
        step("Open a shop main page", () ->
                open(""));
        step("Open brand page", () ->
                brand.click());
        step("Choose a letter", () ->
                $(byText("B")).click());
        step("checking presence of block", () ->
                $x("//div[@class='brands-list']//dl[@class='brands-letter letter-B']").shouldBe(exist));
        step("checking, the we moved to right letter", () ->
                $("[data-anchor=letter-B]").shouldHave(text("B")));

    }


}
