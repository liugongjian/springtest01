package thread;

public class Thread01 extends Thread {

    public void run(){
        System.out.println("haha");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread01();
        t.start();
        t.join();
       // Thread.sleep(10000);
    }
}
