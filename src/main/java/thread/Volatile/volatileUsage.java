package thread.Volatile;

import java.util.concurrent.TimeUnit;

public  class  volatileUsage {
    volatile boolean running = true;

    Integer i = 1000000000;

    void m(){

        System.out.println("m start");
        while (running){
            //当sleep之后，该线程可能会cpu中断，就会把cpu cache中的和内存中的数据进行同步，但这种方式是不可控的
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        volatileUsage t = new volatileUsage();
        new Thread(t::m,"t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        t.running = false;
    }
}
