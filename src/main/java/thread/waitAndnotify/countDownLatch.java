package thread.waitAndnotify;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class countDownLatch {



    volatile List lists = new ArrayList();

    void add(Object o){
        lists.add(o);
    }

    int size(){
        return lists.size();
    }


    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(1);

        final Object lock = new Object();
        waitNnotify c = new waitNnotify();
        Thread t1 = new Thread(()->{
            for(int i=0;i<10;i++){
                    c.add(new Object());
                    System.out.println("add"+i);
                    if(c.size() == 5){
                        latch.countDown();
                    }

            }
        },"t1");

        Thread t2 = new Thread(()->{
            System.out.println("t2开始");
                if(c.size() != 5){
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2结束");
                lock.notify();//运行完了以后notify一下

        },"t2");

        t2.start();
        t1.start();
        try {
            t2.join();
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}
