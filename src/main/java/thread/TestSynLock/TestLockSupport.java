package thread.TestSynLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class TestLockSupport {

    static Thread t1=null ,t2 = null;

    public static void main(String[] args) {


        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();


        t1 = new Thread(()->{
            for(char c:aI){
                System.out.println(c);
                LockSupport.unpark(t2);
                LockSupport.park();//T1阻塞
            }
        },"t1");



        t2 = new Thread(()->{
            for(char c:aC){
                LockSupport.park();
                System.out.println(c);
                LockSupport.unpark(t1);
            }
        },"t2");

        t1.start();

//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        t2.start();

    }
}
