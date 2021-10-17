package andrasborsos;

import andrasborsos.resources.ChooseInitializeDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FlixbusTest extends ChooseInitializeDriver {

    WebDriver driver;
    private SimpleDateFormat dateFormat=new SimpleDateFormat("MM-yyyy");
    String formatted = dateFormat.format(new Date());

    @BeforeTest
    public void initialize() {
        //this.driver = initializeDriver();
        //driver.get("https://www.flixbus.hu/");
    }

    @Test
    public void busTicketPrices(){
        System.out.println(formatted);
    }
}
