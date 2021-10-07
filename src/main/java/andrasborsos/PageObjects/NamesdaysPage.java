package andrasborsos.PageObjects;

import andrasborsos.resources.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NamesdaysPage extends Base {

    public NamesdaysPage() {
        this.driver = initilize();
        driver.get("https://mainevnap.hu/");
    }
    public WebDriver driver;

    By namesdaysTitle=new By.ByCssSelector("tr:nth-child(2) > td:nth-child(2)");
    By namesday=new By.ByCssSelector("span[class='piroskiem']");

    public List<WebElement> getNamesdays(){
        return driver.findElement(namesdaysTitle).findElements(namesday);
    }
}