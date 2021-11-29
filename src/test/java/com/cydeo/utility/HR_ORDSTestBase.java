package com.cydeo.utility;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.reset;

/**
 * This class will serve as Base Class to set up BaseURI and BasePath
 * and clean up after all test for any Spartan related test classes.
 */
public class HR_ORDSTestBase  {

    // set up and teardown
    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "http://54.236.150.168:1000" ;
        RestAssured.basePath = "/ords/hr";
    }

    @AfterAll
    public static void teardown(){
        reset();
    }

}