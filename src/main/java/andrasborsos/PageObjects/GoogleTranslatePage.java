package andrasborsos.PageObjects;

import andrasborsos.resources.ChooseInitializeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleTranslatePage extends ChooseInitializeDriver {

    public GoogleTranslatePage() {
        this.driver = initializeDriver();
        driver.get("https://translate.google.com/");
    }

    WebDriver driver;

    By acceptCookiesBTN=new By.ByCssSelector("button[jsname='higCR']");
    By inputTextBox=new By.ByCssSelector("textarea[class='er8xn']");
    By moreLanguagesBTN=new By.ByCssSelector("button[jsname='RCbdJd']");
    By readBTN=new By.ByCssSelector("button[jscontroller='xzbRj']");

    public WebElement getInputTextBox(){
        return driver.findElement(inputTextBox);
    }
    public WebElement getReadBTN(){
        WebDriverWait webDriverWait=new WebDriverWait(driver,5);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(readBTN));
        return driver.findElement(readBTN);
    }
    public WebElement getAcceptCookiesButton(){ return driver.findElement(acceptCookiesBTN);}
}
