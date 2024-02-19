package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage{

    public HomePage(WebDriver givenDriver){
        super(givenDriver);
    }

    @FindBy(css = "i[class='fa fa-plus-circle create']")
    private WebElement plusIcon;
    @FindBy(css = "li[data-testid='playlist-context-menu-create-smart']")
    private WebElement newSmartPlaylistButton;
    @FindBy(css = "input[name='name']")
    private WebElement playlistNameField;
    @FindBy(xpath = "//input[@name='name']")
    public WebElement smartPlaylistNameField;
    @FindBy(xpath = "//div[@data-test='smart-playlist-rule-row'][1]//select[@name='model[]']/option[1]")
    public WebElement RuleOneTitle;
    @FindBy(xpath = "//div[@data-test='smart-playlist-rule-row'][1]//select[@name='operator[]']/option[3]")
    public WebElement RuleOneContains;
    @FindBy(xpath = "//div[@data-test='smart-playlist-rule-row'][1]//input[@type='text']")
    public WebElement RuleOneTextField;
    @FindBy(xpath = "//div[@class='smart-playlist-form']//button[@type='submit']")
    public WebElement saveSmartPlaylistBtn;
    @FindBy(css = "div.alertify-logs.top.right")
    private WebElement playlistCreatedMessageLocator;
    @FindBy(css = "div.success.show")
    private WebElement successNotification;

    //Page Elements
    By userAvatarIcon = By.cssSelector("img.avatar");

    By allSongsList = By.cssSelector("li a.songs");
    By byPlaylistCreatedMessageLocator= By.cssSelector("div.alertify-logs.top.right");

    //Page Methods
    public WebElement getUserAvatarIcon(){
        return findElementUsingByLocator(userAvatarIcon);
    }

    //Element Locator
    //By playlistInputField = By.cssSelector("[name='name']");
    //By notificationRenamePlaylistSuccessMsg = By.cssSelector("div.success.show");

    public void createSmartPlaylist(String playlistName) {
        clickPlusIcon();
        clickNewSmartPlaylistButton();
        createAPlaylistName(playlistName);
    }
    public void clickPlusIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(plusIcon)).click();
    }

    public void clickNewSmartPlaylistButton() {
        wait.until(ExpectedConditions.elementToBeClickable(newSmartPlaylistButton)).click();
    }
    public void createAPlaylistName(String playlistName) {
        //wait.until(ExpectedConditions.elementToBeClickable(playlistNameInputField)).sendKeys(playlistName);
    }
    public String getPlaylistName() {
        return playlistNameField.getAttribute("smartPlaylistName");
    }
    public String getRuleOneModel() {
        return RuleOneTitle.getText();
    }
    public String getRuleOneOperator() {
        return RuleOneContains.getText();
    }
    public String getRuleOneText() {
        return RuleOneTextField.getAttribute("value");
    }
    public boolean isPlaylistCreatedMessageDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(byPlaylistCreatedMessageLocator));
        return playlistCreatedMessageLocator.isDisplayed();
    }

    public void enterNewPlaylistName(String newName, By locator){
//        WebElement playlistInputField =
//                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        //clear does not work since element has an attribute of required.
        findElementUsingByLocator(locator).sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
        findElementUsingByLocator(locator).sendKeys(newName);
        findElementUsingByLocator(locator).sendKeys(Keys.ENTER);
        System.out.println("New Playlist name is entered");
    }
    public String getRenamePlaylistSuccessMsg(By locator){
        return findElementUsingByLocator(locator).getText();
    }

    //allSongs
    public WebElement allSongsList(){
        return findElementUsingByLocator(allSongsList);
    }
    public void goToAllSongsList(){
        findElementUsingByLocator(allSongsList).click();
    }


}
