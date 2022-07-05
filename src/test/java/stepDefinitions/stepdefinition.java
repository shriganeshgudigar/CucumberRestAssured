package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;


public class stepdefinition {

    World world;
    public stepdefinition(World world){
        this.world =world;
    }

    @Given("^username \"([^\"]*)\" and password \"([^\"]*)\" and application url$")
    public void method1(String x, String y)throws Throwable{

    }

    @When("^user enters? (?:valid) credentials$")
    public void method2(DataTable dataTable)throws Throwable{
        List<List<String>> dataTableAsList= dataTable.asLists();
        for(List<String> list: dataTableAsList){
           list.stream().forEach(string -> System.out.println(string));
        }
    }

    @Then("^user logs in to the application successfully$")
    public void method3()throws Throwable{

    }

    @Given("^application is up and running$")
    public void verifyApplicationStatus(){
        System.out.println("Application is up and running");
    }

    @When("^the alert is open$")
    public void alertDisplayed(){
        world.js.executeScript("alert('Shriganesh')");
    }
    @Then("^the user accepts the alert$")
    public void verifyalert(){
        world.webDriver.switchTo().alert().accept();
    }
}
