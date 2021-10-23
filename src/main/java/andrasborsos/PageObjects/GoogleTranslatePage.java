package andrasborsos.PageObjects;

import andrasborsos.resources.InitializeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class GoogleTranslatePage extends InitializeDriver {

    public GoogleTranslatePage(WebDriver driver)throws IOException {
        this.driver = driver;
    }

    WebDriver driver;

    By acceptCookiesBTNLocator =new By.ByCssSelector("button[jsname='higCR']");
    By inputTextBoxLocator=new By.ByCssSelector("textarea[class='er8xn']");
    By readBTNLocator =new By.ByCssSelector("button[jscontroller='xzbRj']");

    public WebElement getInputTextBox(){
        return driver.findElement(inputTextBoxLocator);
    }
    public WebElement getReadBTN(){
        WebDriverWait webDriverWait=new WebDriverWait(driver,5);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(readBTNLocator));
        return driver.findElement(readBTNLocator);
    }
    public WebElement getAcceptCookiesButton(){ return driver.findElement(acceptCookiesBTNLocator);}
    public void waitTillFinishedRead(){
        WebDriverWait webDriverWait=new WebDriverWait(driver,120);
        webDriverWait.until(ExpectedConditions.attributeToBe(readBTNLocator,"aria-pressed","false"));
    }
}
