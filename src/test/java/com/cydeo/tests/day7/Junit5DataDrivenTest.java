package com.cydeo.tests.day7;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.* ;
public class Junit5DataDrivenTest {


    @ParameterizedTest
    @ValueSource(strings={"Furkan","Abbos  ", "Yevheniia","Shazia","Tugba  ","Mohamed","Kimberly"})
    public void testNameLength(String eachName){

        System.out.println("eachName = " + eachName);
assertTrue(eachName.length()>5);
    }


    @ParameterizedTest
    // resources to define where is the csv file under src/test/resources
    // numLinesToSkip = 1 to skip the header row if you have one in csv file
    @CsvFileSource(resources = "/people.csv" , numLinesToSkip = 1)
    public void testPerson(String nameParam, String genderParam, long phoneParam){

        System.out.println("nameParam = " + nameParam);
        System.out.println("genderParam = " + genderParam);
        System.out.println("phoneParam = " + phoneParam);

    }
}
