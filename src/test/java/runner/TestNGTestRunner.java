package runner;


import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


/**
 * use test ng runner to run via cmd
 *  mvn  clean test -Dtest=TestNGTestRunner*
 *  OR
 *
 *  # Linux / OS X:
 * CUCUMBER_FILTER_TAGS="@smoke and @fast" mvn test
 *
 * # Windows:
 * set CUCUMBER_FILTER_TAGS="@smoke and @fast"
 * mvn test
 */

@CucumberOptions(features = "src/test/java/features",
    glue = "stepDefinitions",
        plugin = {"pretty","html:target/cucumber-html-reports/cucumber.html", "json:target/jsonReports/cucumber.json"},
         monochrome = true
)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
    @Before // runs before every scenario
    public void beforeScenario(){
        System.out.println("Test execution started");
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios(){
        return super.scenarios();
    }
}
