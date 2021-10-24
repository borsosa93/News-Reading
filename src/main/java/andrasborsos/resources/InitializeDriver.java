package andrasborsos.resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static andrasborsos.resources.Utilities.getProperty;

public class InitializeDriver {


    String browserName=System.getProperty("browser");
    private final String projectRoot=System.getProperty("user.dir");

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
        if(browserName.equalsIgnoreCase("edge")){
            System.setProperty("webdriver.edge.driver",projectRoot+"\\src\\main\\java\\andrasborsos\\resources\\msedgedriver.exe");
            driver=new EdgeDriver();
        }

        return driver;
    }

}
