package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesCache {
    private static Properties properties;
    private static PropertiesCache propertiesCache;

    public static PropertiesCache getInstance() throws IOException {
        if(propertiesCache==null){
            propertiesCache = new PropertiesCache();
            readPropertyFile();
        }
        return propertiesCache;
    }

    private PropertiesCache(){
    }

    private static void readPropertyFile() throws IOException {
        properties = new Properties();
        FileInputStream fis =  new FileInputStream("src/test/java/resources/global.properties");
        properties.load(fis);
    }
    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
