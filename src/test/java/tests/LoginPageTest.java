package tests;

import com.automate.with.selenium.Resources;
import environment.EnvironmentManager;
import environment.RunEnvironment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.assertEquals;


public class LoginPageTest {
    @Before
    public void startBrowser()  {
        EnvironmentManager.initWebDriver();
    }

    @Test
    public void LoginPage() {
        System.out.println("Starting loginTest");
        WebDriver driver = RunEnvironment.getWebDriver();
        driver.get(Resources.StepUrl);
        WebElement Username = driver.findElement(By.name("username"));
        Username.clear();
        Username.sendKeys(Resources.Username);
        WebElement Password = driver.findElement(By.name("password"));
        Password.clear();
        Password.sendKeys(Resources.Password);
        WebElement LoginButton = driver.findElement(By.className("btn-block"));
        LoginButton.click();
        System.out.println("You have successfully logged in Step");

        // Verify the presence of username
        String LoginUser = driver.findElement(By.id("sessionDropdown")).getText();
        assertEquals("admin [admin]", LoginUser);

        // Logout from application
        WebElement LoggedUser = driver.findElement(By.className("glyphicon-user"));
        LoggedUser.click();
        WebElement LogoutButton = driver.findElement(By.className("glyphicon-off"));
        LogoutButton.click();
        System.out.println("You have successfully logged out from Step");

    }

    @After
    public void tearDown() {
        EnvironmentManager.shutDownDriver();
    }
}
