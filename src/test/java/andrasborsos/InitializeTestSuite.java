package andrasborsos;

import andrasborsos.resources.Utilities;
import org.testng.annotations.BeforeSuite;

public class InitializeTestSuite extends Utilities {

    @BeforeSuite
    public void initialize(){
        initializeToBeRead();
    }


}
