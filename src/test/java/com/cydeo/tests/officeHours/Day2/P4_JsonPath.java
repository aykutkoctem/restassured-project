package com.cydeo.tests.officeHours.Day2;
import com.cydeo.utility.HR_ORDSTestBase;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsInRelativeOrder;

public class P4_JsonPath extends HR_ORDSTestBase {

   /*
    Given
             accept type is application/json
     When
             user sends get request to /locaitons
     Then
             response status code must be 200
             content type equals to application/json
             get the second city with JsonPath
             get the last city with JsonPath
             get all country ids
             get all city where their country id is UK
  */

    @Test
    public void getLocations() {

        Response response = given().log().uri()
                .accept(ContentType.JSON)
                .when()
                .get("/locations").prettyPeek();


        JsonPath jsonPath = response.jsonPath();

    /*
        JsonPath jp = given().log().uri()
                .accept(ContentType.JSON)
                .when()
                .get("/locations").prettyPeek().jsonPath();
     */

        System.out.println("jsonPath.getString(\"items[1].city\") = " + jsonPath.getString("items[1].city"));
        System.out.println("jsonPath.getString(\"items[-1].city\") = " + jsonPath.getString("items[-1].city"));

        List<String> list = jsonPath.getList("items.country_id");
        System.out.println("list = " + list);

        // get all city where their country id is UK
        System.out.println("=============================================================================================");
        List<String> cityNamesWithUK = jsonPath.getList("items.findAll {it.country_id=='UK'}.city");
        System.out.println("cityNamesWithUK = " + cityNamesWithUK);
        System.out.println("=============================================================================================");

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(ContentType.JSON.toString(), response.contentType());



    }
    @Test
    public void getEmployees() {

        Response response = given().log().uri()
                .accept(ContentType.JSON)
                .when()
                .get("/employees").prettyPeek();


        JsonPath jsonPath = response.jsonPath();


        List<String> firstName=jsonPath.getList("items.findAll {it.salary>15000}.first_name");
        System.out.println("firstName = " + firstName);



    }
}
