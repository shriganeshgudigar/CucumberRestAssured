package stepDefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import pojo.place.AddPlace;
import pojo.place.AddPlaceResponse;
import resources.PropertiesCache;
import resources.TestDataProvider;
import resources.Util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class addPlaceSteps {
    RequestSpecification req;
    Response res;

    World world;

    public addPlaceSteps(World common){
        world =  common;
    }

    /**
     * Deserialisation - reading a json file - byte stream - java object
     * read json place as java object and use it in post request
     * Steps: Json file
     * 1. create pojo classes for JSON provided
     * 2. Use Object Mapper - jackson databind, mapper.readValue
     * 3. Instance is returned
     *
     *
     * @throws IOException
     */
    public void readJsonFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        AddPlace addPlace = objectMapper.readValue(new File("addPlace.json"), AddPlace.class);
        objectMapper.writeValueAsString(addPlace);
    }

    /**
     *
     * Serialisation - java object to byte stream to file
     * write java object to a file - use mapper.writevalue
     * @throws IOException
     */
    public void writeToJsonFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        AddPlace addPlace = TestDataProvider.getInstance().getJavaObject();
        objectMapper.writeValue(new File("file.json"), addPlace);
    }

    @Given("^user enters all valid request details$")
    public void user_enters_all_valid_request_details() throws IOException {;
        world.testDataProvider =  TestDataProvider.getInstance();
        world.util = new Util(world);
        world.propertiesCache = PropertiesCache.getInstance();
        req = RestAssured.given().spec(world.util.requestSpecification()).body(world.testDataProvider.getJavaObject());
    }

    @When("user submits add place api request")
    public void user_submits_add_place_api_request() {
        res = req.when().post("maps/api/place/add/json").then().spec(world.util.resonseSpecification()).extract().response();
        // Write code here that turns the phrase above into concrete actions

    }



    public void fileAttach(){
        req.multiPart(new File("")).contentType(ContentType.MULTIPART);
    }

    public void validateSchema(){
        res.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("path to schema.json file"));
    }

    public void deleteAResource(String resource){
        res = req.delete("/delete/"+resource);
        res.then().assertThat().statusCode(204);
    }

    public void responseTime(){

        res.getTime();
    }


    AddPlaceResponse addPlaceResponse;
    @Then("^response with status code \"([^\"]*)\" is recieved$")
    public void response_with_status_code_is_recieved(String statusCode) throws JsonProcessingException {
        // Write code here that turns the phrase above into concrete actions
        ObjectMapper mapper = new ObjectMapper();
        addPlaceResponse = mapper.readValue(res.asString(), AddPlaceResponse.class);

        Assert.assertTrue("Status code not matching",statusCode.matches(String.valueOf(res.getStatusCode())));

    }
    @Then("^response with status is \"([^\"]*)\" recieved$")
    public void response_with_status_is_ok_recieved(String status) {
       Assert.assertTrue("Status Actual and expeceted not matching",status.matches(addPlaceResponse.getStatus()));
    }

}

