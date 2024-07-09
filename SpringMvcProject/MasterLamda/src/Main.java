import lamdaexpt.LengthInter;
import lamdaexpt.MyInter;
import lamdaexpt.MyInterImpl;
import lamdaexpt.SumInter;

public class Main {
    public static void main(String[] args)
    {
        // create the seperate class and implement functional interface
        System.out.println("System starts");
//        MyInter myInter = new MyInterImpl();
//        myInter.sayhello();

        // anonymous class for implementing interface

//        MyInter m1 = new MyInter() {
//            @Override
//            public void sayhello() {
//
//                System.out.println("first annonymous class");
//
//            }
//        };
//
//        m1.sayhello();
//        MyInter m2 = new MyInter()
//        {
//            @Override
//            public void sayhello()
//            {
//                System.out.println("second annonymous class");
//            }
//        };
//    m2.sayhello();

    //using interface with lamda expression

        MyInter i = ()-> System.out.println("hi all this is first lamda expression");
        i.sayhello();

        MyInter i1 = ()-> System.out.println("this is second lamda expression");
        i1.sayhello();

        SumInter sumInter = (a,b)-> a+b;
        System.out.println(sumInter.sum(2,3));
        System.out.println(sumInter.sum(1,6));

        LengthInter lengthInter = (str)->{return str.length();};
        System.out.println("length od string is "+lengthInter.getLength("Amrit"));
        System.out.println(lengthInter.getLength("Jaiaswak"));


    }

}