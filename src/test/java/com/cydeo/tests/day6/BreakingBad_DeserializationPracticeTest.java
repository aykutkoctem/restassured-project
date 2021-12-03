package com.cydeo.tests.day6;
import com.cydeo.pojo.Character;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class BreakingBad_DeserializationPracticeTest {

    // https://breakingbadapi.com/api/characters

    @BeforeAll
    public static void setUp(){
        baseURI="https://breakingbadapi.com";
        basePath="/api";
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @Test
    public void testCharacters(){
        JsonPath jp=get("/characters").prettyPeek().jsonPath();

        Character c1=jp.getObject("[0]",Character.class);
        System.out.println("c1 = " + c1);

        List<Character> allCharacters=jp.getList("",Character.class);
        System.out.println("allCharacters = " + allCharacters);
List<String> allCharacterHasA1=new ArrayList<>();
        for (Character eachCharacter : allCharacters) {
            if( eachCharacter.getAppearance().contains("1")){
                allCharacterHasA1.add(eachCharacter.getName());

            }
        }
        System.out.println("allCharacterHasA1 = " + allCharacterHasA1);

    }

}
// Java Practice HOMEWORK :
// find out the character names appearance count is exactly 1
// find out the name of DEA Agent