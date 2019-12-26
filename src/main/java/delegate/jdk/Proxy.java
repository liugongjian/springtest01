package delegate.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 *     二：动态代理的第一种实现——JDK动态代理——业务类必须实现接口
 *
 *     JDK动态代理所用到的代理类在程序调用到代理类对象时才由JVM真正创建，
 *     JVM根据传进来的业务实现类对象 以及 方法名 ，动态地创建了一个代理类的class文件并被字节码引擎执行，
 *     然后通过该代理类对象进行方法调用。我们需要做的，只需指定代理类的预处理、调用后操作即可。
 */
public class Proxy implements InvocationHandler {

    private Object target;
    public Object bind(Object target){

        this.target = target;
        return java.lang.reflect.Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = null;
        System.out.println("预处理————————");
        result = method.invoke(target,args);
        System.out.println("后处理————————");
        return result;
    }

    public static void main(String[] args) {
        FacadeImpl fi = new FacadeImpl();
        Proxy proxy = new Proxy();
        Facade facade = (Facade) proxy.bind(fi);
        facade.dosmthing();
    }

}
