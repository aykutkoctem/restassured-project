package com.cydeo.tests.officeHours.Day2;

import com.cydeo.utility.HR_ORDSTestBase;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P1_Contains extends HR_ORDSTestBase {
    /*
       Given
                accept type is application/json
        When
                user sends get request to /regions/2
        Then
                response status code must be 200
                content type equals to application/json
                response body contains   Americas
     */


    @Test
    @DisplayName("Get Region")
    public void getRegion() {


        Response response = given().accept(ContentType.JSON).log().all()
                .pathParam("id", 2)
                .when().get("/regions/{id}").prettyPeek();


        assertEquals(200, response.statusCode());

        assertAll(

                () -> assertEquals(ContentType.JSON.toString(), response.contentType()),
                () -> System.out.println("This line needs be executed"),
                () -> assertTrue(response.asString().contains("Americas"))

        );


    }
}