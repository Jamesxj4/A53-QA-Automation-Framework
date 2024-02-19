package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Pages.LoginPage;

import java.time.Duration;

public class LoginStepDefinitions {

    static WebDriver driver;
    WebDriverWait wait;
//static final ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();
//    private WebDriver driver = null;

    String BaseUrl = "https://qa.koel.app/";

//    public static WebDriver getThreadLocal() {
//        return threadLocal.get();
//    }

    @Before
    public void iOpenBrowser() {
//        WebDriverManager.chromedriver().setup();
        WebDriverManager.edgedriver().setup();
//        ChromeOptions options = new ChromeOptions();
        EdgeOptions options= new EdgeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
//        driver = new ChromeDriver(options);
        driver = new EdgeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //threadLocal.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void clearBrowser(){
        driver.quit();
    }
//    public void tearDown() {
//        threadLocal.get().close();
//        threadLocal.remove();
//    }

    @Given("I open login page")
    public void iOpenLogin() {
        //driver.get("https://qa.koel.app/");
        //LoginPage.openLogin();
        driver.get(BaseUrl);
    }

    @When("I login with valid email {string} and password {string}")
    public void iLogin(String email, String password) {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']"))).sendKeys(email);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.provideEmail(email);
        loginPage.providePassword(password);
        loginPage.clickSubmit();
    }
//    @Then("I am logged in")
//    public void iAmLoggedIn() {
//        LoginPage loginPage = new LoginPage(driver);
//        //Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img.avatar"))).isDisplayed());
//        loginPage.loggedIn();
//    }
}
