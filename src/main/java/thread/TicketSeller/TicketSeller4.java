package thread.TicketSeller;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;


//结果正确，且不会出什么问题，效率很高
//因为这里的执行逻辑——没有修改队列（比如remove操作）
//而且poll不是加锁的实现
public class TicketSeller4 {

    static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for(int i=0; i<1000;i++) tickets.add("票编号："+ i);
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(()->{

                while (true){
                    String s = tickets.poll();
                    if(s == null)break;
                    else System.out.println("销售空了--" + s);
                }
            }).start();
        }
    }
}
