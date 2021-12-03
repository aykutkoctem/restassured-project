package com.cydeo.tests.day9;

import com.cydeo.utility.DB_Util;
import com.cydeo.utility.HR_ORDSTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class API_DB_TEST extends HR_ORDSTestBase {

    @DisplayName("Test Region Count from Api with Database Matches")
    @Tag("db")
    @Test
    public void testRegion(){


        DB_Util.createConnection();
        DB_Util.runQuery("SELECT * FROM REGIONS");
        System.out.println("DB_Util.getRowCount() = " + DB_Util.getRowCount());
        DB_Util.displayAllData();
        DB_Util.destroy();
    }

    @Test
    public void testRegionsCount(){
//prepare expected result here
        DB_Util.runQuery("Select * FROM REGIONS");
        int expectedCount=DB_Util.getRowCount();

        given().
                log().uri()
                .when().get("/regions")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("count",is(expectedCount))
                .body("items",hasSize(expectedCount));

    }
    /**
     * Send request to GET /regions/{region_id}   region_id = 1
     * prepare expected result from the database by running
     * SELECT * FROM REGIONS WHERE REGION_ID = 1
     * Save the result of query as Map
     * then verify the region_id and region_name match between api and db response
     */
    @Test
    public void testSingleRegion(){
DB_Util.runQuery("SELECT * FROM REGIONS WHERE REGION_ID = 1");

Map<String,String> dbResultMap =dbResultMap=DB_Util.getRowMap(1);

        System.out.println("dbResultMap = " + dbResultMap);


        int expectedRegionID=Integer.parseInt(dbResultMap.get("REGION_ID"));
        String expectedRegionName=dbResultMap.get("REGION_NAME");


        given()
                .pathParam("region_id" , 1 )
                .log().uri().
                when()
                .get("/regions/{region_id}").
                then()
                .log().all()
                .statusCode(200)
                .body("region_id" , equalTo( expectedRegionID)   )
                .body("region_name", is(expectedRegionName) )

        ;
    }
}
