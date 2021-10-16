package andrasborsos.PageObjects;

import andrasborsos.resources.ChooseInitializeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YoutubeSearchResultsPage extends ChooseInitializeDriver {

    public YoutubeSearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    By latestVideoLocator=new By.ByCssSelector("a[id='video-title'] > yt-formatted-string");

    public WebElement getlatestVideo(){
        WebDriverWait webDriverWait=new WebDriverWait(driver,5);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(latestVideoLocator));
        return driver.findElement(latestVideoLocator);
    }

}
