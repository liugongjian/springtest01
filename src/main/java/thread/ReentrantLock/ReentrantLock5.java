package thread.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock5 extends Thread{
    private static ReentrantLock lock = new ReentrantLock(true);//true为公平锁，对比输出结果

    public void run(){
        for(int i=0;i<100;i++){
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+"获得锁");
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock5 rl = new ReentrantLock5();
        Thread th1 = new Thread(rl);
        Thread th2 = new Thread(rl);
        Thread th3 = new Thread(rl);
        th1.start();
        th2.start();
        th3.start();
    }
}
