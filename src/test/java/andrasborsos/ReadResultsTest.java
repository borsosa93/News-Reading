package andrasborsos;

import andrasborsos.PageObjects.GoogleTranslatePage;
import andrasborsos.resources.InitializeDriver;
import andrasborsos.resources.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.ArrayList;

public class ReadResultsTest extends InitializeDriver {

    private WebDriver driver;

    public ReadResultsTest() throws IOException {
    }

    @BeforeTest
    public void initialize(){
        driver=initializeDriver();
        driver.get("https://translate.google.com/");
    }

    @AfterSuite
    public void readResults() throws IOException {
        GoogleTranslatePage googleTranslatePage=new GoogleTranslatePage(driver);
        googleTranslatePage.getAcceptCookiesButton().click();
        ArrayList<String> textToBeRead= Utilities.getToBeRead();

        for(int i=0;i<textToBeRead.size();i++){
            googleTranslatePage.getInputTextBox().sendKeys(textToBeRead.get(i)+"\n");

        }
        googleTranslatePage.getReadBTN().click();
    }
}
