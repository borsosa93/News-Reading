package andrasborsos.resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Base {

    // to be changed
    //String browserName=System.getProperty("browser");
    String browserName="chrome";

    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private static String formatted = df.format(new Date());

    public String projectRoot=System.getProperty("user.dir");
    private static ArrayList<String> toBeRead=new ArrayList<>();

    private static void initializeToBeRead(){
        toBeRead.add("A mai dátum "+formatted+"");
    }

    public WebDriver initilize(){
        WebDriver driver=null;
        if(browserName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver",projectRoot+"\\src\\main\\java\\andrasborsos\\resources\\chromedriver.exe");
            driver=new ChromeDriver();
        }
        if(browserName.equalsIgnoreCase("firefox")){
            driver=new FirefoxDriver();
        }

        initializeToBeRead();
        return driver;
    }

    public ArrayList<String> getToBeRead() {
        return toBeRead;
    }

    public void addToBeRead(String toBeAdded) {

        toBeRead.add(toBeAdded);
    }

}
