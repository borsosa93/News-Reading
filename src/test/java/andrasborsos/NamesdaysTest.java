package andrasborsos;

import andrasborsos.PageObjects.NamesdaysPage;
import andrasborsos.resources.StringParser;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;

public class NamesdaysTest implements StringParser {

   private String namesdays="Ma ";


    @Test
    public void TodaysNamesdays(){
        NamesdaysPage namesdaysPage=new NamesdaysPage();
        List<WebElement> namesdaysList=namesdaysPage.getNamesdays();
        Iterator<WebElement> iterator= namesdaysList.listIterator();

        while (iterator.hasNext()){
            String textToAdd=iterator.next().getText();
            if(iterator.hasNext()){
                namesdays+=textToAdd+", ";
            }
            else{
                namesdays+=textToAdd+" ";
            }

        }

        editStringToRead();
        namesdaysPage.addToBeRead(namesdays);

        System.out.println(namesdaysPage.getToBeRead());
    }

    @Override
    public void editStringToRead() {
        namesdays+="névnapja van";
    }

}
