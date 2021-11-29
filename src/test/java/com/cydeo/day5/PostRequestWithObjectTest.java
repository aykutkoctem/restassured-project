package com.cydeo.day5;
import com.cydeo.utility.SpartanTestBase;
import com.cydeo.utility.SpartanUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

/**
 * Serialization :
 *      Process of Converting from Java Object to Json (or any other text)
 * De-Serialization :
 *       Process of Converting from Json(any text) to Java Object
 */

public class PostRequestWithObjectTest extends SpartanTestBase{

    /**
     POST /spartans
     content type is json
     body is
     {
     "name":"API POST",
     "gender":"Male",
     "phone":1231231231
     }
     instead of providing above body in String ,
     We want to be able to provide the body as java object (like map or POJO)
     and let any kind of Serialization library convert that into String for us
     while sending the request
     **/

    @Test
    public void testPostWithMap(){

        Map<String, Object> bodyMap = new LinkedHashMap<>() ;
        bodyMap.put("name","API POST");
        bodyMap.put("gender","Male");
        bodyMap.put("phone",1231231231L);

        System.out.println("bodyMap = " + bodyMap);
        /**
         Jackson-databind is the library for serialization and de-serialization-->
         rest assured need it in dependency so it can automatically
         convert the java object to json without additional code
         */
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body( bodyMap ).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201) ;

    }


    @Test
    public void testPostWithMapWithRandomData(){

        // create random data instead of always creating with same body
        // optionally : create a utility out of it.


        // having utility, so we can just call a method as below
        // 1. create a class under utility package with name SpartanUtil
        // 2. create a public static method with return type of Map<String, Object>
        // 3. add above code you already wrote for method body and return the bodyMap from the method
        // 4. call the method here to get the random body
        Map<String, Object> bodyMap  = SpartanUtil.getRandomSpartanBody();


        /**
         Jackson-databind is the library for serialization and de-serialization-->
         rest assured need it in dependency so it can automatically
         convert the java object to json without additional code
         */
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body( bodyMap ).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201) ;

    }

    // Send request to PUT /spartans/{id}  with Random Map Body
    @Test
    public void testUpdate1DataWithRandomBody(){

        // instead of guessing what id exists in my server
        // I will send request to get all spartans and get last json object id

        int lastId = get("/spartans").path("id[-1]") ;
        System.out.println("lastId = " + lastId);

        // prepare Updated body :
        Map<String, Object> updatedBodyMap = SpartanUtil.getRandomSpartanBody() ;

        given()
                .log().all()
                .pathParam("id", lastId)
                .contentType(ContentType.JSON)
                .body(updatedBodyMap).
                when()
                .put("/spartans/{id}").
                then()
                .log().all()
                .statusCode(204) ;
        // Homework : Send another get request to verify the body actually get updated.

    }









}





