package stepDefinition;

import Pages.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Pages.LoginPage;
import org.testng.Assert;

import java.time.Duration;

public class LoginStepDefinitions {

    static WebDriver driver;
    WebDriverWait wait;
//static final ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();
//    private WebDriver driver = null;

    String BaseUrl = "https://qa.koel.app/";
    String baseUrl ="https://qa.koel.app/#!/home";

//    public static WebDriver getThreadLocal() {
//        return threadLocal.get();
//    }

    @Before
    public void iOpenBrowser() {
        WebDriverManager.chromedriver().setup();
//        WebDriverManager.edgedriver().setup();
        ChromeOptions options = new ChromeOptions();
//        EdgeOptions options= new EdgeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
//        driver = new EdgeDriver(options);
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
//        loginPage.provideEmail(email);
//        loginPage.providePassword(password);
//        loginPage.clickSubmit();
        loginPage.provideEmailToLogin(email);
        loginPage.providePasswordToLogin(password);
        loginPage.clickSubmitToLogin();
    }
    @And("I login with email {string} and password {string}")
    public void iLoginBackground(String email, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.provideEmailToLogin(email);
        loginPage.providePasswordToLogin(password);
        loginPage.clickSubmitToLogin();
    }
    @When("I login with empty email and password")
    public void iLoginWithEmptyEmailAndPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.provideEmailToLogin("");
        loginPage.providePasswordToLogin("");
        loginPage.clickSubmitToLogin();
    }
    @Then("I am logged in")
    public void iAmLoggedIn() {
        LoginPage loginPage = new LoginPage(driver);
        //Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img.avatar"))).isDisplayed());
        loginPage.loggedIn();
    }
    @Then("Log out button is existed")
    public void logoutBthExist(){
        HomePage homePage = new HomePage(driver);
        homePage.showLogoutButton();
    }

    @And("I click log out button")
    public void clickLogOutBtn(){
        HomePage homePage = new HomePage(driver);
        homePage.clickLogoutButton();
    }
    @When("I click logout button")
    public void clickLogoutBtn(){
        HomePage homePage = new HomePage(driver);
        homePage.clickLogoutButton();
    }
    @Then("I am not logged in")//log out from home page
    public void iAmNotLoggedIn(){
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl);
    }
    @Then("I am not logged in v0") //log out from profile page
    public void iAmNotLoggedInv0(){
        Assert.assertEquals(driver.getCurrentUrl(), BaseUrl);
    }
    @Then("I am not logged in v2") //log out from profile page
    public void iAmNotLoggedInv2(){
        String profileUrl = "https://qa.koel.app/#!/profile";
        Assert.assertEquals(driver.getCurrentUrl(), profileUrl);
    }
}
