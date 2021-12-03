package com.cydeo.tests.officeHours.Day2;

import com.cydeo.utility.HR_ORDSTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P2_ResponsePath extends HR_ORDSTestBase {

   /*
       Given
                accept type is application/json
        When
                user sends get request to /regions/2
        Then
                response status code must be 200
                region_name is Americas
                region_id is 2
                print out all the links
     */

    @Test
    public void getRegion() {

        Response response = given().accept(ContentType.JSON).log().all()
                .pathParam("id", 2)
                .when().get("/regions/{id}").prettyPeek();

        assertEquals(200, response.statusCode());

        Integer region_id = response.path("region_id");
        System.out.println("region_id = " + region_id);

        String region_name = response.path("region_name");
        System.out.println("region_name = " + region_name);

        List<Map<String,String>> links = response.path("links");

        for (Map<String, String> link : links) {
            System.out.println("========================");
            System.out.println("link.get(\"rel\") = " + link.get("rel"));
            System.out.println("link.get(\"href\") = " + link.get("href"));
            System.out.println("========================");
        }

        assertEquals("Americas", region_name);
        assertEquals(2, region_id);


    }



    @ParameterizedTest
    @CsvFileSource(resources = {"/region.csv"},numLinesToSkip = 1)
    public void getRegionParameterized(Integer id,String regionName) {

        Response response = given().accept(ContentType.JSON).log().all()
                .pathParam("id", id)
                .when().get("/regions/{id}").prettyPeek();

        assertEquals(200, response.statusCode());

        Integer region_id = response.path("region_id");
        System.out.println("region_id = " + region_id);

        String region_name = response.path("region_name");
        System.out.println("region_name = " + region_name);

        List<Map<String,String>> links = response.path("links");

        for (Map<String, String> link : links) {
            System.out.println("========================");
            System.out.println("link.get(\"rel\") = " + link.get("rel"));
            System.out.println("link.get(\"href\") = " + link.get("href"));
            System.out.println("========================");
        }

        assertEquals(regionName, region_name);
        assertEquals(id, region_id);


    }
}