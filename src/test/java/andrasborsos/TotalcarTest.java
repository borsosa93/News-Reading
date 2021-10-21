package andrasborsos;

import andrasborsos.PageObjects.TotalcarPage;
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

public class TotalcarTest extends InitializeDriver {

    WebDriver driver;
    ArrayList<String> coverPageArticlesTitles =new ArrayList<>();
    ArrayList<String> WTCRArticlesTitles =new ArrayList<>();

    @BeforeTest
    public void initialize() throws IOException {
        this.driver=initializeDriver();
        driver.get(getProperty("totalcarURL"));
    }

    @Test
    public void carNews(){
        TotalcarPage totalcarPage=new TotalcarPage(driver);
        totalcarPage.getacceptCookiesBTN().click();
        coverPageArticlesTitles.add("Autós-motoros hírek a totalcar pont huról.");
        ArrayList<WebElement> articles=totalcarPage.getcoverPageLeftArticles();
        for (WebElement article : articles) {
            coverPageArticlesTitles.add(article.getText()+".");
        }
        if(totalcarPage.getnewsAboutWTCR().size()>0){
            articles=totalcarPage.getnewsAboutWTCR();
            for (WebElement article : articles) {
                WTCRArticlesTitles.add(article.getText()+".");
            }
        }
    }
    @AfterTest
    public void postproc(){
        driver.close();
        Utilities.addToBeRead(coverPageArticlesTitles);
        if(WTCRArticlesTitles.size()>0){
            Utilities.addToBeRead(WTCRArticlesTitles);
        }
    }

}
