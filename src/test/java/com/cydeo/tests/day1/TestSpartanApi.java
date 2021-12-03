package com.cydeo.tests.day1;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.* ;


@Tag("smokeAll")
public class TestSpartanApi {

    @Test
    public void TestHello(){
        System.out.println("Hello world");

        // send request to below request url and save the response
        // GET http://54.236.150.168:8000/api/hello
        // RestAssured.get("http://54.236.150.168:8000/api/hello") ;
        // get method is coming from RestAssured class to send get request to the url defined
        // the result of sending request can be stored in Response object coming from RestAssured
        // Type Response is coming from import io.restassured.response.Response;
        Response response = get("http://54.236.150.168:8000/api/hello");
        System.out.println("response.statusCode() = " +  response.statusCode()    );

        // there are many ways to print the response body , the easiest one will be prettyPrint
        response.prettyPrint() ;
        // assert status code is 200
        Assertions.assertEquals(200 ,   response.statusCode()   );
    }

}
