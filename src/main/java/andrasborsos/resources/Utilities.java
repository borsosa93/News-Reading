package andrasborsos.resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

public class Utilities {

    static ArrayList<String> toBeRead=new ArrayList<>();
    private static LocalDate todaysDate =LocalDate.now();
    private static String projectRoot=System.getProperty("user.dir");
    private static Properties properties=new Properties();


    public static LocalDate getTodaysDate() {
        return todaysDate;
    }
    protected static void initializeToBeRead(){
        toBeRead.add("A mai dátum "+ todaysDate+".");
    }
    public static ArrayList<String> getToBeRead() {
        return toBeRead;
    }
    public static void addToBeRead(String toBeAdded) {
        toBeRead.add(toBeAdded);
    }
    public static void addToBeRead(ArrayList<String> toBeAdded) {
        toBeRead.addAll(toBeAdded);
    }
    public static String getProperty(String key) throws IOException {
        FileInputStream fileInputStream=new FileInputStream(projectRoot+"\\src\\data.properties");
        properties.load(fileInputStream);
        return properties.getProperty(key);
    }
    public static void setProperty(String key, String value) throws IOException {
        Properties properties=new Properties();
        FileInputStream fileInputStream=new FileInputStream(projectRoot+"\\src\\data.properties");
        properties.load(fileInputStream);
        properties.setProperty(key, value);
        FileOutputStream fileOutputStream=new FileOutputStream(projectRoot+"\\src\\data.properties");
        properties.store(fileOutputStream,null);
    }

}
