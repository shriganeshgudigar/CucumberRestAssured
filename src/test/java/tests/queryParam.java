package tests;

import static io.restassured.RestAssured.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class queryParam {

    @Test
    public void queryParam(){
         RequestSpecification rq= given().log().all().spec(getSpec()).queryParam("key", "Shriganesh");
         String responseString=rq.get("/get").then().log().all().assertThat().extract().response().asString();

        JsonPath jsonPath=new JsonPath(responseString);
        System.out.println("queryParam:" +jsonPath.getString("args.key"));
    }

    @Test
    public void extractHeaders(){
        RequestSpecification rq= given().log().all().spec(getSpec()).queryParam("key", "Shriganesh");
        String responseString=rq.get("/get").then().log().all().assertThat().extract().headers().toString();

        System.out.println("headers alome:" +responseString);
    }

    @Test
    public void blackListHeaders(){
        RequestSpecification rq= given().config(RestAssured.config().logConfig(LogConfig.logConfig().blacklistHeader("Accept","name"))).log().all().spec(getSpec()).queryParam("key", "Shriganesh").header("name","shriganesh");
        String responseString=rq.get("/get").then().log().all().assertThat().extract().headers().toString();

        System.out.println("headers alome:" +responseString);
    }


    @Test
    public void FormData(){
        RequestSpecification rq= given().log().all().spec(getSpec().contentType(ContentType.MULTIPART)).queryParam("key", "Shriganesh").header("name","shriganesh").
                multiPart("control1","content1");
        String responseString=rq.post("/post").then().log().all().extract().response().toString();
    }

    /**
     * request body is sent in multpile parts, content type is Multipart
     */
    @Test
    public void UploadFile(){
        RequestSpecification rq= given().log().all().spec(getSpec().contentType(ContentType.MULTIPART)).queryParam("key", "Shriganesh").header("name","shriganesh").
                multiPart("control1","content1").
                multiPart("attributes", "{\"name\":\"Ramesh\"}","application/json").
                multiPart( new File("src/test/java/resources/addPlace.json"));
        String responseString=rq.post("/post").then().log().all().extract().response().toString();
    }


    /**
     * request body in url encoded - x-www-form-url-encoded
     * @return
     */
    @Test
    public void FormDatAsUrlEncoded(){
        RequestSpecification rq= given().config(config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .log().all().spec(getSpec()).contentType(ContentType.URLENC).
                formParam("form1","content1");
        String responseString=rq.post("/post").then().log().all().extract().response().toString();

    }

    /**
     *
     *
     * @return
     */
    @Test
    public void filter(){
        RequestSpecification rq= given().config(config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .filter(new RequestLoggingFilter(LogDetail.ALL))
                .filter(new RequestLoggingFilter(LogDetail.ALL))
                .spec(getSpec()).contentType(ContentType.URLENC).
                formParam("form1","content1");
        String responseString=rq.post("/post").then().log().all().extract().response().toString();

    }

    /**
     * log to file
     * @return
     */

    @Test
    public void filterLogToFile() throws FileNotFoundException {
        PrintStream outputStream=new PrintStream(new File("restAssuredFilter.log"));
        RequestSpecification rq= given().config(config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .filter(new RequestLoggingFilter(LogDetail.ALL, outputStream))
                .filter(new ResponseLoggingFilter(LogDetail.ALL, outputStream))
                .spec(getSpec()).contentType(ContentType.URLENC).
                formParam("form1","content1");
        String responseString=rq.post("/post").then().log().all().extract().response().toString();

    }
    public RequestSpecification getSpec(){
        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("http://postman-echo.com").setContentType(ContentType.JSON).build();
        return requestSpecification;
    }


    /**
     * send json object to rest endpoint
     *
     * json object ------serialize using jackson object mapper -----send as string
     */

    @Test
    public void jsonObjectToPost() throws JsonProcessingException {
        RequestSpecification rq= given().log().all().spec(getSpec())
                .body(jsonObjectToString());
        String responseString=rq.post("/post").then().log().all().extract().response().toString();

    }

    public String jsonObjectToString() throws JsonProcessingException {
        Map<String,String> map = new HashMap<>();
                map.put("name", "Shriganesh");
        ObjectMapper mapper=new ObjectMapper();
        String str =mapper.writeValueAsString(map);
        return str;
    }

    public void myjsonArray() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();

     JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(""));

    }


    @Test
    public void encodeAnddecodeString(){
        String userNameColonPassword = "username:password";
        String bytes = Base64.getEncoder().encodeToString(userNameColonPassword.getBytes());
        System.out.println(bytes.toString());
        byte[] decodedStr = Base64.getDecoder().decode(bytes);
        System.out.println(new String(decodedStr));
    }
}

