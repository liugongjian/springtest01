package thread.Atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class AtomicI {

    //ReentrantLock
    AtomicInteger count = new AtomicInteger(0);

    /*synchronized*/ void m(){
        count.incrementAndGet();//count++
    }

    public static void main(String[] args) {
        AtomicI t = new AtomicI();
        List<Thread> threads = new ArrayList<Thread>();

        for(int i=0;i<10000;i++){
            threads.add(new Thread(t::m,"thread-"+i));
        }
        threads.forEach((o)->o.start());

        threads.forEach((o)->{
            try {
                o.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        System.out.println(t.count);
    }
}
