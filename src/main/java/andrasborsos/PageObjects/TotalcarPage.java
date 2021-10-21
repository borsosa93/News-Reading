package andrasborsos.PageObjects;

import andrasborsos.resources.InitializeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.ArrayList;

public class TotalcarPage extends InitializeDriver {

    public TotalcarPage(WebDriver driver)throws IOException {
        this.driver=driver;
    }

    WebDriver driver;

    By acceptCookiesBTNLocator=new By.ByCssSelector("button[aria-label='ELFOGADOM']");
    By coverPageLeftArticlesLocator =new By.ByCssSelector("section[class*='dupla-vezeto-blokk'] > div:nth-child(1) > div > article > div > div > div > h1 > a");
    By newsAboutWTCRLocator =new By.ByXPath("//a[contains(text(),'WTCR')]");
    By newsAboutMicheliszLocator =new By.ByXPath("//a[contains(text(),'Michelisz')]");

    public WebElement getacceptCookiesBTN(){
        WebDriverWait webDriverWait=new WebDriverWait(driver,5);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(acceptCookiesBTNLocator));
        return driver.findElement(acceptCookiesBTNLocator);
    }
    public ArrayList<WebElement> getcoverPageLeftArticles(){
        return (ArrayList<WebElement>)driver.findElements(coverPageLeftArticlesLocator);
    }
    public ArrayList<WebElement> getnewsAboutWTCR(){
        ArrayList<WebElement> newsAboutWTCR= (ArrayList<WebElement>)driver.findElements(newsAboutWTCRLocator);
        newsAboutWTCR.addAll((ArrayList<WebElement>)driver.findElements(newsAboutMicheliszLocator));
        return newsAboutWTCR;
    }
}
