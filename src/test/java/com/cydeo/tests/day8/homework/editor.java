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

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

public class editor extends SpartanAuthTestBase {

    /**
     * As homework
     * Create 3 separate class AdminAuthTest , EditorAuthTest , UserAuthTest
     * Write separate tests for below requirement to do Role Base access control test
     *      * admin :  create , read , update , delete
     *      * editor:  create , read , update
     *      * user  :  read
     */

    @DisplayName("Editor should be able to create")
    @Test
    public void CreateSpartans(){
        Map<String,Object> newSpartan3=new HashMap<>();

        newSpartan3.put("name","Luicano");
        newSpartan3.put("gender", "Male");
        newSpartan3.put("phone",653335222273l);

        given().log().all().contentType(ContentType.JSON)
                .auth().basic("editor","editor")
                .body(newSpartan3).when().post("/spartans")
                .then().log().ifValidationFails().statusCode(201);

    }
    @DisplayName("Editor should be able to read a Spartan")
    @Test
    public void testEditorReadSpartan() {
given().log().all().contentType(ContentType.JSON)
        .auth().basic("editor","editor").when()
        .get("/spartans").then().log().ifValidationFails()
        .statusCode(200);

    }

    @DisplayName("Editor should be able to update a Spartan")
    @Test
    public void testEditorUpdateSpartan() {
        Map<String,Object> newSpartan4=new HashMap<>();

        newSpartan4.put("name","Stephen");
        newSpartan4.put("gender", "Female");
        newSpartan4.put("phone",654852330073l);

        given().log().all().contentType(ContentType.JSON)
                .auth().basic("editor","editor")
                .pathParam("id",734).
                body(newSpartan4)
                .when().put("/spartans/{id}").then()
                .log().ifValidationFails().statusCode(204);

    }


}

