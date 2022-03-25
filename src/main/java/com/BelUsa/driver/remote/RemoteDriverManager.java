package com.BelUsa.driver.remote;

import com.BelUsa.config.Configuration;
import com.BelUsa.config.ConfigurationManager;
import com.BelUsa.driver.IDriver;
import com.BelUsa.exceptions.BrowserNotSupportedException;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteDriverManager implements IDriver {

    private static final Logger logger = LogManager.getLogger(RemoteDriverManager.class);
    public Configuration configuration;

    @Override
    public WebDriver createInstance(String browser) {
        RemoteWebDriver remoteWebDriver = null;
        Configuration configuration = ConfigurationManager.getConfiguration();
        try {
            // a composition of the target grid address and port
            String gridURL = String.format("http://%s:%s/wd/hub", configuration.gridUrl(), configuration.gridPort());

            remoteWebDriver = new RemoteWebDriver(new URL(gridURL), getCapability(browser));
        } catch (MalformedURLException e) {
            logger.error("Grid URL is invalid or Grid is not available");
            logger.error(String.format("Browser: %s", browser), e);
        } catch (IllegalArgumentException e) {
            logger.error(String.format("Browser %s is not valid or recognized", browser), e);
        }

        return remoteWebDriver;
    }

    private static MutableCapabilities getCapability(String browser) {
        MutableCapabilities mutableCapabilities;
        DriverManagerType driverManagerType = DriverManagerType.valueOf(browser.toUpperCase());

        switch (driverManagerType) {

            case CHROME:
                mutableCapabilities = chromeOptions();
                break;
            case FIREFOX:
                mutableCapabilities = firefoxOptions();
                break;
            case OPERA:
                mutableCapabilities = new OperaOptions();
                break;
            case EDGE:
                mutableCapabilities = edgeOptions();
                break;
            case IEXPLORER:
                mutableCapabilities = new InternetExplorerOptions();
                break;
            case SAFARI:
                mutableCapabilities =  safariOptions();
                break;
            default:
                throw new BrowserNotSupportedException(driverManagerType.toString());
        }

        return mutableCapabilities;
    }

    private static MutableCapabilities chromeOptions() {
        ChromeOptions capabilities = new ChromeOptions();
        capabilities.addArguments("start-maximized");

        return capabilities;
    }

    private static MutableCapabilities firefoxOptions() {
        FirefoxOptions capabilities = new FirefoxOptions();
        capabilities.addArguments("start-maximized");

        return capabilities;
    }

    private static MutableCapabilities edgeOptions() {
        EdgeOptions capabilities = new EdgeOptions();
//        capabilities.addArguments("start-maximized");
        capabilities.setCapability("resolution", "1920x1080");

        return capabilities;
    }

    private static MutableCapabilities safariOptions() {
        SafariOptions capabilities = new SafariOptions();
//        capabilities.setUseTechnologyPreview(true);
        capabilities.getAutomaticInspection();
        capabilities.getAutomaticProfiling();

        return capabilities;
    }


    public Boolean getWindowSize() {
        String windowSize = configuration.windowMaximized();
        if(windowSize != null) {
            return Boolean.valueOf(windowSize);
        }else {
            return true;
        }
    }
}
