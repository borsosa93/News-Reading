package andrasborsos;

import andrasborsos.PageObjects.MainevnapPage;
import andrasborsos.resources.InitializeDriver;
import andrasborsos.resources.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static andrasborsos.resources.Utilities.getProperty;

public class MainevnapTest extends InitializeDriver {

   private String namesdays="Ma ";
   private WebDriver driver;

    public MainevnapTest() throws IOException {
    }

    @BeforeTest
    public void initialize() throws IOException {
        driver=initializeDriver();
        driver.get(getProperty("maiNevnapURL"));
    }

    @Test
    public void todaysNamesdays() throws IOException {
        MainevnapPage mainevnapPage=new MainevnapPage(driver);
        List<WebElement> namesdaysList=mainevnapPage.getNamesdays();
        Iterator<WebElement> iterator= namesdaysList.listIterator();

        while (iterator.hasNext()){
            String textToAdd=iterator.next().getText();
            if(iterator.hasNext()){
                namesdays+=textToAdd+", ";
            }
            else{
                namesdays+=textToAdd+" ";
            }
        }
    }

    @AfterTest
    public void postproc(){
        driver.close();
        Utilities.addToBeRead((namesdays+"névnapja van."));
    }


}
