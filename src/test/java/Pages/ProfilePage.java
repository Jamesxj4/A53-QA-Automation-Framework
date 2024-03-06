package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ProfilePage extends BasePage{
    public ProfilePage(WebDriver givenDriver){
        super(givenDriver);
    }

    @FindBy(xpath = "//input[@id='inputProfileCurrentPassword']")
    public WebElement currentPasswordField;
    @FindBy(xpath = "//input[@id='inputProfileNewPassword']")
    public WebElement newPasswordField;
    @FindBy(xpath = "//input[@id='inputProfileEmail']")
    private WebElement emailField;
    @FindBy(xpath = "//input[@id='inputProfileName']")
    public WebElement newNameField;
    @FindBy(xpath = "//button[@class='btn-submit']")
    public WebElement profileSaveBtn;
    @FindBy(xpath = "//div[@class='success show']")
    public WebElement notificationShow;
    @FindBy(xpath = "//img[@class='avatar']")
    private WebElement userProfileIcon;


    public ProfilePage enterNewName(String newName){
        newNameField.clear();
        newNameField.sendKeys(newName);
        return this;
    }
    public ProfilePage enterCurrentPassword(String currentPassword){
        newNameField.clear();
        newNameField.sendKeys(currentPassword);
        return this;
    }
    public ProfilePage enterNewPassword(String newPassword){
        newNameField.clear();
        newNameField.sendKeys(newPassword);
        return this;
    }
    public ProfilePage getProfileMessage(){
        notificationShow.getText();
        return this;
    }
    public void clickSaveButton(){
        actions.moveToElement(findElementClickable(profileSaveBtn)).click().perform();
    }
    public void updatePassword(String password, String newPassword){
        findElementVisibility(userProfileIcon).click();
        findElementVisibility(currentPasswordField).sendKeys(password);
        findElementVisibility(newPasswordField).sendKeys(newPassword);
        actions.moveToElement(findElementClickable(profileSaveBtn)).click().perform();
        String expectedProfileNotification = "Profile updated.";
        Assert.assertEquals(findElementVisibility(notificationShow).getText(), expectedProfileNotification);
    }
}
