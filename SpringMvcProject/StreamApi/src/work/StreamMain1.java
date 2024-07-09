package work;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMain1 {
    public static void main(String[] args)
    {

//        List<Integer> list2 = new ArrayList<>();
//        list2.add(20);
//        list2.add(2);
//        list2.add(1);
//        list2.add(5);
//        list2.add(4);
//        list2.add(7);
//        list2.add(8);
//        System.out.println(list2);
//        list2.set(1,120);
//        list2.add(230);
//        System.out.println(list2);


//        List<Integer> list3 = Arrays.asList(10,40,23,12,6,8,5);
//        System.out.println(list3);
//       // list3.set(2,230);
//       // list3.add(200);
//        System.out.println(list3.get(2));
//        System.out.println(list3);


        //immutable list
//        List<Integer> list = List.of(12,2,3,5,6,7,2,3);
//        list.add(234);
//        System.out.println(list);

        // without StreamApi - Boiler Plate CODE
       List<Integer> list1 = List.of(6, 2, 1, 8, 9, 3, 10, 4);
//        List<Integer> evenlist = new ArrayList<>();
//        for(Integer i:list1)
//        {
//            if (i%2==0)
//            {
//                evenlist.add(i);
//
//            }
//        }

//        System.out.println(list1);
//        System.out.println(evenlist);

        // with stream
        Stream<Integer> stream = list1.stream();
        List<Integer> newList = stream.filter(i -> i % 2 == 0).collect(Collectors.toList());
        System.out.println(newList);

//        List<Integer> newList = list1.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
//        System.out.println(newList);

//        List<Integer> newList2 = list1.stream().filter(i -> i > 8).collect(Collectors.toList());

//        System.out.println(newList2);

    }
}
