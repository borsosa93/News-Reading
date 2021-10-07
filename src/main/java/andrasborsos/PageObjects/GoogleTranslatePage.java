package andrasborsos.PageObjects;

import andrasborsos.resources.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleTranslatePage extends Base {

    WebDriver driver= initilize();

    By inputTextBox=new By.ByCssSelector("textarea[class='er8xn']");
    By moreLanguagesBTN=new By.ByCssSelector("button[jsname='RCbdJd']");
    By readBTN=new By.ByCssSelector("button[jscontroller='xzbRj']");

    public WebElement getInputTextBox(){
        return driver.findElement(inputTextBox);
    }
    public WebElement getReadBTN(){
        return driver.findElement(readBTN);
    }
}
