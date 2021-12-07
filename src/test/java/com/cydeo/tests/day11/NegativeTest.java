package com.cydeo.tests.day11;

import com.cydeo.pojo.Spartan;
import com.cydeo.utility.SpartanTestBase;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class NegativeTest extends SpartanTestBase {

    /**
     * Post /spartans request json payload have below requirements
     * name  : 2-15 chars
     * gender : Male or Female
     * phone  : 10 - 13 digit
     *
     * Knowing that we already tested all positive scenario
     * make sure it generates error with proper json body to reflect the error
     * according to the negative test data you provided.
     *
     * for example :
     * {
     *     "name":"A",
     *     "gender":"Male",
     *     "phone":1231231231
     * }
     * we should expect 400 response
     * {
     *     "message": "Invalid Input!",
     *     "errorCount": 1,
     *     "errors": [
     *         {
     *             "errorField": "name",
     *             "rejectedValue": "A",
     *             "reason": "name should be at least 2 character and max 15 character"
     *         }
     *     ]
     * }
     * error count should be 1
     * "message": "Invalid Input!",
     * "errorField": "name"
     *  from the response
     */
@DisplayName("Post / spartans invalid payload should return error")
    @Test
    public void testAdd1DataNegativeScenario(){

    // prepare test body
    Spartan invalidBody = new Spartan("A","Male",1231231231L) ;

    given()
            .log().all()
            .contentType(ContentType.JSON)
            .body(invalidBody).
            when()
            .post("/spartans").
            then()
            .log().all()
            .statusCode( 400 )
            .body("message", is("Invalid Input!") )
            .body("errorCount", equalTo(1) )
            .body("errors[0].errorField" , is("name"))
    ;
}
@ParameterizedTest
@CsvFileSource(resources = "/negativePostData.csv",numLinesToSkip = 1)
public void testNegativePostPayload(String nameParam,String genderParam,long phoneParam,int expectedCount){

    // prepare test body
    Spartan invalidBody = new Spartan(nameParam,genderParam,phoneParam) ;
    given()
            .log().all()
            .contentType(ContentType.JSON)
            .body(invalidBody).
            when()
            .post("/spartans").
            then()
            .log().all()
            .statusCode( 400 )
            .body("message", is("Invalid Input!") )
            .body("errorCount", equalTo(expectedCount) );

}
@Disabled("Know issue = Defect110")
@DisplayName("Test Phone upper boundary in PUT /spartans/{id}")
@Test
    public void testRequestNegativeScenario(){


int lastID=get("/spartans").path("id[-1]");
    System.out.println("lastID = " + lastID);

    Faker faker=new Faker();
    long phone15Digit=faker.number().randomNumber(15,true);
    System.out.println("phone15Digit = " + phone15Digit);

    Spartan bodyWithInvalidPhone = new Spartan("Alex", "Male" , phone15Digit) ;

    given()
            .log().uri()  // just to see what id i used for logging purpose
            .pathParam("id" , lastID)
            .contentType(ContentType.JSON)
            .body(bodyWithInvalidPhone).
            when()
            .put("/spartans/{id}").
            then()
            .log().ifValidationFails()
            .statusCode(500) ;
}
}

