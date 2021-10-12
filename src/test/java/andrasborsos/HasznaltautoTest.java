package andrasborsos;


import andrasborsos.PageObjects.HasznaltautoPage;
import andrasborsos.PageObjects.HasznaltautoSearchResultsPage;
import andrasborsos.resources.ChooseInitializeDriver;
import andrasborsos.resources.StringParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class HasznaltautoTest extends ChooseInitializeDriver implements StringParser {

   private String motorbikesAVGPrice="Jelenleg ";
   private String makeToFind="SUZUKI";
   private String modelToFind="XF 650";
   private WebDriver driver;

   private int euroRate=361;

    @BeforeTest
    public void initialize() {
        driver=initializeDriver();
        driver.get("https://www.hasznaltauto.hu/");
    }

    @Test
    public void motorbikesPrices(){
        HasznaltautoPage hasznaltautoPage=new HasznaltautoPage(driver);
        hasznaltautoPage.getAcceptCookiesBTN().click();
        hasznaltautoPage.getMotorbikeBTN().click();
        hasznaltautoPage.getMakeDropDown().click();
        hasznaltautoPage.getChosenMake(makeToFind).click();
        hasznaltautoPage.getModelDropDown().click();
        hasznaltautoPage.getChosenModel(modelToFind).click();
        hasznaltautoPage.getSearchBTN().click();

        HasznaltautoSearchResultsPage hasznaltautoSearchResultsPage=new HasznaltautoSearchResultsPage(driver);
        motorbikesAVGPrice+=hasznaltautoSearchResultsPage.getPrices().size();
        motorbikesAVGPrice+=(" darab eladó "+makeToFind+" "+modelToFind+" található a használtautó.hun. Átlagos áruk ");
        motorbikesAVGPrice+=parsePrices(hasznaltautoSearchResultsPage.getPrices());

    }

    @AfterTest
    public void postproc(){
        driver.close();
        editStringToRead();
        addToBeRead(motorbikesAVGPrice);
    }

    @Override
    public void editStringToRead() {
        motorbikesAVGPrice+=" forint.";
    }

    int parsePrices(ArrayList<WebElement> pricesList){
        String priceToParse;
        int priceAVG=0;
        for (WebElement webElement : pricesList) {
            //get the elements' text and delete hard spaces
            priceToParse = webElement.getText().replace(" ", "");
            if (priceToParse.contains("€")) {
                priceAVG += (Integer.parseInt(priceToParse.split("€")[1])) * euroRate;
            } else {
                priceAVG += Integer.parseInt(priceToParse.split("Ft")[0]);
            }
        }
        return (priceAVG/pricesList.size());
    }

}
