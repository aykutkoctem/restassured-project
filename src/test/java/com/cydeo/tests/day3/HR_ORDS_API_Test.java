package com.cydeo.tests.day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class HR_ORDS_API_Test {

    @BeforeAll
    public static void setup(){
        baseURI  = "http://54.236.150.168:1000";
        basePath = "/ords/hr/";
    }

    @AfterAll
    public static void teardown(){
        reset();

    }

    @Test
    public void testGetAllJobs(){
        //Get Jobs

        Response response= given().log().all().when().get("/jobs");
        response.prettyPrint();

        Assertions.assertEquals(200,response.statusCode());

        Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());

        int countValue=response.path("count");
        Assertions.assertEquals(19,countValue);
        String secondJobId=response.path("items[1].job_id");
        System.out.println("secondJobId = " + secondJobId);

        int fourthMinSalary=response.path("items[3].min_salary");
        System.out.println("fourthMinSalary = " + fourthMinSalary);

       List<String> jobTitles=response.path("items.job_title");
        System.out.println("jobTitle = " + jobTitles);
        System.out.println(jobTitles.size());


    }
    @Test
    public void testJobsWithQueryParam(){

        Response response= given().log().all().queryParam("limit",5).when().get("/jobs");
        response.prettyPrint();
        int actualCount=response.path("count");
        Assertions.assertEquals(5,actualCount);

        String lastJobId=response.path("items[4].job_id");
        System.out.println("lastJobId = " + lastJobId);
        String lastJobIdDiffirentMethod=response.path("items[-1].job_id");
        System.out.println("lastJobIdDiffirentMethod = " + lastJobIdDiffirentMethod);
        Assertions.assertEquals(lastJobId,"AD_VP");


    }


    @Test
    public void testSingleJobWithParam(){
        Response response=given().log().all().pathParam("jobs_id","AD_VP").when().get("/jobs/{jobs_id}")
                .prettyPeek();


        String actualJobTitle=response.path("job_title");
        Assertions.assertEquals("Administration Vice President",actualJobTitle);


    }
}
