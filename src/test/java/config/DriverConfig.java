package config;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Properties;

import static config.ProjectConfig.isRemoteWebDriver;

public class DriverConfig {

    public static void configure() {

        Properties properties = System.getProperties();
        String system = properties.getProperty("type");
        if (system == null) {
            System.setProperty("type", "local");
        }

        Configuration.browser = ProjectConfig.config.getBrowserName();
        Configuration.browserSize = ProjectConfig.config.getBrowserSize();
        Configuration.baseUrl = ProjectConfig.config.getBaseUrl();
        Configuration.browserVersion = ProjectConfig.config.getBrowserVersion();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (isRemoteWebDriver()) {
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.remote = ProjectConfig.config.getRemoteUrl();
        }
        Configuration.browserCapabilities = capabilities;

    }

}
