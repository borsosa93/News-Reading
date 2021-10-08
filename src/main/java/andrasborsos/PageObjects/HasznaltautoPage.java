package andrasborsos.PageObjects;

import andrasborsos.resources.ChooseInitializeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HasznaltautoPage extends ChooseInitializeDriver {

    public HasznaltautoPage(WebDriver driver) {
        this.driver=driver;
    }

    WebDriver driver;

    By acceptCookiesBTNLocator= new By.ByCssSelector("a[id='CybotCookiebotDialogBodyButtonAccept']");
    By motorbikeBTNLocator= new By.ByCssSelector("li[data-original-title='Motorkerékpár/quad']");
    By makeDropDownLocator=new By.ById("hirdetesmotorsearch-marka_id");
    By modelDropDownLocator=new By.ById("hirdetesmotorsearch-modell_id");
    By searchBTNLocator=new By.ByCssSelector("button[name='submitKereses']");
    By listOptionLocator=new By.ByTagName("option");

    public WebElement getAcceptCookiesBTN(){
        WebDriverWait webDriverWait=new WebDriverWait(driver,5);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(acceptCookiesBTNLocator));
        return driver.findElement(acceptCookiesBTNLocator);
    }
    public WebElement getMotorbikeBTN(){
        return driver.findElement(motorbikeBTNLocator);
    }

    public WebElement getMakeDropDown(){
        return driver.findElement(makeDropDownLocator);
    }
    public WebElement getModelDropDown(){
        return driver.findElement(modelDropDownLocator);
    }

    public WebElement getChosenMake(String make){
        return driver.findElement(makeDropDownLocator).findElement(By.xpath("//option[contains(text(),'SUZUKI')]"));
    }
    public WebElement getChosenModel(String model){
        return driver.findElement(modelDropDownLocator).findElement(By.xpath("//option[contains(text(),'XF')]"));
    }

    public WebElement getSearchBTN(){
        return driver.findElement(searchBTNLocator);
    }

}

