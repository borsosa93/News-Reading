package andrasborsos;

import andrasborsos.PageObjects.PortfolioPage;
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
import static andrasborsos.resources.Utilities.setProperty;

public class PortfolioTest extends InitializeDriver {

    WebDriver driver;
    ArrayList<String> ratesEURUSD=new ArrayList<>();
    ArrayList<String> articlesTitles=new ArrayList<>();
    String rate;
    String EURHUF="0";
    String USDHUF="0";


    public PortfolioTest() throws IOException {
    }

    @BeforeTest
    public void initialize() throws IOException {
        driver=initializeDriver();
        driver.get(getProperty("portfolioURL"));
    }

    @Test
    public void exchangeRates() throws IOException {
        PortfolioPage portfolioPage=new PortfolioPage(driver);
        //This one changes to a frame, so in the next line the driver is returned to defaultContent()
        portfolioPage.getAcceptCookiesBTN().click();
        driver.switchTo().defaultContent();
        portfolioPage.getdismissNotificationsBTN().click();
        EURHUF=portfolioPage.getEURHUF().getText();
        if(!(EURHUF==null)){
            ratesEURUSD.add("Az euró árfolyama "+EURHUF+" Ft.");
            setProperty("EURHUF",EURHUF);
        }
        USDHUF=portfolioPage.getUSDHUF().getText();
        if(!(USDHUF==null)){
            ratesEURUSD.add("A dollár árfolyama "+USDHUF+" Ft.");
            setProperty("USDHUF",USDHUF);
        }
    }

    @Test(dependsOnMethods = {"exchangeRates"})
    public void economyArticles() throws IOException {
        articlesTitles.add("Gazdasági hírek a portfólió pont huról.");
        PortfolioPage portfolioPage=new PortfolioPage(driver);
        ArrayList<WebElement> articles=portfolioPage.getCoverPageArticles();
        for (WebElement article : articles) {
            articlesTitles.add(article.getText()+".");
        }
    }

    @AfterTest
    public void postproc(){
        driver.close();
        Utilities.addToBeRead(ratesEURUSD);
        Utilities.addToBeRead(articlesTitles);
    }
}