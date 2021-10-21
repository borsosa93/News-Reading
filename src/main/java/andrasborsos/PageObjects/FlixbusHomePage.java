package andrasborsos.PageObjects;

import andrasborsos.resources.InitializeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.ArrayList;

public class FlixbusHomePage extends InitializeDriver {

    public FlixbusHomePage(WebDriver driver)throws IOException {
        this.driver = driver;
    }

    WebDriver driver;
    private int firstActiveDayIndex =-1;
    private int firstActiveDayNumber=-1;
    private int departureDayIndex=-1;
    private int arrivalDayIndex=-1;

    By shadowHostLocator =new By.ByCssSelector("div[id='usercentrics-root']");
    By shadowHostFirstChildLocator =new By.ByCssSelector("div[data-testid='uc-app-container']");
    By acceptCookiesBTNLocator=new By.ByCssSelector("button[data-testid='uc-accept-all-button']");

    By roundtripRBTNLocator= new By.ByCssSelector("label[for='search-mask-trip-mode-roundtrip-toggle']");
    By departureInputBoxLocator=new By.ByCssSelector("div[data-e2e='departure-city-input-field']>div>div>div>input");
    By arrivalInputBoxLocator=new By.ByCssSelector("div[data-e2e='arrival-city-input-field']>div>div>div>input");
    By citiesLocator=new By.ByCssSelector("button._18Kcp");
    By citiesRestOfNameLocator =new By.ByCssSelector("div > div > span > span");

    By departureCalendarLocator =new By.ByCssSelector("input[data-e2e='departure-date-input-field']");
    By arrivalCalendarLocator =new By.ByCssSelector("input[data-e2e='arrival-date-input-field']");
    By calendarTodayLocator = new By.ByCssSelector(".DayPicker-Day--today");
    By calendarFirstActiveDayTextLocator=new By.ByCssSelector("span");
    By calendarNextMonthBTNLocator=new By.ByCssSelector(".flix-icon-arrow-right");
    By calendarMonthsLocator=new By.ByCssSelector(".DayPicker-Months");
    By calendarDay=new By.ByCssSelector(".DayPicker-Day");

    By passengersOptionsLocator =new By.ByCssSelector(".smhc-input.hWzho._3XtvC._1sS9N > input");
    By adultsInputLocator =new By.ByCssSelector("div[data-product-type='adult'] > div.OsIw7 > div > div > input");
    By childrenInputLocator=new By.ByCssSelector("div[data-product-type='children'] > div.OsIw7 > div > div > input");
    By bikesInputLocator=new By.ByCssSelector("div[data-product-type='bike_slot'] > div.OsIw7 > div > div > input");

    By searchBTNLocator=new By.ByCssSelector("button.smhc-btn--primary");

    public WebElement getAcceptCookiesBTN() {
        WebDriverWait webDriverWait=new WebDriverWait(driver,10);
        webDriverWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(shadowHostLocator,0));
        WebElement shadowHost= driver.findElement(shadowHostLocator);
        JavascriptExecutor jsExecutor=(JavascriptExecutor) driver;
        WebElement shadowRoot=(WebElement) jsExecutor.executeScript("return arguments[0].shadowRoot",shadowHost);
        webDriverWait.until(ExpectedConditions.visibilityOf((shadowRoot.findElement(shadowHostFirstChildLocator))));
        return shadowRoot.findElement(acceptCookiesBTNLocator);
   }

    public WebElement getRoundtripRBTN(){
        WebDriverWait webDriverWait=new WebDriverWait(driver,10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(roundtripRBTNLocator));
        return driver.findElement(roundtripRBTNLocator);
    }

    public WebElement getDepartureInputBox(){
        return driver.findElement(departureInputBoxLocator);
    }

    public WebElement getArrivalInputBox(){
        return driver.findElement(arrivalInputBoxLocator);
    }

    public WebElement getCity(){
        WebDriverWait webDriverWait=new WebDriverWait(driver,3);
        webDriverWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(citiesLocator,0));
        ArrayList<WebElement> cities=(ArrayList<WebElement>)driver.findElements(citiesLocator);
        ArrayList<WebElement> spanNodes;
        for (WebElement city : cities) {
            spanNodes = (ArrayList<WebElement>) city.findElements(citiesRestOfNameLocator);
            if (!(spanNodes.size() > 0)) {
                return city;
            }
        }
        return null;
    }

    public WebElement getDepartureCalendar(){
        return driver.findElement(departureCalendarLocator);
    }

    public WebElement getArrivalCalendar(){
        return driver.findElement(arrivalCalendarLocator);
    }

    public WebElement getCalendarToday(){
        return driver.findElement(calendarTodayLocator);
    }

    public WebElement getCalendarNextMonthBTN(){
        return driver.findElement(calendarNextMonthBTNLocator);
    }

    public WebElement getDepartureDay(int departureDay){
        ArrayList<WebElement> allDays=(ArrayList<WebElement>)driver.findElement(calendarMonthsLocator).findElements(calendarDay);
        for (int i = 0; i <allDays.size() ; i++) {
            if(!Boolean.parseBoolean(allDays.get(i).getAttribute("aria-disabled"))){
                firstActiveDayIndex =i;
                firstActiveDayNumber=Integer.parseInt((allDays.get(i).findElement(calendarFirstActiveDayTextLocator).getText()));
                break;
            }
        }
        departureDayIndex= firstActiveDayIndex-firstActiveDayNumber +departureDay;
        return allDays.get(departureDayIndex);
    }

    public WebElement getArrivalDay(int stayLength){
        ArrayList<WebElement> allDays=(ArrayList<WebElement>)driver.findElement(calendarMonthsLocator).findElements(calendarDay);
        if(!Boolean.parseBoolean(allDays.get(departureDayIndex+stayLength).getAttribute("aria-disabled"))){
            arrivalDayIndex=departureDayIndex+stayLength;
        }
        else{
            for (int i = departureDayIndex+stayLength; i <7 ; i++) {
                if(!Boolean.parseBoolean(allDays.get(i).getAttribute("aria-disabled"))){
                    arrivalDayIndex=i;
                    break;
                }
            }
        }
        return allDays.get(arrivalDayIndex);
    }

    public WebElement getPassengersOptions(){
        return driver.findElement(passengersOptionsLocator);
    }

    public WebElement getAdultsInput(){
        return driver.findElement(adultsInputLocator);
    }

    public WebElement getChildrenInput(){
        return driver.findElement(childrenInputLocator);
    }

    public WebElement getbikesInput(){
        return driver.findElement(bikesInputLocator);
    }

    public WebElement getsearchBTN(){
        return driver.findElement(searchBTNLocator);
    }

}
