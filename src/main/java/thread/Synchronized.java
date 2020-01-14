package thread;

public class Synchronized {



    Object lock = new Object();
    void m(){
        synchronized (lock){
            System.out.println("haha");
        }
    }

    synchronized void n(){
        System.out.println("heihei ");
    }

    public static void main(String[] args) {
        Synchronized s = new Synchronized();
        s.m();
        s.n();
    }
}
