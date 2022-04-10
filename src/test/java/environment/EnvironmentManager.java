package environment;

import com.automate.with.selenium.Resources;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;


public class EnvironmentManager {
    public static void initWebDriver()  {
        System.setProperty(Resources.DriverType,Resources.DriverLocation);
        WebDriver driver = new ChromeDriver();
        RunEnvironment.setWebDriver(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    public static void shutDownDriver() {
        RunEnvironment.getWebDriver().quit();
    }
}
