package andrasborsos;

import andrasborsos.PageObjects.FlixbusHomePage;
import andrasborsos.PageObjects.FlixbusSearchResultsPage;
import andrasborsos.resources.ChooseInitializeDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class FlixbusTest extends ChooseInitializeDriver {

    WebDriver driver;
    ArrayList<String> ticketsPrices=new ArrayList<>();
    private String departure="Krakkó";
    private String arrival="Budapest";

    @BeforeTest
    public void initialize() {
        this.driver = initializeDriver();
        driver.get("https://www.flixbus.hu/");
    }

    @Test
    public void busTicketPrices() {
        FlixbusHomePage flixbusHomePage=new FlixbusHomePage(driver);
        flixbusHomePage.getAcceptCookiesBTN().click();
        flixbusHomePage.getRoundtripRBTN().click();

        flixbusHomePage.getDepartureInputBox().click();
        flixbusHomePage.getDepartureInputBox().sendKeys(departure);
        flixbusHomePage.getCity().click();

        flixbusHomePage.getArrivalInputBox().click();
        flixbusHomePage.getArrivalInputBox().sendKeys(arrival);
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
        //flixbusHomePage.getbikesInput().sendKeys("2");

        flixbusHomePage.getsearchBTN().click();

        FlixbusSearchResultsPage flixbusSearchResultsPage=new FlixbusSearchResultsPage(driver);
        parseSearchResults(flixbusSearchResultsPage.getDepartureTimes(), flixbusSearchResultsPage.getPrices(), flixbusSearchResultsPage.getreservationErrorMessages());

    }

    private int departureDay(){
        int todayNumber=Integer.parseInt(getFormatted().split("-")[2]);
        if(todayNumber>28){
            todayNumber=28;
        }
        return todayNumber;
    }
    private void parseSearchResults(ArrayList<WebElement> departureTimes, ArrayList<WebElement> prices,ArrayList<WebElement> errorMessages){
        ArrayList<String> departureTimesUnique=new ArrayList<>();
        ArrayList<String> pricesText=new ArrayList<>();
        ArrayList<String> errorMessagesText=new ArrayList<>();

        if(errorMessages.size()>0){
            ticketsPrices.add("A következő hiba a találatokban:");
            for (int i = 0; i < errorMessages.size(); i++) {
                ticketsPrices.add(errorMessages.get(i).getText());
            }
            ticketsPrices.add("A hibáról képernyőfotót készítettem a projekt gyökérmappájába.");
            System.out.println(ticketsPrices);
            return;
        }

        if(departureTimes.size()>0){
            for (int i = 0; i < departureTimes.size(); i++) {
                (departureTimes.get(i).getText());
                if(prices.size()>0){
                    pricesText.add(prices.get(i).getText());
                }
                if(errorMessages.size()>0){
                    errorMessagesText.add(errorMessages.get(i).getText());
                }
            }
        }
        else{
            ticketsPrices.add("A Flixbusnál nincsenek jegyek "+departure+" és "+arrival+" között a kiválasztott napokon.");
        }


        //ArrayList<String> departureTimesUnique=(ArrayList<String>)departureTimesText.stream().distinct().collect(Collectors.toList());
        //ArrayList<String> pricesUnique=(ArrayList<String>)pricesText.stream().distinct().collect(Collectors.toList());


    }
}
