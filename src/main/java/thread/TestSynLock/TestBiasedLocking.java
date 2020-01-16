package thread.TestSynLock;


//为了验证：当又很多线程并发访问的时候，不使用偏向锁的效率比使用偏向锁的效率要高
//-XX:+UseBiasedLocking     -XX:-UseBiasedLocking
public class TestBiasedLocking {

    private static Object o = new Object();
    private static long count = 0;

    private static Thread[] threads = new Thread[25000];

    static {
        for(int i=0;i<threads.length;i++){
            threads[i] = new Thread(()->{
                synchronized (o){
                    for(int j=0;j<100_0000;j++) count = count+1;
                }
            });
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.nanoTime();
        for(Thread thread : threads){
            thread.start();
        }
        for(Thread thread : threads){
            thread.join();
        }
        long end = System.nanoTime();

        System.out.println(end-start);
    }


}
