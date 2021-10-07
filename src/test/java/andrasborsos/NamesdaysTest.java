package andrasborsos;

import andrasborsos.PageObjects.NamesdaysPage;
import andrasborsos.resources.ChooseInitializeDriver;
import andrasborsos.resources.StringParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class NamesdaysTest extends ChooseInitializeDriver implements StringParser {

   private String namesdays="Ma ";
   private WebDriver driver;

    @BeforeTest
    public void initialize() throws IOException {
        driver=initializeDriver();
    }

    @Test
    public void todaysNamesdays(){
        NamesdaysPage namesdaysPage=new NamesdaysPage(driver);
        List<WebElement> namesdaysList=namesdaysPage.getNamesdays();
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

        editStringToRead();
        namesdaysPage.addToBeRead(namesdays);

        System.out.println(namesdaysPage.getToBeRead());
        namesdaysPage.driver.close();
    }

    @Override
    public void editStringToRead() {
        namesdays+="névnapja van.";
    }

}
