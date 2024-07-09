package org.example.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class MockExample {

    @Mock
    List<Integer> list;

    @Mock
    Map<String,Integer> map;

    @Test
    public void runTest(){

        int listSize = 5;
        int keyAtZerothPosition = 32;

        Mockito.when(list.size()).thenReturn(5);
        Mockito.when(map.get("first")).thenReturn(32);


        Assertions.assertEquals(listSize,list.size());
        Assertions.assertEquals(keyAtZerothPosition,map.get("first"));




    }

}
