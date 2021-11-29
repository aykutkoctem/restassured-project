package com.cydeo.day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class SwapiTest {

    @BeforeAll
    public static void setup(){
        baseURI  = "https://swapi.dev";
        basePath = "/api";
    }

    @AfterAll
    public static void teardown(){
        reset();

    }

    @Test
    public void testHomework1(){
        Response response= given().log().all().when().get("https://swapi.dev/api/people");
       response.prettyPrint();

       given().log().all().contentType(ContentType.JSON).when().get("https://swapi.dev/api/people").then()
               .log().all()     .contentType(ContentType.JSON).body("count",is(82));


        Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());
        int countValue=response.path("count");
        Assertions.assertEquals(82,countValue);
        String actualName=response.path("results[0].name");
        System.out.println("actualName = " + actualName);
        Assertions.assertEquals("Luke Skywalker",actualName);
        String hairColor=response.path("results[3].hair_color");
        System.out.println("hairColor = " + hairColor);
        Assertions.assertEquals("none",hairColor);
        String yearBirth=response.path("results[-1].birth_year");
        System.out.println("yearBirth = " + yearBirth);
        Assertions.assertEquals("57BBY",yearBirth);
        List<String> allName=response.path("results.name");
        System.out.println("allName.size() = " + allName.size());
        Assertions.assertEquals(allName.size(),10);
        List<String> HeightAll=response.path("results.height");
        System.out.println("HeightAll = " + HeightAll);
        System.out.println(Collections.max(HeightAll));


        Integer max = Integer.MIN_VALUE;

        for (String height : HeightAll) {
            if (Integer.parseInt(height) > max) {
                max = Integer.parseInt(height);
            }
        }

        System.out.println(max);
    }

    @Test
    public void testHomework2(){
        given().log().all().queryParam("page",2).
                when().get("https://swapi.dev/api/people?page=2").
                then().log().all().body("results[3].name",is("Han Solo")).body("results[8].name",is("Yoda"));


        Response response=get("https://swapi.dev/api/people?page=2");
        System.out.println((String) response.path("results[3].name"));


        String name=response.path("results[3].name");
        System.out.println(name);
    }

    @Test
    public void testHomework3(){
       // Response response=given().pathParam("film_id",3).log().all()
           //     .when().get("https://swapi.dev/api/films/3");
       given().log().all().queryParam("episode_id",3).when().get("https://swapi.dev/api/films/3").then().log().all()
               .statusCode(200).contentType(ContentType.JSON).body("title",is("Return of the Jedi"));

       Response response=get("https://swapi.dev/api/films/3");
      List<String> ALLChr=response.path("characters");
        System.out.println(ALLChr.size());
    }
}
/*
   Response response= given().log().all().when().get("/jobs");
        response.prettyPrint();

        Assertions.assertEquals(200,response.statusCode());

        Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());

        int countValue=response.path("count");
        Assertions.assertEquals(19,countValue);
        String secondJobId=response.path("items[1].job_id");
        System.out.println("secondJobId = " + secondJobId);

        int fourthMinSalary=response.path("items[3].min_salary");
        System.out.println("fourthMinSalary = " + fourthMinSalary);

       List<String> jobTitles=response.path("items.job_title");
        System.out.println("jobTitle = " + jobTitles);
        System.out.println(jobTitles.size());
 */