package com.cydeo.runner;

import org.junit.platform.suite.api.*;

@Suite
@SuiteDisplayName("Smoke Test")
//@SelectPackages("com.cydeo.tests.day8") // just to run day8 package
//@SelectPackages({"com.cydeo.tests.day8","com.cydeo.tests.day1"})
//@SelectClasses(BaseAuthTest.class)
@SelectPackages("com.cydeo.tests")
//@IncludeTags("smokeAll")
@IncludeTags("db")

//@ExcludeTags("smoke2")
public class TestRunner {


}