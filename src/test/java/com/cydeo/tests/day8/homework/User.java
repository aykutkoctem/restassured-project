package com.cydeo.tests.day8.homework;
import com.cydeo.utility.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import com.cydeo.utility.SpartanAuthTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;

public class User extends SpartanAuthTestBase {

    /**
     * As homework
     * Create 3 separate class AdminAuthTest , EditorAuthTest , UserAuthTest
     * Write separate tests for below requirement to do Role Base access control test
     *      * admin :  create , read , update , delete
     *      * editor:  create , read , update
     *      * user  :  read
     */

    @DisplayName("User should be able to read a Spartan")
    @Test
    public void testUserReadSpartan(){

        given().log().all().contentType(ContentType.JSON)
                .auth().basic("user","user")
                .when().get("/spartans").then()
                .log().ifValidationFails().statusCode(200);
    }
}
