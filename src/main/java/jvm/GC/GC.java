package jvm.GC;

import java.util.Arrays;

public class GC {

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");

    }

    public static void main(String[] args) {
        int i = 0;
        for (;i<100000;i++) {
            byte[] a = new byte[1024 * 1024];
            System.out.println(i);
        }
    }

}
