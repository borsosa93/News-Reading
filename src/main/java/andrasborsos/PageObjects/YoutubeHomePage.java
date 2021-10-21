package andrasborsos.PageObjects;

import andrasborsos.resources.InitializeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class YoutubeHomePage extends InitializeDriver {

    public YoutubeHomePage(WebDriver driver) throws IOException {
        this.driver = driver;
    }

    WebDriver driver;

    By acceptCookiesBTNLocator=new By.ByCssSelector("tp-yt-paper-dialog[id='dialog'] > div:nth-child(2) > div:nth-of-type(2) > div:nth-child(5)> div:nth-child(2) > ytd-button-renderer:nth-child(2)");
    //By acceptCookiesBTNLocator =new By.ByCssSelector("");
    By inputTextBoxLocator=new By.ByCssSelector("input[id='search']");
    By searchBTNLocator=new By.ByCssSelector("button[id='search-icon-legacy']");

    public WebElement getAcceptCookiesButton(){
        WebDriverWait webDriverWait=new WebDriverWait(driver,10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(acceptCookiesBTNLocator));
        return driver.findElement(acceptCookiesBTNLocator);
    }
    public WebElement getInputTextBox(){
        WebDriverWait webDriverWait=new WebDriverWait(driver,5);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(inputTextBoxLocator));
        return driver.findElement(inputTextBoxLocator);
    }
    public WebElement getsearchBTN(){
        return driver.findElement(searchBTNLocator);
    }
}
