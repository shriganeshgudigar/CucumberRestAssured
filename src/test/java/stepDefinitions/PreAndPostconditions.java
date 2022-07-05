package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class PreAndPostconditions {

    World world;
    public PreAndPostconditions(World world){
        this.world = world;
    }

    @Before("@UITest")
    public void launchBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Divya Shriganesh\\softwares\\chromedriver_102\\chromedriver.exe");
        world.webDriver = new ChromeDriver();
        world.js = (JavascriptExecutor)world.webDriver;
        world.webDriver.get("https://www.amazon.com/");
        world.webDriver.manage().window().maximize();
        world.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @After("@UITest")
    public void afterhooks(){
        world.webDriver.close();
    }
}
