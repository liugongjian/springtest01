package jvm.StrongWeakReference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceGC {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        //强引用——new方法出来的
        //软引用——如果内存够用的情况下，不会回收，如果内存不够了，就会回收
        SoftReference aSoftRef = new SoftReference(o);
        o = null;
        Object another = (Object)aSoftRef.get();
        System.out.println(another==null);

        //弱引用——生存道下一次垃圾回收之前，无论当前内存是否够用，都回收掉弱引用关联的对象
        Object o1 = new Object();
        WeakReference wr = new WeakReference(o1);
        System.gc();

        new Thread().sleep(8000);
        System.out.println(o1.hashCode());
        System.out.println(wr.get().hashCode());

        //虚引用（没什么用）——不会对对象的生命周期有任何影响，也无法通过它得到对象的实例，唯一的作用也就是在对象被垃圾回收前收到一个系统通知
        ReferenceQueue<String> queue = new ReferenceQueue<>();
        PhantomReference<String> pr = new PhantomReference<>(new String("hello"),queue);
        System.out.println(pr.get());
    }
}
