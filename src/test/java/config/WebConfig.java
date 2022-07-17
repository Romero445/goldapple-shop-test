package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${type}.properties"
})

public interface WebConfig extends Config {

        @Key("browserName")
        @DefaultValue("Chrome")
        String getBrowserName();

        @Key("browserVersion")
        String getBrowserVersion();

        @Key("browserSize")
        @DefaultValue("1920x1080")
        String getBrowserSize();

        @Key("remoteUrl")
        String getRemoteUrl();

        @Key("baseUrl")
        String getBaseUrl();

}
