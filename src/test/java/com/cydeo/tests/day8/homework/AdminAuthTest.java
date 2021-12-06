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

public class AdminAuthTest extends SpartanAuthTestBase {

    /**
     * As homework
     * Create 3 separate class AdminAuthTest , EditorAuthTest , UserAuthTest
     * Write separate tests for below requirement to do Role Base access control test
     *      * admin :  create , read , update , delete
     *      * editor:  create , read , update
     *      * user  :  read
     */

static int spartanId1;
    @DisplayName("Admin should be able to create")
    @Test
    public void CreateSpartans(){

        Map<String,Object> newSpartan=new HashMap<>();

        newSpartan.put("name","Alex");
        newSpartan.put("gender", "Male");
        newSpartan.put("phone",65485222273l);

     given().log().all().contentType(ContentType.JSON)
                .auth().basic("admin","admin")
                .body(newSpartan)
                .when().post("/spartans")
                .then().log().ifValidationFails()
                .statusCode(201);

        System.out.println("spartanId = " + spartanId1);
    }
    @DisplayName("Admin should be able to read a Spartan")
    @Test
    public void testAdminReadSpartan() {

given().log().all().contentType(ContentType.JSON)
        .auth().basic("admin","admin")
        .when().get("/spartans").then().log().ifValidationFails()
        .statusCode(200);

    }

    @DisplayName("Admin should be able to update a Spartan")
    @Test
    public void testAdminUpdateSpartan() {
        Map<String,Object> newSpartan2=new HashMap<>();

        newSpartan2.put("name","Aykut");
        newSpartan2.put("gender", "Female");
        newSpartan2.put("phone",654852333273l);
        given().log().all()
                .contentType(ContentType.JSON).auth().basic("admin","admin").
                pathParam("id",909)
                .body(newSpartan2)
                .when().put("/spartans/{id}").then().log().ifValidationFails()
                .statusCode(204);


    }

    @DisplayName("Admin should be able to deletea Spartan")
    @Test
    public void testAdminUpdateDelete() {

        given().log().all().auth().basic("admin","admin")
                .pathParam("id",909)
                .delete("/spartans/{id}").prettyPeek().then().statusCode(204);


    }
}
