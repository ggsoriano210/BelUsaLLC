package com.BelUsa.managers;

import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;
import com.BelUsa.config.Configuration;
import com.BelUsa.config.ConfigurationManager;
import com.BelUsa.driver.DriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.BelUsa.driver.DriverManager.getDriver;

public class AllureManager {

    private AllureManager() {}

    public static void setAllureEnvironmentInformation() {
        Configuration configuration = ConfigurationManager.getConfiguration();

        AllureEnvironmentWriter.allureEnvironmentWriter(
                ImmutableMap.<String, String>builder().
                        put("Test URL", configuration.url()).
                        put("Target execution", configuration.target()).
                        put("Global timeout", String.valueOf(configuration.timeout())).
                        put("Headless mode", String.valueOf(configuration.headless())).
                        put("Local browser", configuration.browser()).
                        put("Grid URL", configuration.gridUrl()).
                        put("Grid port", configuration.gridPort()).
                        build());
    }

    @Step("Failed Step Screenshot")
    @Attachment(value = "Failed test screenshot", type = "image/png")
    public static byte[] takeScreenshotToAttachOnAllureReport() {

        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Browser information", type = "text/plain")
    public static String addBrowserInformationOnAllureReport() {
        return DriverManager.getInfo();
    }

}
