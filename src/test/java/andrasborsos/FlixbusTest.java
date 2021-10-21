package andrasborsos;

import andrasborsos.PageObjects.FlixbusHomePage;
import andrasborsos.PageObjects.FlixbusSearchResultsPage;
import andrasborsos.resources.InitializeDriver;

import andrasborsos.resources.Utilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static andrasborsos.resources.Utilities.getProperty;

public class FlixbusTest extends InitializeDriver {

    WebDriver driver;
    ArrayList<String> ticketsPrices = new ArrayList<>();

    @BeforeTest
    public void initialize() throws IOException {
        this.driver = initializeDriver();
        driver.get(getProperty("flixbusURL"));
    }

    @Test
    public void busTicketPrices() throws IOException {
        FlixbusHomePage flixbusHomePage = new FlixbusHomePage(driver);
        flixbusHomePage.getAcceptCookiesBTN().click();
        flixbusHomePage.getRoundtripRBTN().click();

        flixbusHomePage.getDepartureInputBox().click();
        flixbusHomePage.getDepartureInputBox().sendKeys(getProperty("departure"));
        flixbusHomePage.getCity().click();

        flixbusHomePage.getArrivalInputBox().click();
        flixbusHomePage.getArrivalInputBox().sendKeys(getProperty("arrival"));
        flixbusHomePage.getCity().click();

        //Make sure the calendar starts at today
        flixbusHomePage.getDepartureCalendar().click();
        flixbusHomePage.getCalendarToday().click();
        flixbusHomePage.getDepartureCalendar().click();
        //Pick today's number day some months from now (specified as property)
        for(int i=1;i<=Integer.parseInt(getProperty("monthOffset"));i++){
            flixbusHomePage.getCalendarNextMonthBTN().click();
        }
        flixbusHomePage.getDepartureDay(departureDay()).click();
        //Pick return date a week from then
        flixbusHomePage.getArrivalCalendar().click();
        flixbusHomePage.getArrivalDay().click();

        flixbusHomePage.getPassengersOptions().click();
        flixbusHomePage.getAdultsInput().sendKeys(Keys.BACK_SPACE);
        flixbusHomePage.getAdultsInput().sendKeys(getProperty("adults"));
        flixbusHomePage.getChildrenInput().sendKeys(getProperty("children"));
        flixbusHomePage.getbikesInput().sendKeys(getProperty("bikes"));

        flixbusHomePage.getsearchBTN().click();

        FlixbusSearchResultsPage flixbusSearchResultsPage = new FlixbusSearchResultsPage(driver);
        ticketsPrices.add("Jegyek a Flixbus "+getProperty("departure")+" - "+getProperty("arrival")+ " járatára "+ Utilities.getTodaysDate().plusMonths(Integer.parseInt(getProperty("monthOffset")))+" napon "+getProperty("adults")+ " felnőtt és "+getProperty("children")+" gyerek részére "+getProperty("bikes")+" kerékpárral:");
        noTicketsMessage(flixbusSearchResultsPage.getNoTicketsMessage());
        parseSearchResults(flixbusSearchResultsPage.getResults());

        flixbusSearchResultsPage.getreserveBTN().click();
        flixbusSearchResultsPage.waitToLeaveResultsPage();
        ticketsPrices.add("Jegyek a visszaútra "+ Utilities.getTodaysDate().plusMonths(Integer.parseInt(getProperty("monthOffset"))).plusDays(Integer.parseInt(getProperty("stayLengthDays")))+" napon:");
        noTicketsMessage(flixbusSearchResultsPage.getNoTicketsMessage());
        parseSearchResults(flixbusSearchResultsPage.getResults());
        flixbusSearchResultsPage.getdeleteReservationBTN().click();
    }

    @AfterTest
    public void postproc(){
        driver.close();
        Utilities.addToBeRead(ticketsPrices);
    }

    private int departureDay() throws IOException {
        LocalDate departureDate= Utilities.getTodaysDate().plusMonths(Integer.parseInt(getProperty("monthOffset")));
        return Integer.parseInt(departureDate.toString().split("-")[2]);
    }
    private void noTicketsMessage(WebElement message){
        if(!(message==null)){
            ticketsPrices.add(("Hibaüzenet az oldalon: "+message.getText()));
            Assert.fail();
        }
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
                for (String s : departureTimesUnique) {
                    uniqueIndeces.add(departureTimesText.indexOf(s));
                }
                //If the price belonging to the result defined by the time is not null, add them to be read
                String ticketOption;
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
        ArrayList<ArrayList<WebElement>> transposedMatrix = new ArrayList<>();
        if (!matrixIn.isEmpty()) {
            int noOfElementsInInnerList = matrixIn.get(0).size();
            for (int i = 0; i < noOfElementsInInnerList; i++) {
                ArrayList<WebElement> col = new ArrayList<>();
                for (ArrayList<WebElement> row : matrixIn) {
                    col.add(row.get(i));
                }
                transposedMatrix.add(col);
            }
        }
        return transposedMatrix;
    }
}
