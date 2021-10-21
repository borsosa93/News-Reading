package andrasborsos;

import andrasborsos.PageObjects.HasznaltautoPage;
import andrasborsos.PageObjects.HasznaltautoSearchResultsPage;
import andrasborsos.resources.InitializeDriver;
import andrasborsos.resources.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

import static andrasborsos.resources.Utilities.getProperty;

public class HasznaltautoTest extends InitializeDriver {

   private ArrayList<String> motorbikesAVGPrice=new ArrayList<>();
   private String makeToFind=getProperty("make");
   private String modelToFind=getProperty("model");
   private WebDriver driver;

    public HasznaltautoTest() throws IOException {
    }

    @BeforeTest
    public void initialize() throws IOException {
        driver=initializeDriver();
        driver.get(getProperty("hasznaltautoURL"));
    }

    @Test
    public void motorbikesPrices() throws IOException {
        HasznaltautoPage hasznaltautoPage=new HasznaltautoPage(driver);
        hasznaltautoPage.getAcceptCookiesBTN().click();
        hasznaltautoPage.getMotorbikeBTN().click();
        hasznaltautoPage.getMakeDropDown().click();
        hasznaltautoPage.getChosenMake(makeToFind).click();
        hasznaltautoPage.getModelDropDown().click();
        hasznaltautoPage.getChosenModel(modelToFind).click();
        hasznaltautoPage.getSearchBTN().click();

        HasznaltautoSearchResultsPage hasznaltautoSearchResultsPage=new HasznaltautoSearchResultsPage(driver);
        motorbikesAVGPrice.add("Jelenleg "+hasznaltautoSearchResultsPage.getPrices().size()+" darab eladó "+makeToFind+" "+modelToFind+" található a használtautó.hun.");
        motorbikesAVGPrice.add("Átlagos áruk "+parsePrices(hasznaltautoSearchResultsPage.getPrices())+" Ft");
    }

    @AfterTest
    public void postproc(){
        driver.close();
        Utilities.addToBeRead(motorbikesAVGPrice);
    }

    int parsePrices(ArrayList<WebElement> pricesList) throws IOException {
        String priceToParse;
        int priceAVG=0;
        for (WebElement webElement : pricesList) {
            //get the elements' text and delete hard spaces
            priceToParse = webElement.getText().replace(" ", "");
            if (priceToParse.contains("€")) {
                priceAVG += (Integer.parseInt(priceToParse.split("€")[1])) * Integer.parseInt(getProperty("EURHUF"));
            } else {
                priceAVG += Integer.parseInt(priceToParse.split("Ft")[0]);
            }
        }
        return (priceAVG/pricesList.size());
    }

}
