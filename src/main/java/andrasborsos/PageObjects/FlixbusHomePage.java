package andrasborsos.PageObjects;

import andrasborsos.resources.ChooseInitializeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class FlixbusHomePage extends ChooseInitializeDriver {

    public FlixbusHomePage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    By acceptCookiesBTNLocator =new By.ByCssSelector("button[data-testid='uc-accept-all-button']");
    By roundtripRBTNLocator= new By.ByCssSelector("input[id='search-mask-trip-mode-roundtrip-toggle']");
    By departureInputBoxLocator=new By.ByCssSelector("div[data-e2e='departure-city-input-field']>div>div>div>input");
    By arrivalInputBoxLocator=new By.ByCssSelector("div[data-e2e='arrival-city-input-field']>div>div>div>input");
    By citiesLocator=new By.ByCssSelector("ul[class='_1nfrX']>li>button");
    By getCitiesRestOfNameLocator =new By.ByCssSelector("div > div._27QGb > span > span");

    By getDepartureCalendarLocator=new By.ByCssSelector("input[data-e2e='departure-date-input-field']");
    By getCalendarMonthYear= new By.ByCssSelector(".DayPicker-Caption");

    public WebElement getacceptCookiesBTN(){
        WebDriverWait webDriverWait=new WebDriverWait(driver,5);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(acceptCookiesBTNLocator));
        return driver.findElement(acceptCookiesBTNLocator);
    }

    public WebElement getroundtripRBTN(){
        WebDriverWait webDriverWait=new WebDriverWait(driver,3);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(roundtripRBTNLocator));
        return driver.findElement(roundtripRBTNLocator);
    }

    public WebElement getdepartureInputBox(){
        return driver.findElement(departureInputBoxLocator);
    }

    public WebElement getarrivalInputBox(){
        return driver.findElement(arrivalInputBoxLocator);
    }

    public WebElement getCity(){
        ArrayList<WebElement> departureCities=(ArrayList<WebElement>)driver.findElements(citiesLocator);
        for (WebElement departureCity : departureCities) {
            List<WebElement> spanNodes = departureCity.findElements(getCitiesRestOfNameLocator);
            if (!(spanNodes.size() > 0)) {
                return departureCity;
            }
        }
        return null;
    }
}
