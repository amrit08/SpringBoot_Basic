package work;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamObject {
    public static void main(String[] args)
    {
         //blank stream
//        Stream<Object> emptyStream = Stream.empty();
//        emptyStream.forEach(e->
//        {
//            System.out.println(e);
//        });

       //  stream from array
//        String names[] = {"Amrit","Dayan","GUDDI","AMAR"};
//
//        List<Integer> list = List.of(12,2,3,45,6,4,32,1);
//        Stream<List<Integer>> list1 = Stream.of(list);
//      list1.forEach(t-> System.out.print(t));

//        Stream<String> stream1 = Stream.of(names);
//        stream1.forEach(e->
//        {
//            System.out.println(e);
//        });

        //stream from Builder pattern

      //  Stream<Object> streamBuilder = Stream.builder().build();

      //  from arrays directly

        IntStream stream = Arrays.stream(new int[]{2, 4, 8, 1, 4, 7});

        stream.forEach(e->{
            System.out.println(e);

        });

        IntStream stream1 = Arrays.stream(new int[]{3, 5, 6, 3, 22, 56, 76});

        // from list , set

        List<Integer> list2 = new ArrayList<>();
        list2.add(20);
        list2.add(2);
        list2.add(1);
        list2.add(5);
        list2.add(4);
        list2.add(7);
        list2.add(8);

        Stream<Integer> stream2 = list2.stream();
        stream2.forEach(e->{
            System.out.println(e);

        });

    }

}
