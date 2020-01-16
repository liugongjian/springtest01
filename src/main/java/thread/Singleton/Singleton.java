package thread.Singleton;


/**
 * 线程安全的单例模式：
 * 既不用加锁，也能实现懒加载（节省运行时内存）
 *
 */
public class Singleton {

    private Singleton(){

    }
    private static class Inner{
        private static Singleton s = new Singleton();
    }

    //因为Inner类是private，所以程序加载的时候不会加载这个类，只有调用getSingle()方法的时候才会加载这个类，并实例化Singleton
    public static Singleton getSingle(){
        return Inner.s;
    }


    public static void main(String[] args) {

    }
}
