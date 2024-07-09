//package org.example.services;
//
//import org.junit.*;
//
//import java.util.Date;
//
//public class CalculatorServiceTest
//{
//    int count;
//
//    @BeforeClass
//    public  static  void init(){
//
//        System.out.println("Before all test cases:");
//        System.out.println("Started test"+new Date());
//
//    }
//
//    @Before
//    public void beforeEach(){
//        System.out.println("Before each test case");
//        count=0;
//
//    }
//
//    //test method of addTwoNumbers
//    @Test(timeout = 2000)
//    public void addTwoNumbersTest() throws InterruptedException {
//        for (int i=0;i<20;i++)
//        {
//            count+=i;
//        }
//        Thread.sleep(3000);
//
//        System.out.println("Count value in first test "+count);
//        System.out.println("test for addTwoNumbersTest");
//        int result = CalculatorService.addTwoNumbers(10,30);
//        int expected = 40;
//        Assert.assertEquals(expected,result);
//
//    }
//
//    @Test
//    public void productTwoNumbersTest(){
//        int result = CalculatorService.productTwoNumbers(10, 4);
//        int expected = 40;
//        System.out.println("test case for product of two numbers");
//        Assert.assertEquals(expected,result);
//
//    }
//
//    @Test
//    public void divideTwoNumbersTest(){
//
//        double result = CalculatorService.divideTwoNumbers(4, 2);
//        double expected = 2;
//        Assert.assertEquals(result,expected,3);
//
//    }
//
//    @Test
//    public void sumAnyNumberTest()
//    {
//        for (int i=0;i<10;i++)
//        {
//            count+=i;
//        }
//        System.out.println("Count value in Second test "+count);
//        System.out.println("test for sumAnyNumbersTest");
//        int result = CalculatorService.sumAnyNumbers(1, 2, 3, 4, 5);
//        int expected = 15;
//        Assert.assertEquals(expected,result);
//
//    }
//
//    @AfterClass
//    public static void cleanup(){
//        System.out.println("After all test cases");
//        System.out.println("End Test Cases"+new Date());
//
//    }
//
//
//}
