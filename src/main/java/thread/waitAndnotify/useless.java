package thread.waitAndnotify;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class useless {

    volatile List lists = new ArrayList();

    void add(Object o){
        lists.add(o);
    }

    int size(){
        return lists.size();
    }


    public static void main(String[] args) {
        useless c = new useless();
        new Thread(()->{
            for(int i=0;i<10;i++){
                c.add(new Object());
                System.out.println("add"+i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"t1").start();

        new Thread(()->{
            while (true){
                if(c.size() == 5){
                    break;
                }
            }
            System.out.println("t2结束");
        },"t2").start();


    }
}
