package com.cydeo.tests.day6;

import com.cydeo.pojo.Article;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class NewsAPI_DeserializationTest {

    /**
     * GET https://newsapi.org/v2/top-headlines?country=us
     * Header :  Authorization = Bearer cbeb9b369672417ba25af35e95edbb69
     */
    @BeforeAll
    public static void setUp(){
        baseURI="https://newsapi.org";
        basePath="/v2";
    }
    @AfterAll
    public static void teardown(){
        reset();
    }
@Test
    public void testNews(){
    JsonPath jp=given().log().all()
            .queryParam("country","us").
            header("Authorization","Bearer cbeb9b369672417ba25af35e95edbb69").when().get("/top-headlines")
            .prettyPeek().jsonPath();

    Article a1=jp.getObject("articles[0]",Article.class);
    System.out.println("a1 = " + a1);


    System.out.println("a1.getSource().get(\"id\") = " + a1.getSource().get("id"));

    List<Article> allArticles=jp.getList("articles",Article.class);
    for (Article eachArticles : allArticles) {
        if (eachArticles.getSource().get("id")!=null){
            System.out.println(eachArticles.getAuthor());
        }
    }
}

}