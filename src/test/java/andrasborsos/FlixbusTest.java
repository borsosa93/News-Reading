package andrasborsos;

import andrasborsos.PageObjects.FlixbusHomePage;
import andrasborsos.resources.ChooseInitializeDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class FlixbusTest extends ChooseInitializeDriver {

    WebDriver driver;

    @BeforeTest
    public void initialize() {
        this.driver = initializeDriver();
        driver.get("https://www.flixbus.hu/");
    }

    @Test
    public void busTicketPrices() throws InterruptedException {
        FlixbusHomePage flixbusHomePage=new FlixbusHomePage(driver);
        //flixbusHomePage.getAcceptCookiesBTN().click();
        Thread.sleep(5000);
        flixbusHomePage.getRoundtripRBTN().click();

        flixbusHomePage.getDepartureInputBox().click();
        flixbusHomePage.getDepartureInputBox().sendKeys("krakkó");
        flixbusHomePage.getCity().click();

        flixbusHomePage.getArrivalInputBox().click();
        flixbusHomePage.getArrivalInputBox().sendKeys("budapest");
        flixbusHomePage.getCity().click();

        //Make sure the calendar starts at today
        flixbusHomePage.getDepartureCalendar().click();
        flixbusHomePage.getCalendarToday().click();
        flixbusHomePage.getDepartureCalendar().click();
        //Pick today's number day two months from now if today isn't later than 28th, else pick 28th
        flixbusHomePage.getCalendarNextMonthBTN().click();
        flixbusHomePage.getCalendarNextMonthBTN().click();
        flixbusHomePage.getDepartureDay(departureDay()).click();
        //Pick return date a week from then
        flixbusHomePage.getArrivalCalendar().click();
        flixbusHomePage.getArrivalDay().click();

        flixbusHomePage.getPassengersOptions().click();
        flixbusHomePage.getAdultsInput().sendKeys(Keys.BACK_SPACE);
        flixbusHomePage.getAdultsInput().sendKeys("2");
        flixbusHomePage.getbikesInput().sendKeys("2");

        flixbusHomePage.getsearchBTN().click();

    }

    private int departureDay(){
        int todayNumber=Integer.parseInt(getFormatted().split("-")[2]);
        if(todayNumber>28){
            todayNumber=28;
        }
        return todayNumber;
    }
}
