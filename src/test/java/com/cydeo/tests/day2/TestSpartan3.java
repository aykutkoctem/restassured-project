package com.cydeo.tests.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class TestSpartan3 {

    // set up and teardown
    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "http://54.236.150.168:8000" ;
        RestAssured.basePath = "/api";
    }

    @AfterAll
    public static void teardown(){
        reset();
    }

    // Send request to GET /spartans and check status code 200 and content type json
    @Test
    public void testAllSpartan(){

        Response response = get("/spartans");
        response.prettyPrint() ;  // print the payload and return it as string at the same time.

        Assertions.assertEquals(200,  response.statusCode()  );

        Assertions.assertEquals(ContentType.JSON.toString() ,  response.contentType()     );

        System.out.println("response.path(\"[0].gender\") = "
                + response.path("[0].gender"));

        System.out.println("response.path(\"gender[0]\") = "
                + response.path("gender[0]"));

        // Get All the id (instead of just one) and store it into List<Integer>
        // "id" in here will get all the id from this json array
        // and since we provided the variable data type as List<Integer>
        // it will automatically add all the id into the list for you.
        List<Integer> idList =   response.path("id") ;
        System.out.println("idList = " + idList);


    }


    // Send request to GET /spartans and provide accept header as application xml
    // and check status code 200 and content type application xml

    @Test
    public void testGetXMLResponse(){

        // RestAssured use method chaining extensively to combine all part of requests
        // and verify the response in one shot
        // here since we need to provide additional header information to get xml response
        // we will start learning some method chaining to see
        // how we can provide additional information to the request

        // you can either write it in one line
//        Response response = given().header("Accept","application/xml").when().get("/spartans") ;
        // or multiple line to make it slightly more obvious
        Response response = given()
                //.header("Accept","application/xml").
//                                .header("Accept",ContentType.XML).
                .accept(ContentType.XML).      // special support method for accept header
                        when()
                .get("/spartans") ;
        response.prettyPrint();
        // write assertions
        Assertions.assertEquals(200,  response.statusCode() );
        Assertions.assertEquals(  ContentType.XML.toString()  ,response.contentType()   );



    }

    // SEND REQUEST TO GET http://54.236.150.168:8000/api/spartans/search?nameContains=Ea&gender=Male
    @Test
    public void testSearch(){

        Response response = given()
                //.accept(ContentType.JSON)     // this is redundant because it already give us json
                .queryParam("nameContains","Ea") // this is how you provide one query param
                .queryParam("gender","Male").    // this is how you provide another query param
                        when()
                .get("/spartans/search") ;
        response.prettyPrint() ;

        System.out.println("response.path(\"totalElement\") = " + response.path("totalElement"));
        System.out.println("response.path(\"content[0].name\") = " + response.path("content[0].name"));
        System.out.println("response.path(\"content.name[0]\") = " + response.path("content.name[0]"));

        System.out.println("response.path(\"content[1].name\") = " + response.path("content[1].name"));
        System.out.println("response.path(\"content[1].id\") = " + response.path("content[1].id"));


        List<String> allNames=response.path("content.name");
        System.out.println("allNames = " + allNames);
        System.out.println(allNames.size());
    }
@Test
    public void testOneSpartanPathParam(){

        Response response=given().pathParam("id",4).log().all()
    .when().get("/spartans/{id}");
        response.prettyPrint();

        String str="B23 is Great";
        str.toUpperCase().toLowerCase().replace("Great","Excellent").startsWith("B23");
}


}