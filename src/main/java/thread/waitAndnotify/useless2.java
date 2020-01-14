package thread.waitAndnotify;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class useless2 {

    volatile List lists = new ArrayList();

    void add(){

        int i = 0;
        while (i<10) {
            lists.add(i);
            i++;
            System.out.println("t1:"+i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    int size(){
        return lists.size();
    }


    public static void main(String[] args) {
        useless2 c = new useless2();
        Thread t1 = new Thread(c::add,"t1");
        Thread t2 = new Thread(()->{
            while (true){
                if(c.size() == 5){
                    break;
                }
            }
            System.out.println("t2结束");
        },"t2");
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
