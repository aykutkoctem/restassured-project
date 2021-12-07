package com.cydeo.tests.day11;

import com.cydeo.utility.SpartanTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.* ;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
@DisplayName("Json Schema Validation Tests")
public class JsonSchemaValidationPracticeTest extends SpartanTestBase {


    @DisplayName("Test GET /spartans/search schema")
    @Test
    public void testSearchSchema(){

        //http://54.236.150.168:8000/api/spartans/search?nameContains=Ea&gender=Male
        // When there are more than couple key value pairs
        // like many headers , or many query params , form params
        // RestAssured provided method to let you pass it as a map in one shot
        Map<String, Object> queryMap = new LinkedHashMap<>();
        queryMap.put("nameContains", "Ea" ) ;
        queryMap.put("gender", "Male" ) ;


        given()
                .log().uri()
                .queryParams( queryMap ).
                when()
                .get("/spartans/search").
                then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("SearchSpartanSchema.json"))
        ;


    }

}