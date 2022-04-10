package tests;

import com.automate.with.selenium.Resources;
import environment.EnvironmentManager;
import environment.RunEnvironment;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class PageSurfingTest {

    @Before
    public void startBrowser()  {
        EnvironmentManager.initWebDriver();
    }

    @Test
    public void PageSurfing() throws InterruptedException {

        System.out.println("Starting Surfing Test");
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //looking for New Plan button
        WebElement NewPlanButton = driver.findElement(By.xpath("//button[normalize-space()='New plan']"));
        Assert.assertTrue(NewPlanButton.isDisplayed());
        //move to Keywords tab
        WebElement Keywords = driver.findElement(By.cssSelector("a[ng-click=\"setView('functions')\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(Keywords));
        Keywords.click();
        //test that new keyword is available
        sleep(1000);
        WebElement NewKeyword = driver.findElement(By.cssSelector("button[class='btn btn-success']"));
        NewKeyword.click();
        WebElement Cancel = driver.findElement(By.xpath("//button[normalize-space()='Cancel']"));
        Cancel.click();
        //switch to Parameters Tab
        WebElement Parameters = driver.findElement(By.className("glyphicon-list-alt"));
        Parameters.click();
        String CurrentUrl = driver.getCurrentUrl();
        //verify the URL
        assertEquals("The URL is the one expected","http://localhost:8080/#/root/parameters", CurrentUrl);
        //move to Execution Tab
        WebElement Executions = driver.findElement(By.className("glyphicon-tasks"));
        Executions.click();
        //verify if the page was loaded
        String  ExecList = driver.findElement(By.xpath("//a[normalize-space()='Execution list']")).getText();
        assertEquals("The tab was loaded successfully", "Execution list", ExecList);

        //move to Scheduler Tab
        WebElement Scheduler = driver.findElement(By.className("glyphicon-time"));
        Scheduler.click();
        sleep(1000);
        WebElement NewTaskButton = driver.findElement(By.xpath("//button[normalize-space()='New task']"));
        Assert.assertTrue(NewTaskButton.isDisplayed());
       //move to Grid Tab
        WebElement Grid = driver.findElement(By.className("glyphicon-th"));
        Grid.click();
        //click on tokens
        WebElement Tokens = driver.findElement(By.xpath("//a[normalize-space()='Tokens']"));
        Tokens.click();
       //click on Token Group
        WebElement TokensGroup = driver.findElement(By.xpath("//a[normalize-space()='Token Groups']"));
        TokensGroup.click();
       //click on QuotaManager
        WebElement QuotaManager = driver.findElement(By.xpath("//a[normalize-space()='Quota Manager']"));
        QuotaManager.click();
      // move to Admin Tab
        WebElement AdminTab = driver.findElement(By.className("glyphicon-cog"));
        AdminTab.click();

        // add new user
        WebElement NewUserButton = driver.findElement(By.xpath("//button[normalize-space()='Add user']"));
        NewUserButton.click();
        WebElement NewUsername = driver.findElement(By.cssSelector("#username"));
        NewUsername.sendKeys("User1");
        WebElement SaveUser = driver.findElement(By.xpath("//button[normalize-space()='Save']"));
        SaveUser.click();
        WebElement CloseButton = driver.findElement(By.xpath("//button[normalize-space()='Close']"));
        CloseButton.click();
       // check the presence of new user
        WebElement UserCreated = driver.findElement(By.xpath("//a[normalize-space()='User1']"));
        Assert.assertTrue(UserCreated.isDisplayed());

        WebElement Questions = driver.findElement(By.className("glyphicon-question-sign"));
        Questions.click();

        System.out.println("Test finished");
    }


    @After
    public void tearDown() {
        EnvironmentManager.shutDownDriver();
    }
}