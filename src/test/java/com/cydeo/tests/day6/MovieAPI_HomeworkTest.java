package com.cydeo.tests.day6;
import com.cydeo.pojo.Movie;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class MovieAPI_HomeworkTest {

    //http://www.omdbapi.com/?s=The%20Mandalorian&apikey=25d8fdf1

    @BeforeAll
    public static void setup(){
        baseURI="http://www.omdbapi.com";

    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @Test
    public void testMovies(){

        //http://www.omdbapi.com/?s=The%20Mandalorian&apikey=25d8fdf1

        JsonPath jp =   given()
                .log().uri()
               .queryParam("apikey", "25d8fdf1")
               .queryParam("s", "The Mandalorian").
                when()
                .get("")
                .prettyPeek()
                .jsonPath()
                ;  // our url is already complete no need to add anything here

        // what is the json path to get the first object : Search[0]
        Movie m1 = jp.getObject("Search[0]", Movie.class) ;
        System.out.println("m1 = " + m1);

        //setTitle -->  title


    }


}