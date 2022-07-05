package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import stepDefinitions.World;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Util {

    public Util(World world) throws IOException {
        this.world  =  world;
    }

    private World world;


    public RequestSpecification requestSpecification() throws IOException {
        PrintStream ps = new PrintStream(new FileOutputStream("logging.txt"));
        RequestSpecification requestSpecification =  new RequestSpecBuilder().setBaseUri(world.propertiesCache.getProperty("uri")).addQueryParams(new HashMap<String,String>()).
                addHeaders(new HashMap<String,String>()).
                addQueryParam("key","qaclick123").setContentType(ContentType.JSON).addFilter(RequestLoggingFilter.logRequestTo(ps)).
                addFilter(ResponseLoggingFilter.logResponseTo(ps)).build();
        return requestSpecification;
    }

    public ResponseSpecification resonseSpecification(){
        ResponseSpecification responseSpecification =  new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        return responseSpecification;
    }




}
