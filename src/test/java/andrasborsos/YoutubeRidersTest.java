package andrasborsos;

import andrasborsos.PageObjects.YoutubeHomePage;
import andrasborsos.PageObjects.YoutubeSearchResultsPage;
import andrasborsos.resources.InitializeDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class YoutubeRidersTest extends InitializeDriver {

    WebDriver driver;
    String videoTitle="";

    public YoutubeRidersTest() throws IOException {
    }

    @BeforeTest
    public void initialize(){
        this.driver=initializeDriver();
        driver.get("https://www.youtube.com/");
    }

    @Test
    public void ridersNewVideoTest() throws IOException {
        YoutubeHomePage youtubeHomePage=new YoutubeHomePage(driver);
        youtubeHomePage.getAcceptCookiesButton().click();
        youtubeHomePage.getInputTextBox().click();
        youtubeHomePage.getInputTextBox().sendKeys("r1d3rs");
        youtubeHomePage.getsearchBTN().click();

        YoutubeSearchResultsPage youtubeSearchResultsPage=new YoutubeSearchResultsPage(driver);
        videoTitle=youtubeSearchResultsPage.getlatestVideo().getText();
        System.out.println(videoTitle);
    }
}
