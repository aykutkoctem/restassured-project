package com.cydeo.tests.day8;

import com.cydeo.utility.LibraryAPI_BaseTest;
import com.cydeo.utility.LibraryAPI_Util;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LibraryLoginTest extends LibraryAPI_BaseTest {


    @ParameterizedTest
    @CsvFileSource(resources = "/library_credentials.csv", numLinesToSkip = 1)
    public void testLogin(String username, String password){

        System.out.println("username = " + username);
        System.out.println("password = " + password);

        given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("email" , username)
                .formParam("password" , password).
                when()
                .post("/login").
                then()
                .log().all()
                .statusCode(200) ;
        // why 200 , in this particular api it decided to give you 200 since you are not actually adding data to the server
    }


    @DisplayName("Librarian Should be able to add book")
    @Test
    public void testAddBook(){

        // first get library token by sending POST /login request
        // and save it  (eventually make a method out of it)
        String libraryToken = LibraryAPI_Util.getToken() ;   // call the method here

        // if you have many form parameters as body
        // you can use formParams method and pass map object instead
        // optionally create a utility to generate random book map object

        System.out.println("bookMap = " + LibraryAPI_Util.getRandomBookMap() );

        // send request to POST /add_book and verify the body
        // "message": "The book has been created.",
        given()
                .log().all()
                .header("X-LIBRARY-TOKEN", libraryToken)
                .contentType(ContentType.URLENC)
                .formParams(  LibraryAPI_Util.getRandomBookMap()  ).
                when()
                .post("/add_book").
                then()
                .log().all()
                .body("message",  is("The book has been created.") ) ;





    }




}