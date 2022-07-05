package resources;

import pojo.place.AddPlace;
import pojo.place.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataProvider {
    private static TestDataProvider testDataProvider;
    private TestDataProvider(){};

    public static TestDataProvider getInstance(){
        if(testDataProvider==null){
            testDataProvider =  new TestDataProvider();
        }
        return testDataProvider;
    }

    public AddPlace getJavaObject(){
        AddPlace addPlace =  new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setAddress("29, side layout, cohen 09");
        addPlace.setPhone_number("(+91) 983 893 3937");
        Location location = new Location();
        location.setLat(-38.383494);
        location.setLat(-38.383494);
        addPlace.setLocation(location);
        List<String> typeList = new ArrayList<>();
        typeList.add("Shri");
        addPlace.setTypes(typeList);
        addPlace.setName("Shri");
        addPlace.setLanguage("java");
        addPlace.setWebsite("www.abc.com");
        return addPlace;
    }

    public void getPlace(){

    }

    public void deletePlace(){

    }
}
