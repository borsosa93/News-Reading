package andrasborsos;

import andrasborsos.resources.ChooseInitializeDriver;
import org.testng.annotations.BeforeSuite;

public class Initialize extends ChooseInitializeDriver {

    //TODO - this initialization of the date is an initial (pun intended) version
    @BeforeSuite
    public void initialize(){
        initializeToBeRead();
    }


}
