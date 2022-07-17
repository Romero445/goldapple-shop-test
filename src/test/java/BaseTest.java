import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.DriverConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.LocalTime;
import java.util.Objects;

import static Common.Config.CLEAR_RESULT;
import static com.codeborne.selenide.Selenide.closeWebDriver;




public class BaseTest{

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    static {
        LOGGER.info("START TIME"+ LocalTime.now());
        LOGGER.info("Start clear reports dir: dir/reports...");
        if(CLEAR_RESULT){
            File allureResults = new File("build/allure-results");
            for(File item: Objects.requireNonNull(allureResults.listFiles()))
                item.delete();
        }
    }
    @BeforeEach
    void BeforeEach() {
        DriverConfig.configure();
        Configuration.reportsFolder = "build/reports/tests";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void closeBrowser() {
        closeWebDriver();
    }


}
