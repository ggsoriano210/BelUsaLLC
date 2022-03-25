

package com.BelUsa.driver;

import com.BelUsa.config.Configuration;
import com.BelUsa.config.ConfigurationManager;
import com.BelUsa.driver.local.LocalDriverManager;
import com.BelUsa.driver.remote.RemoteDriverManager;
import com.BelUsa.enums.Target;
import com.BelUsa.exceptions.TargetNotValidException;
import org.openqa.selenium.WebDriver;

public class DriverFactory implements IDriver {

    public WebDriver createInstance(String browser) {
        Configuration configuration = ConfigurationManager.getConfiguration();
        Target target = Target.valueOf(configuration.target().toUpperCase());
        WebDriver driver;

        switch (target) {

            case LOCAL:
                //override the browser value from @Optional on BaseClass
                driver = new LocalDriverManager().createInstance(configuration.browser());
                break;
            case GRID:
                // getting the browser from the suite file or @Optional on BaseClass
                driver = new RemoteDriverManager().createInstance(browser);
                break;
            default:
                throw new TargetNotValidException(target.toString());
        }


            driver.manage().window().maximize();

        return driver;
    }
}
