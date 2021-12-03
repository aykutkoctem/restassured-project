package com.cydeo.tests.officeHours.Day2;
import com.cydeo.utility.HR_ORDSTestBase;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsInRelativeOrder;

public class P3_HamCrest extends HR_ORDSTestBase {

    /*
      Given
               accept type is application/json
       When
               user sends get request to /regions
       Then
               response status code must be 200
               verify Date has values
               first region name is Europe
               Regions name should be same order as "Europe","Americas","Asia","Middle East and Africa"
               region ids needs to be 1,2,3,4
               ...
               ..
               .
    */

    @Test
    public void getRegion() {

        Response response = given().log().uri()
                .accept(ContentType.JSON)
                .when()
                .get("/regions").prettyPeek()
                .then()
                .statusCode(200)
                .header("Date", notNullValue())
                .body("items[0].region_name", is("Europe"))
                .body("items[0].region_id", is(1))
                .body("items", hasSize(4))
                .body("items.region_name", containsInRelativeOrder("Europe", "Americas", "Asia", "Middle East and Africa"))
                .body("items.region_id", containsInRelativeOrder(1, 2, 3, 4))
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        List<String> list = jsonPath.getList("items.region_name");
        System.out.println("list = " + list);

        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

    }
}