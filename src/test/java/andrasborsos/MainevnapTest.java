package andrasborsos;

import andrasborsos.PageObjects.MainevnapPage;
import andrasborsos.resources.ChooseInitializeDriver;
import andrasborsos.resources.StringParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class MainevnapTest extends ChooseInitializeDriver implements StringParser {

   private String namesdays="Ma ";
   private WebDriver driver;

    @BeforeTest
    public void initialize() throws IOException {
        driver=initializeDriver();
    }

    @Test
    public void todaysNamesdays(){
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
        editStringToRead();
        addToBeRead(namesdays);
    }

    @Override
    public void editStringToRead() {
        namesdays+="névnapja van.";
    }

}
