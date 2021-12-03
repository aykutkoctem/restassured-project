package com.cydeo.tests.day5;
import com.cydeo.pojo.Spartan;
import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
@Tag("tc1")
public class PostPutRequestWithPOJO_Test extends SpartanTestBase{

    /**
     POST /spartans
     content type is json
     body is
     {
     "name":"API POST",
     "gender":"Male",
     "phone":1231231231
     }
     We learned how to represent json body using Map
     and let Jackson data-bind library to serialize it into json when sending to the server
     Another common way of representing json data is using
     special purpose Java Object created from A class
     that have fields matched to json keys
     and have getters and setters
     This type of Object , sole purpose is to represent data ,
     known as POJO , plain old java object
     The class to create POJO known as POJO class
     It's used for creating POJO , just like you create any other object
     for example : in order to represent a json data with 3 keys , name, gender, phone
     we can create a java class with 3 instance fields with same name
     **/

    @Test
    public void testPostWithPOJOasBody(){
        // preparing the post body as POJO
        Spartan sp1 = new Spartan("B23 POJO", "Male", 5555555555L);
        System.out.println("sp1 = " + sp1);

        // HOMEWORK : Create a method under SpartanUtil class
        // called getRandomSpartanPOJO()
        // return Spartan object with randomized field values


        // now we are going to use this body in post request
        // and expect jackson-databind to convert that to json string when sending as body
        // so it can be added to the server as new data
        // only thing different in here is using POJO as body

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(sp1).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201);


    }


    /**
     given()
     .log().all()
     .contentType(ContentType.JSON)
     .body( sp1 ).
     when()
     .post("/spartans").
     then()
     .log().all()
     .statusCode(201) ;
     **/


    // HOMEWORK : Use POJO for Update 1 Data PUT Request
    // use your getRandomSpartanPOJO utility method for body

}