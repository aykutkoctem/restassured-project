package com.cydeo.tests.day10;

import com.cydeo.utility.SpartanTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.validation.Schema;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class JsonSchemaValidationTest extends SpartanTestBase {

    @DisplayName("Testing GET /spartan/{id} response schema")
    @Test
    public void testSingleSpartanSchema(){

        int firstIdInApp=get("/spartans").path("id[0]");
      //send request to  GET /spartan/{id}
        //and verify the response json match the schema document
        // under resources folder
        //with the name SingleSpartanSchema.json
        given()
                .pathParam("id", firstIdInApp)
                .log().uri().
                when()
                .get("/spartans/{id}").
                then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("SingleSpartanSchema.json"))
        ;

    }





}
