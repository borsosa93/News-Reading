package andrasborsos.PageObjects;

import andrasborsos.resources.ChooseInitializeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Vector;

public class HasznaltautoSearchResultsPage extends ChooseInitializeDriver {

    public HasznaltautoSearchResultsPage(WebDriver driver) {
        this.driver=driver;
    }

    WebDriver driver;

    //Paid and normal ads'parent node
    By resultsLocator =By.cssSelector("div[class*='talalati-sor']");
    By pricesLocator =By.cssSelector("div[class='vetelar']");
    By nextResultsPageBTNLocator= By.cssSelector(".lapozoNyilJobb.haicon.haicon-uj-jnyil-kicsi");

    public ArrayList<WebElement> getPrices(){
        ArrayList<WebElement> results=(ArrayList<WebElement>)driver.findElements(resultsLocator);
        ArrayList<WebElement> prices=new ArrayList<>();

        for(int i = 0; i< results.size(); i++){
            prices.add(results.get(i).findElement(pricesLocator));
        }
        return prices;
    }

    public WebElement getnextResultsPageBTN(){
        return driver.findElement(nextResultsPageBTNLocator);
    }
}
