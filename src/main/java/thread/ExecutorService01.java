package thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorService01 {

    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//
//        for(int i=0;i<10000;i++){
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(executorService.hashCode());
//                }
//            });
//        }

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread());
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread());
            }
        });

    }
}
