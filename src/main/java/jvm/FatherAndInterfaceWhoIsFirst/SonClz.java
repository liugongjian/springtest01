package jvm.FatherAndInterfaceWhoIsFirst;


import jvm.FatherAndInterfaceWhoIsFirst.FatherClz;
import jvm.FatherAndInterfaceWhoIsFirst.FatherInterface;
import org.junit.Test;

public class SonClz extends FatherClz implements FatherInterface {


    @Test
    public void main() {
        this.print();//只执行父类的同名方法
        //System.out.println(this.s);
        /**
         * 报错：
         * Error:(12, 32) java: 对s的引用不明确
         *   jvm.FatherClz 中的变量 s 和 jvm.FatherInterface 中的变量 s 都匹配
         */
    }
}
