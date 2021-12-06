package com.cydeo.tests.day7;
import com.cydeo.pojo.Places;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
@Tag("HOMEWORK")
public class Test_Homework {


    @BeforeAll
    public static void setup() {
        baseURI = "https://api.zippopotam.us";
        basePath = "/us";
    }

    @AfterAll
    public static void teardown() {
        reset();
    }

    /**
     *   Homework :
     *   in one test
     *   send request to GET https://api.zippopotam.us/us/va/fairfax
     *   log request all parts
     *   use va and fairfax as path variables with name state / city
     *   send get request verify
     *   status code 200 , json format
     **/
    @Test
    public void testZipToCity(){}


@Test
    public void homework1(){

    given()
            .log().all()
            .pathParam("state","va")
            .pathParam("city","fairfax").

            when()
            .get("/{state}/{city}").
            then()
            .log().all()
            .statusCode(200)
            .contentType(ContentType.JSON);





}
  /**
     *   in another test
     *   send same request and store the response object
     *   get JsonPath object from the response
     *   print last place name
     *   print all zip codes after storing it into the list
     *   create a pojo called Place to represent place json object
     *      with these specific fields :
     *      - name as String
     *      - postCode as int
     *      - latitude as float
     *      - longitude as float
     *      {
     *             "place name": "Fairfax",
     *             "longitude": "-77.3242",
     *             "post code": "22030",
     *             "latitude": "38.8458"
     *         }
     *  de-serialize the first response into Place pojo and print it out
     *  save all the place json array into List<Place> and print it out.
     *
     */
@Test
    public void homework3(){

    Response response=given().log().all()
            .when().get("/va/fairfax")
            .prettyPeek();

    JsonPath jp = response.jsonPath();
    Places c1=jp.getObject("places[-1]",Places.class);

    System.out.println("c1.getPlaceName() = " + c1.getPlaceName());
    List<Places> allPlaces=jp.getList("places",Places.class);

    List<Integer> allZip=new ArrayList<>();
   // List<String>allPlaces=new ArrayList<>();
    for (Places eachZipCode : allPlaces) {
        allZip.add( eachZipCode.getPostCode());
    //    System.out.println("allZip = " + allZip);
    }
    System.out.println("allZip = " + allZip);


}

    @ParameterizedTest
    @ValueSource(ints = {22030 , 10019 , 12345 , 10000, 19152})
    public void testZipToCityDDT (int zipParam) {

        System.out.println("zipParam = " + zipParam);

        given()
                .log().uri()
                .pathParam("zip",zipParam)
                .when()
                .get("/{zip}")
                .then().statusCode(200);
    }

    /**
     * Create a Parameterized Test to check
     * using given zipcodes 22030 , 10019 , 12345 , 10000, 19152
     * send out request to
     * GET https://api.zippopotam.us/us/{zip}
     * test the status code is 200
     */


    @ParameterizedTest
    @CsvFileSource(resources= "/state_city.csv" , numLinesToSkip =1 )

    public void testHomework4(String state, String city){
        given()
                .log().all()
                .pathParam("state",state)
                .pathParam("city",city).

                when()
                .get("/{state}/{city}").
                then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON);

    }

    @ParameterizedTest
    @CsvFileSource(resources= "/math.csv" , numLinesToSkip =1 )

    public void testHomework5(int num1, int num2,int expected){

        assertEquals(num1+num2 , expected)  ;


    }
/**
     *  Create a file called state_city.csv under resources folder
     *  it has two columns  state , city
     *  add some valid data
     *
     *  send request to GET https://api.zippopotam.us/us/{state}/{city}
     *      *   log request uri
     *      *   use state and city as path variables with name state / city
     *      *   for actual value of path params get it from csv file
     *      *   send get request verify
     *      *   status code 200 , json format
     *
     */
     }

/**
 * ### Task last
 *
 * 1. Create a csv file called `math.csv` under `resource` folder
 * 2. add 3 columns `num1` ,   `num2` , `expectedResult`
 * 3. add valid data for addition num1 + num2 = expectedResult
 * 4. create a `@ParameterizedTest` with above `CsvFileSource` , skip the header
 * 5. assert addition num1 + num2 = expectedResult
 * 6. make sure to change the number and see failed tests.
 */





