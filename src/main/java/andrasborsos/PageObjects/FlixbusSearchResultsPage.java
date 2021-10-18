package andrasborsos.PageObjects;

import andrasborsos.resources.ChooseInitializeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class FlixbusSearchResultsPage extends ChooseInitializeDriver {

    public FlixbusSearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    By resultsLocator= new By.ByCssSelector(".ResultsList__resultsList___3yIIM>div");
    By departureTimeLocator=new By.ByCssSelector("div.LocationsHorizontal__departureTime___1_wvV");
    By priceLocator=new By.ByCssSelector("div.SearchResultShop__price___2U4SB");
    By reservationErrorMessageLocator=new By.ByCssSelector("div.SearchResult__bookingMessage___10x2O");

    public ArrayList<ArrayList<WebElement>> getResults(){
        WebDriverWait webDriverWait=new WebDriverWait(driver,10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(departureTimeLocator));
        ArrayList<ArrayList<WebElement>> results=new ArrayList<>();
        //results.add(driver.findElements(departureTimeLocator),0);
        return null;
    }

    public ArrayList<WebElement> getDepartureTimes(){
        WebDriverWait webDriverWait=new WebDriverWait(driver,10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(departureTimeLocator));
        return (ArrayList<WebElement>) driver.findElements(departureTimeLocator);
    }
    public ArrayList<WebElement> getPrices(){
        return (ArrayList<WebElement>) driver.findElements(priceLocator);
    }
    public ArrayList<WebElement> getreservationErrorMessages(){
        return (ArrayList<WebElement>) driver.findElements(reservationErrorMessageLocator);
    }
}
