package andrasborsos;

import andrasborsos.PageObjects.FlixbusHomePage;
import andrasborsos.PageObjects.FlixbusSearchResultsPage;
import andrasborsos.resources.ChooseInitializeDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class FlixbusTest extends ChooseInitializeDriver {

    WebDriver driver;
    ArrayList<String> ticketsPrices = new ArrayList<>();
    private String departure = "Krakkó";
    private String arrival = "Budapest";
    private String passengersAdults="2";
    private String passengersChildren="0";
    private String passengersBikes="0";

    @BeforeTest
    public void initialize() {
        this.driver = initializeDriver();
        driver.get("https://www.flixbus.hu/");
    }

    @Test
    public void busTicketPrices() {
        FlixbusHomePage flixbusHomePage = new FlixbusHomePage(driver);
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
        flixbusHomePage.getAdultsInput().sendKeys(passengersAdults);
        flixbusHomePage.getChildrenInput().sendKeys(passengersChildren);
        flixbusHomePage.getbikesInput().sendKeys(passengersBikes);

        flixbusHomePage.getsearchBTN().click();

        FlixbusSearchResultsPage flixbusSearchResultsPage = new FlixbusSearchResultsPage(driver);
        ticketsPrices.add("Jegyek a Flixbus "+departure+" - "+arrival+ " járatára "+ getTodaysDate().plusMonths(2)+"-án "+passengersAdults+ " felnőtt és "+passengersChildren+" gyerek részére "+passengersBikes+" kerékpárral:");
        //parseSearchResults(flixbusSearchResultsPage.getDepartureTimes(), flixbusSearchResultsPage.getPrices(), flixbusSearchResultsPage.getreservationErrorMessages());
        parseSearchResults(flixbusSearchResultsPage.getResults());
    }

    private int departureDay() {
        LocalDate departureDate= getTodaysDate().plusMonths(2);
        int departureDayNumber = Integer.parseInt(departureDate.toString().split("-")[2]);
        return departureDayNumber;
    }

    private void parseSearchResults(ArrayList<ArrayList<WebElement>> resultsOrganized) {

        ArrayList<WebElement> departureTimes = resultsOrganized.get(0);
        ArrayList<WebElement> prices = resultsOrganized.get(1);
        ArrayList<WebElement> errorMessages= resultsOrganized.get(2);

        ArrayList<String> departureTimesText=new ArrayList<>();

        //If there are departure times at all, then
        if (!departureTimes.stream().allMatch(webElement -> webElement.equals(null))) {
            //Find the indices of unique departure times
            departureTimes.stream().map(WebElement::getText).forEach(departureTimesText::add);
            ArrayList<String> departureTimesUnique = (ArrayList<String>) departureTimesText.stream().distinct().collect(Collectors.toList());
            ArrayList<Integer> uniqueIndeces = new ArrayList<>();
            for (int i = 0; i < departureTimesUnique.size(); i++) {
                uniqueIndeces.add(departureTimesText.indexOf(departureTimesUnique.get(i)));
            }
            //If the price belonging to the result defined by the time is not null, add them to be read
            String ticketOption=null;
            for (Integer uniqueIndex : uniqueIndeces) {
                if(!(prices.get(uniqueIndex).getText().equalsIgnoreCase(""))){
                    ticketOption = departureTimesText.get(uniqueIndex) + " időpontban ";
                    ticketOption += prices.get(uniqueIndex).getText() + " áron";
                    ticketsPrices.add(ticketOption);
                }
                else if(!(errorMessages.get(uniqueIndex).getText().equalsIgnoreCase(""))){
                    ticketOption = departureTimesText.get(uniqueIndex) + " időponttal a következő probléma van ";
                    ticketOption+=errorMessages.get(uniqueIndex).getText();
                    ticketsPrices.add(ticketOption);
                }
            }
        }
        else {
            ticketsPrices.add("A Flixbusnál nincsenek jegyek az útvonalon a kiválasztott paraméterekkel vagy hiba történt. Az oldalról képernyőfotót készítettem a projekt gyökérmappájába.");
        }
    }


    private void parseSearchResults(ArrayList<WebElement> departureTimesWeb, ArrayList<WebElement> pricesWeb, ArrayList<WebElement> errorMessagesWeb) {

        ArrayList<String> departureTimesText = new ArrayList<>();
        //ArrayList<String> pricesTextUnique = new ArrayList<>();
        ArrayList<String> errorMessagesTextUnique = new ArrayList<>();

        if ((departureTimesWeb.size() > 0) && (pricesWeb.size()==departureTimesWeb.size())) {
            //Finding the indices of unique departure times
            departureTimesWeb.stream().map(WebElement::getText).forEach(departureTimesText::add);
            ArrayList<String> departureTimesUnique = (ArrayList<String>) departureTimesText.stream().distinct().collect(Collectors.toList());
            ArrayList<Integer> uniqueIndeces = new ArrayList<>();
            for (int i = 0; i < departureTimesUnique.size(); i++) {
                uniqueIndeces.add(departureTimesText.indexOf(departureTimesUnique.get(i)));
            }
            //Adding the unique elements to the arraylist to be read
            String ticketOption;
            for (Integer uniqueIndex : uniqueIndeces) {
                ticketOption = departureTimesText.get(uniqueIndex) + " időpontban ";
                ticketOption += pricesWeb.get(uniqueIndex).getText() + " áron";
                ticketsPrices.add(ticketOption);
            }
            System.out.println(ticketsPrices);
            }

        if (!(pricesWeb.size() > 0)){
            ticketsPrices.add("A Flixbusnál nincsenek jegyek az útvonalon a kiválasztott paraméterekkel.");
        }

        if (errorMessagesWeb.size() > 0) {
            ticketsPrices.add("A következő hiba a találatokban:");
            for (int i = 0; i < errorMessagesWeb.size(); i++) {
                ticketsPrices.add(errorMessagesWeb.get(i).getText());
            }
            ticketsPrices.add("A hibáról képernyőfotót készítettem a projekt gyökérmappájába.");
            System.out.println(ticketsPrices);
        }

    }
}
