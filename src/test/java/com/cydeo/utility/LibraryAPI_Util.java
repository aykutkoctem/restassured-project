package com.cydeo.utility;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class LibraryAPI_Util {

    // create a method to get token

    public static String getToken(){

        return given()
                .contentType(ContentType.URLENC)
                .formParam("email" , "librarian47@library")
                .formParam("password" , "Sdet2022*").
                when()
                .post("/login").path("token") ;


    }

    // create a method to generate
    // random book map for form params body
    public static Map<String,Object> getRandomBookMap(){

        Faker faker = new Faker() ;
        Map<String,Object> bookMap = new LinkedHashMap<>();
        bookMap.put("name", faker.book().title()   );
        bookMap.put("isbn", faker.code().isbn10()   );
        bookMap.put("year", faker.number().numberBetween(1000,2021)   );
        bookMap.put("author",faker.book().author()   );
        bookMap.put("book_category_id", faker.number().numberBetween(1,20)   );  // in library app valid category_id is 1-20
        bookMap.put("description", faker.chuckNorris().fact() );

        return bookMap ;
    }



}