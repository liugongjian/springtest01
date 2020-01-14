package jvm.GC;

import java.nio.ByteBuffer;

public class TestDirect {

    //-XX:+DisableExplicitGC    禁止显式调用System.gc()——系统也会调用来gc
    public static void main(String[] args) {
        while(true) {
            ByteBuffer b = ByteBuffer.allocateDirect(10 * 1024 * 1024);
        }
    }
}
