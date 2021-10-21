package andrasborsos;

import andrasborsos.PageObjects.FlixbusHomePage;
import andrasborsos.PageObjects.FlixbusSearchResultsPage;
import andrasborsos.resources.ChooseInitializeDriver;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class FlixbusTest extends ChooseInitializeDriver {

    WebDriver driver;
    ArrayList<String> ticketsPrices = new ArrayList<>();
    private String departure = "Budapest";
    private String arrival = "Moss";
    private String passengersAdults="2";
    private String passengersChildren="0";
    private String passengersBikes="0";
    private int monthOffset=0;
    private int stayLengthDays=7;
    private boolean noTicketsFlag=true;

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
        //Pick today's number day two months from now
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
        ticketsPrices.add("Jegyek a Flixbus "+departure+" - "+arrival+ " járatára "+ getTodaysDate().plusMonths(monthOffset)+" napon "+passengersAdults+ " felnőtt és "+passengersChildren+" gyerek részére "+passengersBikes+" kerékpárral:");
        noTicketsMessage(flixbusSearchResultsPage.getNoTicketsMessage());
        parseSearchResults(flixbusSearchResultsPage.getResults());

        //if(noTicketsFlag==false){
            flixbusSearchResultsPage.getreserveBTN().click();
            flixbusSearchResultsPage.waitToLeaveResultsPage();
            ticketsPrices.add("Jegyek a visszaútra "+getTodaysDate().plusMonths(monthOffset).plusDays(stayLengthDays)+" napon:");
            noTicketsMessage(flixbusSearchResultsPage.getNoTicketsMessage());
            parseSearchResults(flixbusSearchResultsPage.getResults());
            flixbusSearchResultsPage.getdeleteReservationBTN().click();
        //}

        System.out.println(ticketsPrices);
    }

    @AfterTest
    public void postproc(){
        driver.close();
        addToBeRead(ticketsPrices);
    }

    private int departureDay() {
        LocalDate departureDate= getTodaysDate().plusMonths(monthOffset);
        int departureDayNumber = Integer.parseInt(departureDate.toString().split("-")[2]);
        return departureDayNumber;
    }
    private void noTicketsMessage(WebElement message){
        if(!(message==null)){
            ticketsPrices.add(("Hibaüzenet az oldalon: "+message.getText()));
            Assert.fail();
        }
        else noTicketsFlag=false;
    }
    private void parseSearchResults(ArrayList<ArrayList<WebElement>> resultsOrganized) {
        //if(!noTicketsFlag&&!(resultsOrganized.isEmpty())){
            resultsOrganized = transpose(resultsOrganized);

            ArrayList<WebElement> departureTimes = resultsOrganized.get(0);
            ArrayList<WebElement> prices = resultsOrganized.get(1);
            ArrayList<WebElement> errorMessages = resultsOrganized.get(2);

            ArrayList<String> departureTimesText = new ArrayList<>();
            //If there are departure times, then
            if (!(departureTimes.stream().allMatch(webElement -> webElement == null))) {
                //Find the indices of unique departure times
                departureTimes.stream().map(WebElement::getText).forEach(departureTimesText::add);
                ArrayList<String> departureTimesUnique = (ArrayList<String>) departureTimesText.stream().distinct().collect(Collectors.toList());
                ArrayList<Integer> uniqueIndeces = new ArrayList<>();
                for (int i = 0; i < departureTimesUnique.size(); i++) {
                    uniqueIndeces.add(departureTimesText.indexOf(departureTimesUnique.get(i)));
                }
                //If the price belonging to the result defined by the time is not null, add them to be read
                String ticketOption = null;
                int faultyResultCounter = 0;
                for (Integer uniqueIndex : uniqueIndeces) {
                    if ((!(prices.get(uniqueIndex) == null)) && (!(prices.get(uniqueIndex).getText() == null))) {
                        ticketOption = departureTimesText.get(uniqueIndex) + " időpontban ";
                        ticketOption += prices.get(uniqueIndex).getText() + " áron.";
                        ticketsPrices.add(ticketOption);
                    } else if ((!(errorMessages.get(uniqueIndex) == null)) && (!(errorMessages.get(uniqueIndex).getText() == null))) {
                        ticketOption = departureTimesText.get(uniqueIndex) + " időponttal a következő probléma van ";
                        ticketOption += errorMessages.get(uniqueIndex).getText();
                        ticketsPrices.add(ticketOption);
                        faultyResultCounter++;
                    }
                    Assert.assertNotEquals(uniqueIndeces.size(), faultyResultCounter);
                }
            }
            else{
                ticketsPrices.add("Hiba történt. Az oldalról képernyőfotót készítettem a projekt gyökérmappájába.");
                Assert.fail();
            }
        //}
    }

    private ArrayList<ArrayList<WebElement>> transpose(ArrayList<ArrayList<WebElement>> matrixIn) {
        ArrayList<ArrayList<WebElement>> transposedMatrix = new ArrayList<ArrayList<WebElement>>();
        if (!matrixIn.isEmpty()) {
            int noOfElementsInInnerList = matrixIn.get(0).size();
            for (int i = 0; i < noOfElementsInInnerList; i++) {
                ArrayList<WebElement> col = new ArrayList<WebElement>();
                for (ArrayList<WebElement> row : matrixIn) {
                    col.add(row.get(i));
                }
                transposedMatrix.add(col);
            }
        }
        return transposedMatrix;
    }
}
