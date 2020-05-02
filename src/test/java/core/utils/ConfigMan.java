package core.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigMan {


    public static String CONFIG_FILE_PATH_NAME = "src/test/java/config/config.properties";
    public static Properties props = null;

    public ConfigMan() {


        if (props == null) {

            File configFile = new File(CONFIG_FILE_PATH_NAME);

            try {

                FileReader reader = new FileReader(configFile);
                props = new Properties();
                props.load(reader);
                reader.close();

            } catch (FileNotFoundException ex) {

                ex.printStackTrace();

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }


    }


    public static String getProp(String propName) {

            String propValue = props.getProperty(propName);
            return propValue;
    }

//    public static void main(String arg[]){
//
//
//        ConfigMan config = new ConfigMan();
//        System.out.print("Property value is: " + ConfigMan.getProp("TestBaseURL"));
//    }


}
