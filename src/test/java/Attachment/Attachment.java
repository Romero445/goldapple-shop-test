package Attachment;

import com.codeborne.selenide.Selenide;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class Attachment {

    @io.qameta.allure.Attachment(value = "{attachName}", type = "text/plain")
    public static String attachAsText(String attachName, String message) {
        return message;
    }

    @io.qameta.allure.Attachment(value = "Page source", type = "text/plain")
    public static byte[] pageSource() {

        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }


    public static void browserConsoleLogs() {
        attachAsText(
                "Browser console logs",
                String.join("\n", Selenide.getWebDriverLogs(BROWSER))
        );
    }

}
