import java.security.cert.CollectionCertStoreParameters;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args)
    {
        System.out.println("Hello world!");
        Integer a[] = {1,10,5,2,67,5,5,4,16,8,23};

        List<Integer> collect3 = Arrays.stream(a).filter(i -> i % 2 == 0).sorted().collect(Collectors.toList());
        System.out.println(collect3);

        List<Integer> collect2 = Arrays.stream(a).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(collect2);

        Stream<Integer> a1 = Stream.of(a);
        List<Integer> collect = a1.map(i -> i * i).collect(Collectors.toList());
        System.out.println(collect);

        List<Integer> collect1 = Arrays.stream(a).map(i -> i * 4).collect(Collectors.toList());
        System.out.println(collect1);

    }

}