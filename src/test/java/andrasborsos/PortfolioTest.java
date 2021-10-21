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

    @BeforeTest
    public void initialize() throws IOException {
        driver=initializeDriver();
        driver.get(getProperty("portfolioURL"));
    }

    @Test
    public void exchangeRates() throws IOException {
        PortfolioPage portfolioPage=new PortfolioPage(driver);
        driver.switchTo().frame(1);
        portfolioPage.getAcceptCookiesBTN().click();
        driver.switchTo().defaultContent();
        portfolioPage.getdismissNotificationsBTN().click();
        setProperty("EURHUF",portfolioPage.getEURHUF().getText());
        String rate="Az euró árfolyama ";
        rate+=portfolioPage.getEURHUF().getText()+" Ft.";
        ratesEURUSD.add(rate);
        setProperty("USDHUF",portfolioPage.getUSDHUF().getText());
        rate="A dollár árfolyama ";
        rate+=portfolioPage.getUSDHUF().getText()+" Ft.";
        ratesEURUSD.add(rate);
    }

    @Test(dependsOnMethods = {"exchangeRates"})
    public void economyArticles(){
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