package com.cydeo.tests.day11;
import com.github.javafaker.Faker;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;

public class Homeworks {

    @BeforeAll
    public static void setUp(){
        baseURI="http://ergast.com";
        basePath="/api/f1";
    }


    @AfterAll
    public static void tearDown(){
        reset();
    }



    @DisplayName("Homework1")
    @Test
    public void test1(){
        // Homework , Create a method to get RaNdom valid driverId
        // so you can pass to the test /drivers/{driverId}  instead of guessing valid driver id

        // Explore the rest of the Collection
        // for example :
        // http://ergast.com/api/f1/2021/drivers
        // practice getting xml element text value , xml element attributes
        // same goes for the rest of the collection.
        Faker faker=new Faker();
        XmlPath xp=get("/drivers").xmlPath();
        List<String> listID=xp.getList("MRDate.DriverTable.Driver.@driverId");
        int randomID=faker.number().numberBetween(0,listID.size()-1);
        System.out.println("randomID = " + randomID);
        System.out.println("listID.get(randomID) = " + listID.get(randomID));


    }
    @DisplayName("Homework2")
    @Test
    public void test2(){
        // Homework 2 :
        // Send this request in Movie API get xml response by using query param r=xml
        // GET http://www.omdbapi.com/?t=The Mandalorian&r=xml?apiKey=aaa9ecb8

        // print out all the movie attributes

        // Send request GET http://www.omdbapi.com/?s=The Mandalorian&r=xml?apiKey=YourKeyGoesHere
        // verify root element attribute  totalResults="11"
        // store all the titles on List<String> and print.
        // verify the size of list match the attribute totalResults="11"
        Response response = given().
                log().uri()
                .param("apiKey","25d8fdf1")
                .param("t", "The Mandalorian")
                .and()
                .param("r", "xml")

                .when()
                .get("http://www.omdbapi.com/");
        XmlPath xp=response.xmlPath();
        xp.prettyPeek();
        String staticXML="root.movie.";
        List<String> allAtribute=new ArrayList<>(Arrays.asList("title","year","rated","released","runtime","genre",
                "director","writer","actors","plot","language","country","awards","poster","metascore","imdbRating"
        ,"imdbVotes","imdbID","type"));

        for (String eachAtribute : allAtribute) {
            String attribute=xp.getString(staticXML+"@"+eachAtribute);
            System.out.println("attribute = " + attribute);
        }

    }

    @DisplayName("Homework3")
        @Test
    public void test3(){

        // Homework 3 :
        // Send request to  GET /spartans provide accept header to get XML response
        // practice getting the value of xml elements for single elements
        // or list of elements

        Response response=given().log().uri()
                .header("Accept","application/xml")
                .when()
                .get("http://54.236.150.168:8000/api/spartans");
        XmlPath xp=response.xmlPath();


        List<Integer> allId=xp.getList("List.item.id");
        System.out.println("allId = " + allId);

        List<String> allName=xp.getList("List.item.name");
        System.out.println("allName = " + allName);

        List<String> allGender=xp.getList("List.item.gender");
        System.out.println("allGender = " + allGender);

        List<Long> allPhone=xp.getList("List.item.phone");
        System.out.println("allPhone = " + allPhone);
    }

    @DisplayName("Homework4")
    @Test
    public void test4(){

        // Homework 4 :
          /*
            Send GET Request to https://vpic.nhtsa.dot.gov/api/vehicles/GetMakeForManufacturer/988?format=xml
            and verify the
            count element text is 2
            message Results returned successfully
            first Make_ID is  474 , Make_Name Honda
            */
        Response response=get("https://vpic.nhtsa.dot.gov/api/vehicles/GetMakeForManufacturer/988?format=xml");
        XmlPath xp= response.xmlPath();

        int count=xp.getInt("Response.Count");
        System.out.println("count = " + count);
        Assertions.assertEquals(count,2);

        String message=xp.getString("Response.Message");
        System.out.println("message = " + message);
        Assertions.assertEquals(message,"Results returned successfully");

        int id=xp.getInt("Response.Results.MakesForMfg.Make_ID[0]");
        System.out.println("id = " + id);
        Assertions.assertEquals(id,474);

        String name=xp.getString("Response.Results.MakesForMfg.Make_Name[0]");
        System.out.println("name = " + name);
        Assertions.assertEquals(name,"HONDA");

    }
}
