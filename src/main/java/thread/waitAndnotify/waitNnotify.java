package thread.waitAndnotify;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class waitNnotify {



    volatile List lists = new ArrayList();

    void add(Object o){
        lists.add(o);
    }

    int size(){
        return lists.size();
    }


    public static void main(String[] args) {

        final Object lock = new Object();
        waitNnotify c = new waitNnotify();
        Thread t1 = new Thread(()->{
            for(int i=0;i<10;i++){
                synchronized (lock){
                    c.add(new Object());
                    System.out.println("add"+i);
                    if(c.size() == 5){
                        try {
                            lock.notify();//notify不会释放锁，所以只有这句的时候t1还是不会执行,要加上下面的wait，释放锁
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                    }
                }
            }
        },"t1");

        Thread t2 = new Thread(()->{
            System.out.println("t2开始");
            synchronized (lock) {
                if(c.size() != 5){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2结束");
                lock.notify();//运行完了以后notify一下
            }
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
