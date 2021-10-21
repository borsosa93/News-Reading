package andrasborsos.PageObjects;

import andrasborsos.resources.InitializeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.ArrayList;

public class HasznaltautoSearchResultsPage extends InitializeDriver {

    public HasznaltautoSearchResultsPage(WebDriver driver) throws IOException {
        this.driver=driver;
    }

    WebDriver driver;

    By resultsLocator =By.cssSelector("div[class*='talalati-sor']");
    By pricesLocator =By.cssSelector("div[class='vetelar']");
    By nextResultsPageBTNLocator= By.cssSelector(".lapozoNyilJobb.haicon.haicon-uj-jnyil-kicsi");

    public ArrayList<WebElement> getPrices(){
        WebDriverWait webDriverWait=new WebDriverWait(driver,5);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(pricesLocator));

        ArrayList<WebElement> results=(ArrayList<WebElement>)driver.findElements(resultsLocator);
        ArrayList<WebElement> prices=new ArrayList<>();

        for (WebElement result : results) {
            prices.add(result.findElement(pricesLocator));
        }
        return prices;
    }

    public WebElement getnextResultsPageBTN(){
        if(!driver.findElement(nextResultsPageBTNLocator).equals(null))
        return driver.findElement(nextResultsPageBTNLocator);
        else return null;
    }
}
