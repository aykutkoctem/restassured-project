package com.cydeo.tests.day9;

import com.cydeo.utility.DB_Util;
import com.cydeo.utility.HR_ORDSTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class Homework extends HR_ORDSTestBase {

    /**
     * Write a Parameterized Test to test all regions instead of one above
     * try couple different way
     * 1. @ValueSource  to provide all 4 regions id
     * 2. @MethodSource
     *       -- get all id s from api response GET /regions and return List<Integer>
     * 3. @MethodSource
     *       -- get all id s from SELECT * FROM REGIONS query and return List<String>
     *           and use it as a source
     * @return
     */

    @ParameterizedTest()
    @DisplayName("Test 1")
    @MethodSource("getAllID")
    public void tes1(int id){
        given().pathParam("region_id",id)
                .log().all().when().get("regions/{region_id}")
                .then().log().all().statusCode(200);
    }

    public static List<Integer> getAllID(){



        List<Integer> allID = get("/regions")
                .path("items.region_id") ;
        return allID ;

    }
    public static List<String> getAllID2(){
        DB_Util.runQuery("SELECT * FROM REGIONS");
        List<String> dbResult=DB_Util.getColumnDataAsList(1);

        return dbResult;

    }

    @ParameterizedTest()
    @DisplayName("Test 2")
    @MethodSource("getAllID2")
    public void test2(String id2){
        given().pathParam("region_id",id2).log().all()
                .when().get("regions/{region_id}")
                .then().log().all().statusCode(200);
    }

    @ParameterizedTest
    @DisplayName("Test 3")
    @ValueSource(ints = {1,2,3,4})
    public void test3(int id3){
        given().pathParam("region_id",id3).log().all()
                .when().get("regions/{region_id}")
                .then().log().all().statusCode(200);

    }
}
