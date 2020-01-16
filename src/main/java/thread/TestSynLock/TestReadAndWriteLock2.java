package thread.TestSynLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * ReetrantReadWriteLock实现
 * @author itbird
 * 结论：读写锁的实现必须确保写操作对读操作的内存影响。换句话说，一个获得了读锁的线程必须能看到前一个释放的写锁所更新的内容，读写锁之间为互斥。
 * 读锁和读锁之间是共享锁
 * 写锁和写锁之间是互斥锁
 *
 */
public class TestReadAndWriteLock2 {

    public static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) throws InterruptedException {
        //同时读、写
        ExecutorService service = Executors.newCachedThreadPool();

        Thread readThread1 = new Thread("Read Thread");
        Thread readThread2 = new Thread("Read Thread");
        Thread writeThread1 = new Thread("Write Thread");
        Thread writeThread2 = new Thread("Write Thread");
        service.execute(new Runnable() {
            @Override
            public void run() {
                readFile(readThread1);
            }
        });
//        service.execute(new Runnable() {
//            @Override
//            public void run() {
//                readFile(readThread2);
//            }
//        });
        service.execute(new Runnable() {
            @Override
            public void run() {
                writeFile(writeThread1);
            }
        });
        service.execute(new Runnable() {
            @Override
            public void run() {
                writeFile(writeThread2);
            }
        });




        //TimeUnit.SECONDS.sleep(1);

    }

    // 读操作
    public static void readFile(Thread thread) {
        lock.readLock().lock();
        System.out.println(System.currentTimeMillis());
        boolean readLock = lock.isWriteLocked();
        if (!readLock) {
            System.out.println("当前为读锁！");
        }
        try {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName() + ":正在进行读操作……");
            }
            System.out.println(thread.getName() + ":读操作完毕！");
        } finally {
            System.out.println("释放读锁！");
            lock.readLock().unlock();
        }
    }

    // 写操作
    public static void writeFile(Thread thread) {
        lock.writeLock().lock();
        System.out.println(System.currentTimeMillis());
        boolean writeLock = lock.isWriteLocked();
        if (writeLock) {
            System.out.println("当前为写锁！");
        }
        try {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName() + ":正在进行写操作……");
            }
            System.out.println(thread.getName() + ":写操作完毕！");
        } finally {
            System.out.println("释放写锁！");
            lock.writeLock().unlock();
        }
    }
}
