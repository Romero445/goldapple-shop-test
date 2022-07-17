package config;

import org.aeonbits.owner.ConfigFactory;

public class ProjectConfig {
    public static WebConfig config = ConfigFactory.create(WebConfig.class,System.getProperties());


    public static boolean isRemoteWebDriver(){
        return !config.getRemoteUrl().equals("");
    }
    public static String configBrowserSize(){
        return config.getBrowserSize();
    }
    public static String configBaseUrl(){
        return config.getBaseUrl();

    }
    public static String browserName(){
        return config.getBrowserName();
    }
}
