package com.cydeo.tests.day11;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class XMLResponseTest {
    //http://ergast.com/api/f1/drivers/{driverId}

    @BeforeAll
    public static void setUp(){
baseURI="http://ergast.com";
basePath="/api/f1";
    }


    @AfterAll
    public static void tearDown(){
reset();
    }
    @DisplayName("Test GET /drivers/{driverId} xml response")
    @Test
    public void testSingleDriverXMLResponse(){

        given().pathParam("driverId","abate")
                .log().uri()
                .when().get("/drivers/{driverId}")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.XML)
                .body("MRData.DriverTable.Driver.GivenName",is("Carlo"))
                .body("MRData.DriverTable.Driver.FamilyName",is("Abate"));


    }

    @DisplayName("Test GET /drivers/{driverId} extract xml  elements text")
    @Test
    public void testSingleDriverXMLResponse2(){

        Response response=given()
                .pathParam("driverId","abate")
                .log().uri().
                when()
                .get("/drivers/{driverId}");
     //just like JsonPath class,there is specialized class for xml --> XMLPath
       XmlPath xp= response.xmlPath();
       String firstName=xp.getString("MRData.DriverTable.Driver.GivenName");
        System.out.println("firstName = " + firstName);
         String lastName=xp.getString("MRData.DriverTable.Driver.FamilyName");
        System.out.println("lastName = " + lastName);


        // what is the xmlpath to get attribute of an element
        // if your element tag is  Person  and attrinute is gender
        // here is the xmlpath to get the attribute   Person.@gender
        // TagName.@attributeName syntax

        // get the value of MRData.DriverTable @driverId=abate
        String driverIdAttribute = xp.getString("MRData.DriverTable.@driverId") ;
        System.out.println("driverIdAttribute = " + driverIdAttribute);

        String urlAttribute=xp.getString("MRData.DriverTable.Driver.@url");
        System.out.println("urlAttribute = " + urlAttribute);
    }
    @DisplayName("Test GET/drivers xmt response")
    @Test
    public void getAllDriverXMLResponse(){
        XmlPath xp=get("/drivers").xmlPath();
        String firstDriverName=xp.getString("MRData.DriverTable.Driver[0].GivenName");
        System.out.println("firstDriverName = " + firstDriverName);

        //get all the GivenNames of all drivers

        List<String> allGivenName = xp.getList("MRDate.DriverTable.Driver.GivenName",String.class);
        System.out.println("allGivenName = " + allGivenName);

        List<String> allDriverId = xp.getList("MRDate.DriverTable.Driver.@driverId",String.class);
        System.out.println("allDriverId = " + allDriverId);

        // Homework , Create a method to get RaNdom valid driverId
        // so you can pass to the test /drivers/{driverId}  instead of guessing valid driver id

        // Explore the rest of the Collection
        // for example :
        // http://ergast.com/api/f1/2021/drivers
        // practice getting xml element text value , xml element attributes
        // same goes for the rest of the collection.

        // Homework 2 :
        // Send this request in Movie API get xml response by using query param r=xml
        // GET http://www.omdbapi.com/?t=The Mandalorian&r=xml?apiKey=YourKeyGoesHere

        // print out all the movie attributes

        // Send request GET http://www.omdbapi.com/?s=The Mandalorian&r=xml?apiKey=YourKeyGoesHere
        // verify root element attribute  totalResults="11"
        // store all the titles on List<String> and print.
        // verify the size of list match the attribute totalResults="11"


        // Homework 3 :
        // Send request to  GET /spartans provide accept header to get XML response
        // practice getting the value of xml elements for single elements
        // or list of elements


        // Homework 4 :
          /*
            Send GET Request to https://vpic.nhtsa.dot.gov/api/vehicles/GetMakeForManufacturer/988?format=xml
            and verify the
            count element text is 2
            message Results returned successfully
            first Make_ID is  474 , Make_Name Honda
            */

    }
}
