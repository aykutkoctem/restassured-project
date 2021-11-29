package com.cydeo.day3;
import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class SpartanMethodChainTest extends SpartanTestBase {

    @Test
    public void getOneSpartanTest(){

        // in one shot ,
        // send request to GET /spartans/{id}  with id of 1
        // log everything
        // ask for json response for the same of practice
        // verify statusCode is 200
        // contentType is json
        // json body  : id is 1 , name OliverTheKing,
        given()
                .log().all()
                .pathParam("id",1)
                .accept( ContentType.JSON ).
                when()
                .get("/spartans/{id}").
                then()  // this is where assertions start
                .statusCode(  is(200)   )  // asserting status code 200
                //.header("Content-Type" , "application/json") // asserting header is application json
//                .header("Content-Type" ,  ContentType.JSON.toString()  )
                .contentType(  ContentType.JSON  )  // this does same thing as above lines
                .body( "id" ,  is(1)    )
                .body("name" , is("API POST")    )
        ;

    }
    @Test
    public void testSearch(){

        // http://54.236.150.168:8000/api/spartans/search?nameContains=Ea&gender=Male
        // verify status code is 200
        // content type is json
        //  "totalElement": 3
        //  jsonArray hasSize of 3
        //  all names  has item Sean
        //  every gender is Male
        //  every name should contain ignoring case ea
        given()
                .log().all() // logging everything about request
                .queryParam("nameContains","Ea")
                .queryParam("gender","Male").
                when()
                .get("/spartans/search").
                then()
                .log().all() // this is logging everything about response
                .statusCode( 200 )
                .contentType( ContentType.JSON )
                .body("totalElement", is(24)   )
                .body("content", hasSize(24) )
                .body("content.name", hasItem("Seadra")  )
                .body("content.gender", everyItem(   is("Male")    )     )
                .body("content.name", everyItem( containsStringIgnoringCase("ea")    ) )

        ;
    }
}
