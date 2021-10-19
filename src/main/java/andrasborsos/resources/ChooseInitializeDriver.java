package andrasborsos.resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


public class ChooseInitializeDriver {

    // to be changed
    //String browserName=System.getProperty("browser");
    String browserName="chrome";

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static String formatted = dateFormat.format(new Date());

    private static LocalDate todaysDate =LocalDate.now();

    public String projectRoot=System.getProperty("user.dir");
    private static ArrayList<String> toBeRead=new ArrayList<>();

    protected static void initializeToBeRead(){
        toBeRead.add("A mai dátum "+formatted+".");
    }

    public WebDriver initializeDriver(){
        WebDriver driver=null;
        if(browserName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver",projectRoot+"\\src\\main\\java\\andrasborsos\\resources\\chromedriver.exe");
            driver=new ChromeDriver();
        }
        if(browserName.equalsIgnoreCase("firefox")){
            driver=new FirefoxDriver();
        }

        return driver;
    }

    public ArrayList<String> getToBeRead() {
        return toBeRead;
    }

    public void addToBeRead(String toBeAdded) {
        toBeRead.add(toBeAdded);
    }
    public void addToBeRead(ArrayList<String> toBeAdded) {
        toBeRead.addAll(toBeAdded);
    }

    public static String getFormatted() {
        return formatted;
    }
    public static LocalDate getTodaysDate() {
        return todaysDate;
    }
}
