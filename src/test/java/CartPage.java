import com.codeborne.selenide.SelenideElement;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;


public class CartPage extends BaseTest {

    private final SelenideElement
            cartButton = $("[data-role=header-tab-button-minicart]"),
            addCartButton = $("[data-role=add-to-cart-button]"),
            sliderProduct = $x("//section[@class='slider-container slider-container_product  js-slider-container _visible'][1]//div[@data-slick-index='0']"),
            numberItemCart = $("[class=counter-number]"),
            listTitleBrand = $x("//header[@class='pdp-title pdp__form-title']//h1[@itemprop='name']"),
            cartTitleBrand = $x("//p[@class='product-card-item__name-primary']"),
            removeButton=$x("//button[@class='minicart-edit-remove']"),
            hoverProductArea =$x("//div[@class='product-card-item-details']"),
            cartEmptyTitle = $("[class = cart-title]");


    @Test
    @Story("Проверяем, что при первом открытии сайта корзина пустая")
    @DisplayName("Проверка, что корзина пустая")
    public void emptyCart() {
        step("Открываем главную страницу магазина", () ->
                open(""));
        step("Открываем корзину", () ->
                cartButton.click());
        step("Проверяем, что корзина пустая", () ->
                cartEmptyTitle.shouldHave(text("В корзине ничего нет...")));
    }

    @Test
    @Story("Проверяем возможно добавления товара в корзину")
    @DisplayName("Тест добавления товара в корзину")
    public void addItem() {
        step("Открываем главную страницу магазина", () ->
                open(""));
        step("Открываем карточку товара", () ->
                sliderProduct.click());

        step("Проверяем, что выбранный товар в корзине", () -> {
            String titleBrand = listTitleBrand.text();
            addCartButton.click();
            numberItemCart.shouldHave(text("1"));
            cartButton.click();
            String titleCart = cartTitleBrand.text();
            assertThat(titleCart).isEqualTo(titleBrand);
        });

    }

    @Test
    @Story("Проверяем возможно удаления товара из корзину")
    @DisplayName("Удаляем товар из корзины")
    public void delItem() {
        step("Открываем главную страницу магазина", () ->
                open(""));
        step("Открываем карточку товара", () ->
                sliderProduct.click());

        step("Проверяем, что выбранный товар в корзине", () -> {
            String titleBrand = listTitleBrand.text();
            addCartButton.click();
            numberItemCart.shouldHave(text("1"));
            cartButton.click();
            String titleCart = cartTitleBrand.text();
            assertThat(titleCart).isEqualTo(titleBrand);
        });

        step("удаление товара из корзины", () ->{
            hoverProductArea.hover();
            removeButton.click();
            cartEmptyTitle.shouldHave(text("В корзине ничего нет..."));
        });

    }
}