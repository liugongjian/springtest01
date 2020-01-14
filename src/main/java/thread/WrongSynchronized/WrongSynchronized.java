package thread.WrongSynchronized;

import java.util.concurrent.TimeUnit;


//程序中途改变了锁定的object，则相当于不用带锁的对象了
//所以不要用字符串常量作为锁定对象，
//比如s1 = "hello";s2="hello",那么synchronized（s1）和synchronized（s2）是一个锁，容易发生死锁
public class WrongSynchronized {

    Object o = new Object();
    void m(){
        while (true){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        WrongSynchronized t = new WrongSynchronized();
        new Thread(t::m,"t1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(t::m,"t2").start();

        t.o = new Object();

    }
}
