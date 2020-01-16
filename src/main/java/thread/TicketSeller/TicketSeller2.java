package thread.TicketSeller;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

//Vector就是一个同步容器,所有的方法都是synchronized
//但是这个是无法满足需求的
public class TicketSeller2 {

    static Vector<String> tickets = new Vector<>();

    static {
        for(int i=0; i<1000;i++) tickets.add("票号："+ i);
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(()->{
                while (tickets.size()>0){

//                    try {
//                        TimeUnit.MICROSECONDS.sleep(10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }   加上这段代码，就会java.lang.ArrayIndexOutOfBoundsException: Array index out of range: 0
                    //虽然下面的remove是原子的，但是上面的判断和下面的remove结合就不行了，因为有可能线程A执行remove方法的时候，正好size=0了
                    System.out.println("销售了--"+tickets.remove(0));
                }
            }).start();
        }
    }
}
