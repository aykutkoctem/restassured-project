package com.cydeo.tests.day8;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static io.restassured.RestAssured.*;

public class ZipcodeMethodSourceTest {


    /**
     * Write a static method that return all zipcodes for Fairfax VA
     * using GET https://api.zippopotam.us/us/va/fairfax
     * and return List<String>
     *
     * Write a parameterized Test to test each and every zipcode in  Fairfax VA
     * using GET https://api.zippopotam.us/us/{zip}
     * use @MethodSource to use the method you created above as a source
     *
     */
    @BeforeAll
    public static void setup() {
        baseURI = "https://api.zippopotam.us/";
        basePath = "/us";
    }

    @AfterAll
    public static void teardown() {
        reset();
    }

    /**
     *     * Write a parameterized Test to test each and every zipcode in  Fairfax VA
     *      * using GET https://api.zippopotam.us/us/{zip}
     */
    // you have option to customize the display name of parametrized test
    // using name = "something" and you have option to use the actual argument
    // using {the location index of argument}  so for first arg it will be {0}
    // for second it will be {1} and next {2} if you have more argument
    //@ParameterizedTest(name = "Getting one zipcode {0}")
    @ParameterizedTest
    @MethodSource("getAllZipCodes")
    public void testAllZipCodes(String zipParam){
        //   GET /us/22030
        System.out.println("zipParam = " + zipParam);
        given()
                .log().uri()
                .pathParam("zip" , zipParam).
                when()
                .get("/{zip}").
                then()
                .statusCode(200) ;

    }



    public static List<String> getAllZipCodes(){

        // Send GET https://api.zippopotam.us/us/va/fairfax and store all zipcodes

        List<String> allZips = get("/va/Fairfax")
                .path("places.'post code'") ;
        return allZips ;

    }




}