package com.cydeo.tests.day7;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class to explore more Junit5 features
 * Office doc : https://junit.org/junit5/docs/current/user-guide/#writing-tests
 * JUnit 5 have many annotations to either help to make test result simpler
 *  or to affect the way that test run
 *
 * @DisplayName  : Declares a custom display name for the test class or test method.
 * @Disabled     : to ignore running certain test for any reason you might have
 *    can be at class level or method level
 *    you can optionally add reason inside ("this is optional reason text")
 *    in order to see effect , run at class level
 *
 * Soft Assertion in Junit5 using assertAll Method and provide multiple assertion in ()-> assertEquals(..)
 *
 * all assertion methods has overloaded version that accept String message
 * to provide addition information in the console when something fail.
 *
 * @ParametrizedTest Denotes that a method is a parameterized test
 * a test that annotated with @ParametrizedTest instead of @Test
 * will need to provide where to provide the data known as source
 * there are 4 type of sources supported by JUnit 5
 *  - @valueSource
 *  - @CsvSource
 *  - @CsvFileSource
 *  - @MethodSource
 */

@DisplayName("Explore Junit5 features")
public class Junit5FeaturesTest {

    //import org.junit.jupiter.params.ParameterizedTest;
    //import org.junit.jupiter.params.provider.ValueSource;
    @ParameterizedTest  // mark this method as parametrized test
    @ValueSource(ints = {11, 44, 33,15, 16, 88} )        // provide value for each iteration of test
    public void testNumberMoreThan10( int num )  {
        // assuming we got these number from
        // some complicated previous code here 11, 44, 33,15, 16, 88
        // assert all these numbers are more than 10

        System.out.println("Number in this iteration " + num);

        assertTrue(num> 10 ,"hey it was not more than 10");

    }


    @DisplayName("Testing addition here")
    @Test
    public void testAdd(){
        assertEquals(10 , 5+5)  ;

        // Providing additional error message when assertion fail

        assertEquals(10 , 5+5, "Hey!!! It was not 10!!!!!!")  ;

        assertTrue(10> 9 ,"Salary was not greater than 130K ");

    }

    @Disabled("ignored until defect 101 fixed") // you can optionally ad reason
    @DisplayName("Testing subtraction functionality")
    @Test
    public void testSubtract(){
        assertEquals(9 , 10-1);
    }


    @DisplayName("Assert All together then one result")
    @Test
    public void multipleAssertions(){


//        // in this way , if second assertion fail , it will just stop ay line 46
//        // and we will not know the result of last assertion
//        // and that how the code supposed to work by design
//        assertEquals(10 , 5+5)  ;
//        assertEquals(111 , 5+6)  ;
//        assertEquals(20 , 5+15)  ;

        // but sometimes we want to assert all of them and see a final result
        // it accepts one or more Executable is functional interface
        // and it can be easily created using lambda expression
        // it has single method that accept no parameter and return nothing
        // so it can be in this way  () -> the assertion you want to run
        assertAll(
                ()->  assertEquals(10 , 5+5)  ,
                ()->  assertEquals(11 , 5+6) ,
                ()->  assertEquals(20 , 15+5)
        ) ;
        // this will enable us to continue to run the rest of the assertion
        // in case previous assertion has failed
        // it's also known as soft assertion in other term.


    }







}