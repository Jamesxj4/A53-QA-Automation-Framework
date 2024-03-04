package stepDefinition;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.ProfilePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ProfileStepDefinitions {

    HomePage homePage = new HomePage(LoginStepDefinitions.driver);
    ProfilePage profilePage = new ProfilePage(LoginStepDefinitions.driver);

    @And("I click the view profile button")
    public void clickProfileBtn()throws InterruptedException{
        //WebDriverWait wait = new WebDriverWait(LoginStepDefinitions.driver, Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.elementToBeClickable(homePage.userProfileIcon));
        //wait.until(ExpectedConditions.elementToBeClickable(homePage.userProfileIcon)).click();
       // homePage.userProfileIcon.click();
//        homePage.clickAvatarIcon();
        homePage.clickProfileIcon();
        Thread.sleep(2000);
        //homePage.getUserAvatarIcon().click();
    }
    @Then("I am in profile page")
    public void iAmInProfilePage(){
        LoginPage loginPage = new LoginPage(LoginStepDefinitions.driver);
        loginPage.loggedIn();
    }
    @And("I provide current password {string}")
    public void provideCurrentPassword(String password){
        //profilePage.enterCurrentPassword(password);
//        profilePage.currentPasswordField.clear();
        profilePage.currentPasswordField.sendKeys(password);
    }
    @And("I provide new name {string}")
    public void provideNewName(String newName){
        profilePage.enterNewName(newName);
//        profilePage.newNameField.clear();
//        profilePage.newNameField.sendKeys(newName);
    }
    @And("I provide new password {string}")
    public void provideNewPassword(String newPassword){
        profilePage.enterNewPassword(newPassword);
    }
    @And("I click profile save button")
    public void clickSaveBtn()throws InterruptedException{
        profilePage.profileSaveBtn.click();
        //System.out.println("Profile save button clicked.");
        Thread.sleep(2000);
    }
    @Then("Password updates successfully")
    public void passwordUpdatesSuccessfully(){
        String expectedProfileMessage = "Profile updated.";
        //Assert.assertEquals(profilePage.getProfileMessage(), expectedProfileMessage);
        Assert.assertEquals(profilePage.notificationShow.getText(), expectedProfileMessage);
    }
    @Then("Profile Name updates successfully")
    public void profileNameUpdatesSuccessfully(){
        String expectedProfileMessage = "Profile updated.";
        Assert.assertEquals(profilePage.notificationShow.getText(), expectedProfileMessage);
    }
    @And("I see successful notification")
    public void seeSuccessfulNotification(){
        profilePage.notificationShow.isDisplayed();
    }
    @And("I click notification") //removing the notification message to let log out button clickable
    public void clickNotification(){
        homePage.clickNotificationMsg();
    }
    @And("I click home icon")
    public void clickHomeIcon(){
        homePage.clickHomeIcon();
    }

}
