package org.example.services;

import org.junit.jupiter.api.*;

import javax.sound.midi.Soundbank;

public class CalculatorServiceTestJUnit5
{

    @BeforeAll
    public static void init(){

        System.out.println("this method exceutes before all test cases");
        System.out.println("This is single time logic");

    }
    @AfterAll
    public static void cleanUp(){
        System.out.println("After all test cases this will exceutr");


    }

    @BeforeEach
    public void beforeEachTestCase(){
        System.out.println("BeforeEachTestCase");

    }

    @AfterEach
    public void afterEachTestCase()
    {
        System.out.println("AfterEachTestCase");

    }


    @Test
    @DisplayName("This is custom name")
    public void addTwoNumbersTest()
    {
        System.out.println("First Test Case");
        int actualResult = CalculatorService.addTwoNumbers(12, 13);
        int expectedResult = 25;
        Assertions.assertEquals(expectedResult,actualResult,"Not Matched");
    }

    @Test
    //@Disabled
    public void addAnyNumbersTest()
    {
        System.out.println("Second Test Case");
        int result = CalculatorService.sumAnyNumbers(1, 2, 3, 4, 5);
        int expectedResult=15;
        Assertions.assertEquals(expectedResult,result);

    }

}
