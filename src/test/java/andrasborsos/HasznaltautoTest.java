package andrasborsos;


import andrasborsos.PageObjects.HasznaltautoPage;
import andrasborsos.resources.ChooseInitializeDriver;
import andrasborsos.resources.StringParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;


public class HasznaltautoTest extends ChooseInitializeDriver implements StringParser {

   private String motorbikes=new String();
   private WebDriver driver;

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
        hasznaltautoPage.getChosenMake("SUZUKI").click();
        hasznaltautoPage.getModelDropDown().click();
        hasznaltautoPage.getChosenModel("XF 650").click();
    }

    @Override
    public void editStringToRead() {
        motorbikes+="névnapja van.";
    }



}
