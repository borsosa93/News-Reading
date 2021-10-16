package andrasborsos;

import andrasborsos.PageObjects.YoutubeHomePage;
import andrasborsos.PageObjects.YoutubeSearchResultsPage;
import andrasborsos.resources.ChooseInitializeDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class YoutubeRidersTest extends ChooseInitializeDriver {

    WebDriver driver;
    String videoTitle="";

    @BeforeTest
    public void initialize(){
        this.driver=initializeDriver();
        driver.get("https://www.youtube.com/");
    }

    @Test
    public void ridersNewVideoTest(){
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
