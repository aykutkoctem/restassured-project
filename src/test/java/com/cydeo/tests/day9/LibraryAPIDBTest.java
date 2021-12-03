package com.cydeo.tests.day9;

import com.cydeo.utility.DB_Util;
import com.cydeo.utility.LibraryAPI_BaseTest;
import com.cydeo.utility.LibraryAPI_Util;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class LibraryAPIDBTest extends LibraryAPI_BaseTest {


@Test
    public void testDB(){


    DB_Util.runQuery("SELECT *FROM books");
   // DB_Util.displayAllData();

}
    @DisplayName("Test GET /Dashboard response match database")
    @Test
    public void testDashboardStatsMatchDB(){

        DB_Util.runQuery("SELECT count(*) FROM books") ;
        DB_Util.displayAllData();
        String expectedBookCount =  DB_Util.getCellValue(1,1) ;

        DB_Util.runQuery("select count(*) from users");
        DB_Util.displayAllData();
        String expectedUserCount = DB_Util.getCellValue(1,1);

        DB_Util.runQuery("select count(*) from book_borrow where is_returned=0");
        DB_Util.displayAllData();
        String expectedBorrowedBookCount = DB_Util.getCellValue(1,1) ;

         //create a method a return Map<String,String> to returned above data
        //GET / dashboard_stats
        given().log().all()
                .header("X-LIBRARY-TOKEN", LibraryAPI_Util.getToken())
                .when()
                .get("/dashboard_stats")
                .then().log().all().
                statusCode(200)
                .body("book_count",is(expectedBookCount))
                .body("users",is(expectedUserCount))
                .body("borrowed_books",is(expectedBorrowedBookCount));


    }


}
