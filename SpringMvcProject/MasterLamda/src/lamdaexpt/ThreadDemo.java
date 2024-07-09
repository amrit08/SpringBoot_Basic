package lamdaexpt;

public class ThreadDemo {

    public static void main(String[] args) {

        Runnable thread1 = ()->{

            for(int i =0;i<=10;i++)
            {

                System.out.println("vlaue of i is "+i);
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {

                    throw new RuntimeException(e);
                }
            }

        };

        Thread t = new Thread(thread1);
        t.setName("AMrit");
        t.start();

        Runnable thread2 = ()->{
            for(int i =0;i<=10;i++)
            {
                System.out.println("2*"+i+"="+(2*i));
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }

        };
        Thread t2 = new Thread(thread2);
        t2.setName("lajla");
        t2.start();




    }
}
