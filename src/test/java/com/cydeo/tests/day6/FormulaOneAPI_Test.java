package com.cydeo.tests.day6;


import com.cydeo.pojo.Driver;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.*;

public class FormulaOneAPI_Test {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://ergast.com" ;
        RestAssured.basePath = "/api/f1" ;

    }
    @AfterAll
    public static void cleanup(){
        RestAssured.reset();
    }

    @Test
    public void testDriverDeserialization(){
JsonPath jp=get("/drivers.json").prettyPeek().jsonPath();

        Driver d1=jp.getObject("MRData.DriverTable.Drivers[0]",Driver.class);
        System.out.println("d1 = " + d1);

        List<Driver> allDrivers=jp.getList("MRData.DriverTable.Drivers",Driver.class);
        System.out.println("allDrivers = " + allDrivers);


        List<String> italians=new LinkedList<>();
        List<String> italians1=new ArrayList<>();
        Set<String> italians2=new HashSet<>();
        for (Driver eachDriver : allDrivers) {
            if (eachDriver.getNationality().equals("Italian")){
                italians.add(eachDriver.getGivenName());
                italians1.add(eachDriver.getGivenName());
                italians2.add(eachDriver.getGivenName());
            }
        }
        System.out.println("italians = " + italians);
        System.out.println("italians1 = " + italians1);
        System.out.println("italians2 = " + italians2);
    }
}
