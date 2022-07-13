import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    @BeforeEach
    void BeforeEach() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://goldapple.ru";
        SelenideLogger.addListener("allure", new AllureSelenide());

    }

    @AfterEach
    void closeBrowser() {
        closeWebDriver();

    }

}
