package andrasborsos;

import andrasborsos.PageObjects.PortfolioPage;
import andrasborsos.resources.ChooseInitializeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class PortfolioTest extends ChooseInitializeDriver {

    WebDriver driver;
    ArrayList<String> ratesEURUSD=new ArrayList<>();
    ArrayList<String> articlesTitles=new ArrayList<>();

    @BeforeTest
    public void initialize(){
        driver=initializeDriver();
        driver.get("https://www.portfolio.hu/");
    }

    @Test
    public void exchangeRates(){
        String rate="Az euró árfolyama ";
        PortfolioPage portfolioPage=new PortfolioPage(driver);
        driver.switchTo().frame(1);
        portfolioPage.getAcceptCookiesBTN().click();
        driver.switchTo().defaultContent();
        portfolioPage.getdismissNotificationsBTN().click();
        rate+=portfolioPage.getEURHUF().getText()+" Ft.";
        ratesEURUSD.add(rate);
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
        addToBeRead(ratesEURUSD);
        addToBeRead(articlesTitles);
    }
}