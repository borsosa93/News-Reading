package andrasborsos;

import andrasborsos.resources.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;


import java.io.IOException;

import static andrasborsos.resources.Utilities.addToBeRead;
import static andrasborsos.resources.Utilities.getScreenShotPath;

public class Listeners implements ITestListener {


    @Override
    public void onTestFailure(ITestResult result) {
        //Get the driver field from the failed test's instance! So it's thread safe
        WebDriver driver = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        //take the actual screenshot here and note the screenshot in the toBeRead object
        try {
            String methodName=result.getMethod().getMethodName();
            getScreenShotPath(methodName, driver);
            addToBeRead(methodName+": a teszt sikertelen. A hibáról képernyőfotót készítettem a projekt reports mappájába.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

