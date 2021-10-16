package andrasborsos;

import andrasborsos.PageObjects.GoogleTranslatePage;
import andrasborsos.resources.ChooseInitializeDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

public class ReadResultsTest extends ChooseInitializeDriver {

    private WebDriver driver;

    @BeforeTest
    public void initialize() throws IOException {
        driver=initializeDriver();
        driver.get("https://translate.google.com/");
    }

    @Test
    public void readResults(){
        GoogleTranslatePage googleTranslatePage=new GoogleTranslatePage(driver);
        googleTranslatePage.getAcceptCookiesButton().click();
        ArrayList<String> textToBeRead=googleTranslatePage.getToBeRead();

        for(int i=0;i<textToBeRead.size();i++){
            googleTranslatePage.getInputTextBox().sendKeys(textToBeRead.get(i)+"\n");

        }
        googleTranslatePage.getReadBTN().click();
    }
}
