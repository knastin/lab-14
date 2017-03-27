package spb.herzen.ru;

import java.util.*;
import java.util.concurrent.*;
public class App {
    public static void main(String[] args) { 
        RejectedExecutionHandler rej = new RejectedExecutionHandlerImpl();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        Runnable thread;
        ThreadPoolExecutor executor = new TimingThreadPool(5,10,3,TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2),  threadFactory,rej);
        for (int i = 0; i < 20; i++) {
          thread = new FirstThread(i);
            executor.execute(thread);
        }
        executor.shutdown();
        while (!executor.isTerminated()){}
        System.out.println("Finished all threads");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("<----------Exercice №4------------------>");
        List<Long> synchrCollection = Collections.synchronizedList(new ArrayList<Long>());
        ExecutorService pool = Executors.newFixedThreadPool(15);
        long numb = 1;
        for (int i = 0; i < 25; i++) {
            pool.submit(new Callnumber(numb, synchrCollection));
            numb = numb + 10000;
        }
        pool.shutdown();
       while (!pool.isTerminated()){} 
        for (long number : synchrCollection) {
            System.out.println(number);
        }
        System.out.println("<----------Exercice №5------------------>");
        ConcurrentLinkedQueue<Long> conLinQue = new ConcurrentLinkedQueue<Long>();
        ExecutorService pool5 = Executors.newFixedThreadPool(4);
        long numb5 = 1;
        for (int i = 0; i < 25; i++) {
            pool5.submit(new Callnumberexc5(numb5, conLinQue));
            numb5 = numb5 + 10000;
        }
        pool5.shutdown();
        while (!pool5.isTerminated()){}
        for (long number : conLinQue   ) {
            System.out.println(number);
        }*/
        ConcurrentHashMap<String, Long> hashMap = new ConcurrentHashMap<String, Long>();
        String[] keys = {"Petya0", "Petya1", "Petya2", "Petya3", "Petya4", "Petya5", "Petya6", "Petya7", "Petya8", "Petya9"};
        for (String key : keys) {
            hashMap.put(key, (long) (Math.random() * 99));
        }
        for (String key : keys) {
            System.out.println(key);
        }

        ExecutorService pool6 = Executors.newFixedThreadPool(8);
        for (int i = 0; i <= 30; i++) {
            pool6.submit(() -> {
                        for (int y = 0; y <= 20; y++) {
                            for (Long value : hashMap.values()) {
                                if (value % 5 == 0) {
                                    value = value / 5;
                                } else {value = value + 7;};

                            }
                        }
                    }
            );
        }
        pool6.shutdown();
        while (!pool6.isTerminated()){}

        for (String key : hashMap.keySet()){
            System.out.println(key+" "+hashMap.get(key));
        }


    }
}
