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

        ArrayList<ArrayList<WebElement>> resultsOrganized=new ArrayList<>();
        ArrayList<WebElement> resultContainer=new ArrayList<>();

        ArrayList<WebElement> resultsRowsOnPage=(ArrayList<WebElement>)driver.findElements(resultsLocator);
        for(int i=0;i< resultsRowsOnPage.size();i++){
            resultContainer=null;
            addResultSubtypeToContainer(i,departureTimeLocator, driver,resultsRowsOnPage,resultContainer);
            addResultSubtypeToContainer(i,priceLocator, driver,resultsRowsOnPage,resultContainer);
            addResultSubtypeToContainer(i,departureTimeLocator, driver,resultsRowsOnPage,resultContainer);
            resultsOrganized.add(resultContainer);
        }
        return resultsOrganized;
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

    private ArrayList<WebElement> addResultSubtypeToContainer(int i, By resultSubtypeLocator,WebDriver driver, ArrayList<WebElement> resultsRowsOnPage, ArrayList<WebElement> resultContainer){
        if(resultsRowsOnPage.get(i).findElements(resultSubtypeLocator).size()>0){
            resultContainer.add(driver.findElement(resultSubtypeLocator));
        }
        else{
            resultContainer.add(null);
        }
        return resultContainer;
    }
}
