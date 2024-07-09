package org.example.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;
import java.util.List;

public class AssertExample
{

    @Test
    public void tester()
    {
        System.out.println("Testing some assertions methods");

//        float actual=12;
//        Float expected=12.0f;
       // Assertions.assertEquals(expected,actual);
//
//        int []actualIntArray={1,2,3,4,5};
//        int []expectedIntArray={1,2,3,4,5,};
//        Assertions.assertArrayEquals(expectedIntArray,actualIntArray);

//        String actualName="rahul";
//
//        String expectedName="rahul";
//
//        Assertions.assertSame(actualName,expectedName);

//        boolean value=true;
//        Assertions.assertTrue(value);
//
//        List<Integer> list1 = Arrays.asList(1,2,3,4,5,6);
//        List<Integer> list2 = Arrays.asList(1,2,3,5,4,6);
//
//        Assertions.assertIterableEquals(list2,list1);

        Assertions.assertThrows(RuntimeException.class,()->{
            // System.out.println("this is testing executable");
            throw new RuntimeException("This is testing exception");
        });

    }

}
