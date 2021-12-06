package com.cydeo.tests.day8.homework;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class Wheather {


    @Test
 public void testWheather(){


     Response response=given().log().all()
             .queryParam("q","London")
             .queryParam("appid", "1d62ad5caf9c8023a9572b311aaaba69").
             when()
             .get("http://api.openweathermap.org/data/2.5/weather")
             ;
     JsonPath jsonPath=response.jsonPath();
     double minTemp = jsonPath.getDouble("main.temp_min");
     double maxTemp = jsonPath.getDouble("main.temp_max");
     System.out.println("minTemp = " + minTemp);
     System.out.println("maxTemp = " + maxTemp);

     reset();
 }
}