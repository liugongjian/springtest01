package thread;

import java.util.concurrent.*;

public class Thread01 extends Thread {

    public void run(){
        System.out.println("haha");
    }


    Callable c = new Callable() {
        @Override
        public Object call() throws Exception {
            return "haha";
        }
    };
    FutureTask f = new FutureTask(c);



    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread01();
        t.start();
        t.join();
       // Thread.sleep(10000);
    }
}
