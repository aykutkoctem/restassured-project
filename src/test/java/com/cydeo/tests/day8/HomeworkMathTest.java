package com.cydeo.tests.day8;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HomeworkMathTest {

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
    @ParameterizedTest
    @CsvFileSource(resources = "/math.csv",numLinesToSkip = 1)
    public void testMath(int num1, int num2, int expectedResult){

        System.out.println(num1 + " + " + num2 + " should be " + expectedResult );
        // just practice using hamcrest matcher
        assertThat( num1+num2 ,  equalTo(expectedResult) ) ;
    }

    /**
     * Another way of providing source for @ParamerizedTest is @MethodSource
     * It will allow you to provide the returned value from the static method with no param
     * as the source for your parameterized test
     */

    @ParameterizedTest
    @MethodSource("get10NumberList")
    public void testWithMethodSourceDDT(int num){
        System.out.println("num = " + num);
        // do some complicated assertion here
    }

    // what if the method is not in same class?
    // we provide  full.package.path#staticNethodName
    @ParameterizedTest
    @MethodSource("com.cydeo.day8.MethodSourceUtil#getSomeNames")
    public void testNamesWithMethodSourceDDT(String name){

        System.out.println("name = " + name);

    }


    // write a static method that return List<Integer> that contains 10 numbers
    public static List<Integer> get10NumberList(){
        // This list could have been the result of very long code here
        List <Integer> myList = Arrays.asList(11,2,34,45,15,6,37,48,59,10);
        return myList ;
    }



}