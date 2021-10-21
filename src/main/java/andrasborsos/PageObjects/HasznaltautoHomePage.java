package andrasborsos.PageObjects;

import andrasborsos.resources.InitializeDriver;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class HasznaltautoHomePage extends InitializeDriver {

    public HasznaltautoHomePage(WebDriver driver) throws IOException {
        this.driver=driver;
        webDriverWait=new WebDriverWait(driver,8);
    }

    WebDriver driver;
    WebDriverWait webDriverWait;

    By acceptCookiesBTNLocator= new By.ByCssSelector("a[id='CybotCookiebotDialogBodyButtonAccept']");
    By acceptCookiesTabLocator= new By.ByCssSelector("#CybotCookiebotDialog");
    By motorbikeBTNLocator= new By.ByCssSelector("li[data-original-title='Motorkerékpár/quad']");
    By makeDropDownLocator=new By.ById("hirdetesmotorsearch-marka_id");
    By modelDropDownLocator=new By.ById("hirdetesmotorsearch-modell_id");
    By searchBTNLocator=new By.ByCssSelector("button[name='submitKereses']");
    By listOptionLocator=new By.ByTagName("option");

    public WebElement getAcceptCookiesBTN(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(acceptCookiesBTNLocator));
        return driver.findElement(acceptCookiesBTNLocator);
    }
    public WebElement getMotorbikeBTN(){
        webDriverWait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(acceptCookiesTabLocator,"style","opacity: 1")));
        return driver.findElement(motorbikeBTNLocator);
    }

    public WebElement getMakeDropDown(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(makeDropDownLocator));
        return driver.findElement(makeDropDownLocator);
    }
    public WebElement getModelDropDown(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(modelDropDownLocator));
        return driver.findElement(modelDropDownLocator);
    }

    public WebElement getChosenMake(String make){
        return driver.findElement(makeDropDownLocator).findElement(By.xpath("//option[contains(text(),'"+make+"')]"));
    }
    public WebElement getChosenModel(String model){
        return driver.findElement(modelDropDownLocator).findElement(By.xpath("//option[contains(text(),'"+model+"')]"));
    }

    public WebElement getSearchBTN(){
        return driver.findElement(searchBTNLocator);
    }

}

