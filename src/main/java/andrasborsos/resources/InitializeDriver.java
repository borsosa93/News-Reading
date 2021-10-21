package andrasborsos.resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

import static andrasborsos.resources.Utilities.getProperty;

public class InitializeDriver {

    // to be changed
    //String browserName=System.getProperty("browser");
    String browserName=getProperty("browserName");
    //String browserName="chrome";
    private String projectRoot=System.getProperty("user.dir");

    public InitializeDriver() throws IOException {
    }

    public WebDriver initializeDriver(){
        WebDriver driver=null;
        if(browserName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver",projectRoot+"\\src\\main\\java\\andrasborsos\\resources\\chromedriver.exe");
            driver=new ChromeDriver();
        }
        if(browserName.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver",projectRoot+"\\src\\main\\java\\andrasborsos\\resources\\geckodriver.exe");
            driver=new FirefoxDriver();
        }

        return driver;
    }

}
