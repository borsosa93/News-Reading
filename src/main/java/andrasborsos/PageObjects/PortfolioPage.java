package andrasborsos.PageObjects;

import andrasborsos.resources.ChooseInitializeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class PortfolioPage extends ChooseInitializeDriver {

    public PortfolioPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    By acceptCookiesBTNLocator=new By.ByCssSelector("button.btn.btn-success");
    By dismissNotificationsBTNLocator=new By.ByCssSelector("a.dismiss.btn.btn-secondary");
    By moverLocator=new By.ByCssSelector("div.mover");
    By rateEURHUFLocator=new By.ByCssSelector("a[data-ticker='EURHUF'] > span.price");
    By rateUSDHUFLocator=new By.ByCssSelector("a[data-ticker='USDHUF'] > span.price");

    By coverPageTopLocator=new By.ByCssSelector("div.grid-6.mb-2");
    By coverPageTopArticlesLocator=new By.ByCssSelector("div > article > div > h3 > a");
    By coverPageBottomLocator=new By.ByCssSelector("div.col-md-12.d-flex");
    By coverPageBottomArticlesLocator=new By.ByCssSelector("div > article:nth-child(1) > h3 > a");

    public WebElement getAcceptCookiesBTN(){
        WebDriverWait webDriverWait=new WebDriverWait(driver,10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(acceptCookiesBTNLocator));
        return driver.findElement(acceptCookiesBTNLocator);
    }
    public WebElement getdismissNotificationsBTN(){
        return driver.findElement(dismissNotificationsBTNLocator);
    }
    public WebElement getEURHUF(){
        return driver.findElement(moverLocator).findElement(rateEURHUFLocator);
    }
    public WebElement getUSDHUF(){
        return driver.findElement(moverLocator).findElement(rateUSDHUFLocator);
    }
    public ArrayList<WebElement> getCoverPageArticles(){
        ArrayList<WebElement> coverPageArticles=(ArrayList<WebElement>) driver.findElement(coverPageTopLocator).findElements(coverPageTopArticlesLocator);
        ArrayList<WebElement> coverPageBottomArticles=(ArrayList<WebElement>)driver.findElement(coverPageBottomLocator).findElements(coverPageBottomArticlesLocator);
        coverPageArticles.addAll(coverPageBottomArticles);
        return coverPageArticles;
    }

}
