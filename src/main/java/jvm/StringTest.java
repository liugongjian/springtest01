package jvm;

import java.util.Arrays;

public class StringTest {

    public void print(){
        String s = "1"+"2"+"3";
        String s1 = "hello";
        long l1 = 123;
        JVMInspect ji = new JVMInspect();
    }
    public static void staticfunc(){
        return;
    }
    public static void main(String[] args) throws InterruptedException {
        String s = "1"+"2"+"3";
        String s1 = "hello";
        final String s4 = "hello";
        String s2 = new String("hello");
        //此处的hello是对象指向到常量池（方法区）中的hello，字符串是常量，一单创建了，就不会再新创建了——此处的hello和上面的hello其实都是一个
        //此处可以看简书中的记录
        String s3 = "hello";
        int i = 0;
        StringTest.staticfunc();
//        for(;i<=10000;i++){
//            byte[] a = new byte[1024*1024];
//            Thread.sleep(1000);
//        }


//        StringTest st;
    }
}
