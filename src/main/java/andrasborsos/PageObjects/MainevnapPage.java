package andrasborsos.PageObjects;

import andrasborsos.resources.InitializeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainevnapPage extends InitializeDriver {

    public MainevnapPage(WebDriver driver) {
        this.driver = driver;
    }
    public WebDriver driver;

    By namesdaysTitleLocator=new By.ByCssSelector("tr:nth-child(2) > td:nth-child(2)");
    By namesdayLocator=new By.ByCssSelector("span[class='piroskiem']");

    public List<WebElement> getNamesdays(){
        return driver.findElement(namesdaysTitleLocator).findElements(namesdayLocator);
    }
}