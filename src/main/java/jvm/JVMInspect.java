package jvm;

import recursiveUsage.AfterMethodIntercept;


//javap -v 查看汇编（方法区和常量池）
public class JVMInspect {

    public static int i=1;
    static {
        System.out.println("hello");
    }
    //public static int i;
    //public static AfterMethodIntercept afterMethodIntercept = new AfterMethodIntercept();

    public int add1(int a, int b){

        return a+b;

    }
    public int add2(){
        int a=1;
        int b=200;
        int d = 6;
        int c=(a+b)*100;
        return c;
    }

    public static void main(String[] args) {
        JVMInspect jvm = new JVMInspect();
        int a = jvm.add2();
        System.out.println(a);
    }
}
