package com.cydeo.tests.officeHours.Day2;

import com.cydeo.pojo.Locations;
import com.cydeo.utility.HR_ORDSTestBase;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P5_DeSerialization extends HR_ORDSTestBase {

    /**
     * Create a test called getLocation
     * 1. Send request to GET /locations
     * 2. log uri to see
     * 3. Get all Json as a map and print out screen all the things with the help of  map
     * System.out.println("====== GET FIRST LOCATION  ======");
     * System.out.println("====== GET FIRST LOCATION LINKS  ======");
     * System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");
     * System.out.println("====== FIRST LOCATION ======");
     * System.out.println("====== FIRST LOCATION ID ======");
     * System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
     * System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
     * System.out.println("====== LAST LOCATION ID ======");
     */

    @Test
    public void getLocationWithMap() {

        JsonPath jsonPath = given().log().uri().
                when().get("/locations").prettyPeek().jsonPath();

        System.out.println("====== GET FIRST LOCATION  ======");
        Map<String, Object> firstLocationMap = jsonPath.getMap("items[0]");
        System.out.println("firstLocationMap = " + firstLocationMap);

        System.out.println("====== GET FIRST LOCATION LINKS  ======");
        Map<String, Object> firstLocationsLinks = jsonPath.getMap("items[0].links[0]");
        System.out.println("firstLocationsLinks = " + firstLocationsLinks);
        System.out.println("firstLocationsLinks.get(\"rel\") = " + firstLocationsLinks.get("rel"));
        System.out.println("firstLocationsLinks.get(\"href\") = " + firstLocationsLinks.get("href"));

        System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");
        List<Map<String, Object>> allLocations = jsonPath.getList("items");
        System.out.println("allLocations = " + allLocations);

        System.out.println("====== FIRST LOCATION ======");
        System.out.println("allLocations.get(0) = " + allLocations.get(0));


        System.out.println("====== FIRST LOCATION ID ======");
        System.out.println("allLocations.get(0).get(\"location_id\") = " + allLocations.get(0).get("location_id"));


        System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
        System.out.println("allLocations.get(0).get(\"county_id\") = " + allLocations.get(0).get("county_id"));


        System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
        System.out.println("allLocations.get(0).get(\"links\") = " + allLocations.get(0).get("links"));
        List<Map<String, String>> links = (List<Map<String, String>>) allLocations.get(0).get("links");
        System.out.println("links.get(0).get(\"rel\") = " + links.get(0).get("rel"));
        System.out.println("links.get(0).get(\"href\") = " + links.get(0).get("href"));


        System.out.println("====== LAST LOCATION ID ======");
        System.out.println("allLocations.get(allLocations.size() - 1).get(\"location_id\") = " + allLocations.get(allLocations.size() - 1).get("location_id"));


    }

    /**
     * Create a test called getLocation
     * 1. Send request to GET /locations
     * 2. log uri to see
     * 3. Get all Json as a Location Object and print out screen all the things with the help of  POJO
     * System.out.println("====== GET FIRST LOCATION  ======");
     * System.out.println("====== GET FIRST LOCATION LINKS AS MAP ======");
     * System.out.println("====== ALL LOCATIONS ======");
     * System.out.println("====== FIRST LOCATION ======");
     * System.out.println("====== FIRST LOCATION ID ======");
     * System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
     * System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
     * System.out.println("====== LAST LOCATION ID ======");
     */
    @Test
    public void getLocationsWithPOJO() {


        JsonPath jsonPath = given().log().uri().
                when().get("/locations").prettyPeek().jsonPath();


        List<Locations> allLocations = jsonPath.getList("items", Locations.class);


        System.out.println("====== GET FIRST LOCATION  ======");
        System.out.println("allLocations.get(0) = " + allLocations.get(0));
/*
        System.out.println("====== GET FIRST LOCATION LINKS AS MAP ======");
        System.out.println("allLocations.get(0).getLinks().get(0).get(\"rel\") = " + allLocations.get(0).getLinks().get(0).get("rel"));
        System.out.println("allLocations.get(0).getLinks().get(0).get(\"href\") = " + allLocations.get(0).getLinks().get(0).get("href"));


 */



        System.out.println("====== GET FIRST LOCATION LINKS AS POJO ======");
        System.out.println("allLocations.get(0).getLinksObject().get(0).getHref() = " + allLocations.get(0).getLinks().get(0).getHref());
        System.out.println(allLocations.get(0).getLinks().get(0).getRel());



        System.out.println("====== ALL LOCATIONS ======");
        System.out.println("allLocations = " + allLocations);

        System.out.println("====== FIRST LOCATION ID ======");
        System.out.println("allLocations.get(0).getLocationId() = " + allLocations.get(0).getLocationId());

        System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
        System.out.println("allLocations.get(0).getCountryId() = " + allLocations.get(0).getCountryId());

        System.out.println("====== LAST LOCATION ID ======");
        System.out.println("allLocations.get(allLocations.size()-1) = " + allLocations.get(allLocations.size() - 1));


    }
}