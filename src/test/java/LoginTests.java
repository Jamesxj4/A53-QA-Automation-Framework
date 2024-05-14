import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class
LoginTests extends BaseTest {



    @Test
    public void navigateToKoelApp() {
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

    @Test
    public void loginValidEmailPassword() throws InterruptedException {
        //try {
            //Thread.sleep(2000);
            provideEmail("james.lu@testpro.io");
            //Thread.sleep(2000);
            providePassword("QnNBjg75$$");
            //Thread.sleep(2000);
            clickSubmit();
        Thread.sleep(2000);
            //Assertion
            //WebElement avatarIcon = driver.findElement(By.cssSelector("img[class='avatar']"));
            WebElement avatarIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[class='avatar']")));
            Assert.assertTrue(avatarIcon.isDisplayed());
       // }catch (Exception e){
       //    System.out.println("Something went wrong !" +e);
       // }

//        JavascriptExecutor js = (JavascriptExecutor)driver;
//        driver.manage().timeouts().setScriptTimeout(1,TimeUnit.SECONDS);
//        js.executeAsyncScript("window.scrollBy(0,document.body.scrollHeight)");
//        Thread.sleep(4000);
//        WebElement topAlbumsIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"homeWrapper\"]/div/section[3]/h1")));
//        WebElement topAlbumsIcon = driver.findElement(By.xpath("//*[@id=\"homeWrapper\"]/div/section[3]/h1"));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", topAlbumsIcon);
//        Thread.sleep(5000);
//
//        Assert.assertTrue(topAlbumsIcon.isDisplayed());

        //*[@id="homeWrapper"]/div/section[3]/h1

    }

    @Test
    public void loginWithInvalidEmailValidPassword() throws InterruptedException {
        provideEmail("invalid@class.com");
        providePassword("te$t$tudent");
        clickSubmit();
        Thread.sleep(2000);
        //Expected Result
        Assert.assertEquals(driver.getCurrentUrl(),url);

    }
    @Test
    public void loginWithValidEmailInvalidPassword() throws InterruptedException {
        provideEmail("demo@class.com");
        providePassword("invalidPassword");
        clickSubmit();
        Thread.sleep(2000);
        //Expected Result
        Assert.assertEquals(driver.getCurrentUrl(),url);
    }

}

