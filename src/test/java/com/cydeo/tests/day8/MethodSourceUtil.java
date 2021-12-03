package com.cydeo.tests.day8;

import java.util.Arrays;
import java.util.List;

/**
 * The only purpose of this class is
 * demonstrating providing the method source
 * for a parameterized test and the method exist outside the Test class
 */
public class MethodSourceUtil {

    // create a static method that return list of names
    // so we can use the returned value as data source for our parameterized test
    public static List<String> getSomeNames(){

        return Arrays.asList("Furkan", "Abbos", "Yevheniia" , "Shazia" ,"Tugba" , "Mohamed" , "Kimberley");

    }


}
