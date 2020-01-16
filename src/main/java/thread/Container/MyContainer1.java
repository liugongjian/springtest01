package thread.Container;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class MyContainer1<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10;
    private int count = 0;

    public synchronized void put(T t){
        while(lists.size() == MAX){//为什么用while而不是if：当被叫醒的时候，会再次进行校验，防止容器又被其他线程塞满，而导致的溢出,wait()99%的情况是跟while一起用的
            try {
                this.wait();//wait会释放锁，让其它线程进入
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        lists.add(t);
        ++count;
        this.notifyAll();//通知消费者线程进行消费,如果用notify()，那么，如果唤醒的线程是生产者，那么就会wait()，而且还没有调用下面的notify()，那么所有的线程就会一直wait，没有线程会再醒过来
    }

    public synchronized T get(){
        T t = null;
        while(lists.size() == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t=lists.removeFirst();
        count--;
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        MyContainer1<String> c = new MyContainer1<>();
        for(int i=0;i<10;i++){
            new Thread(()->{
                for(int j=0;j<10000;j++) System.out.println(c.get());
            },"consumer"+i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0;i<10;i++){
            new Thread(()->{
                for(int j=0;j<10000;j++) c.put(Thread.currentThread().getName()+" "+j);
            },"producer"+i).start();
        }

    }

}
