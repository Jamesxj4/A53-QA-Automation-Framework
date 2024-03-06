package stepDefinition;

import Pages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import static stepDefinition.LoginStepDefinitions.*;

public class CreateSmartPlaylistStepDefinitions {
    //HomePage homePage = new HomePage(driver);
    HomePage homePage = new HomePage(LoginStepDefinitions.driver);

    @And("I click Create Playlist button")
    public void clickCreatePlaylistBtn() {
        homePage.clickPlusIcon();
    }
    @When("I select Create Smart Playlist option")
    public void selectCreatePlaylistOption() {
        homePage.clickNewSmartPlaylistButton();
    }
    @And("I input playlist name {string}")
    public void inputPlaylistName(String smartPlaylistName) {
        homePage.smartPlaylistNameField.sendKeys(smartPlaylistName);
    }
    @And("I choose rule one Title contains {string}")
    public void chooseRuleOne(String ruleOneText) {
        homePage.RuleOneTitle.click();
        homePage.RuleOneContains.click();
        homePage.RuleOneTextField.sendKeys(ruleOneText);
    }
    @And("I click Save button")
    public void clickSaveBtnSmartPlaylist(){
        homePage.saveSmartPlaylistBtn.click();
    }
    @Then("Smart playlist {string} is created")
    public void smartPlaylistIsCreated(){
        Assert.assertTrue(homePage.isPlaylistCreatedMessageDisplayed());
    }

}
