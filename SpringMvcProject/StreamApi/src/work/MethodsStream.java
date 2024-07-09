package work;

import java.util.List;
import java.util.stream.Collectors;

public class MethodsStream {
    public static void main(String[] args) {
//
//        List<String> names = List.of("Amar","AJAK","Komal","durgesh","Pnadit");
//        List<String> a = names.stream().filter(e -> e.startsWith("A")).collect(Collectors.toList());
//        for (String e:a) {
//            System.out.println(e);
//        }
//
//        System.out.println(a);
//
//        a.forEach(t-> System.out.println(t));
//
//        names.stream().forEach(e->{
//            System.out.println(e);
//
//        });

        List<Integer> numbers = List.of(2,3,6,5,1,8,4);
        List<Integer> collect = numbers.stream().map(i -> i * 5).collect(Collectors.toList());
        System.out.println(collect);
//        List<Integer> newNumbers = numbers.stream().map(i -> i * i).collect(Collectors.toList());
//        System.out.println(newNumbers);


//        numbers.stream().forEach(System.out::println);
//
//        numbers.stream().sorted().forEach(System.out::println);
//
//        Integer integer = numbers.stream().min((x, y) -> x.compareTo(y)).get();
//        System.out.println("Min"+integer);
//
//        Integer integer1 = numbers.stream().max((x, y) -> x.compareTo(y)).get();
//        System.out.println("Max is"+integer1);


    }
}
