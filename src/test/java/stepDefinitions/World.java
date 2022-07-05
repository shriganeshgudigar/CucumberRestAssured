package stepDefinitions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import resources.PropertiesCache;
import resources.TestDataProvider;
import resources.Util;

public class World {

    public  PropertiesCache propertiesCache;
    public  Util util;
    public TestDataProvider testDataProvider;
    public WebDriver webDriver;
    public JavascriptExecutor js;
}
