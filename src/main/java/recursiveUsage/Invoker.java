package recursiveUsage;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Invoker {



    @Test
    public void test() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        IMethodIntercept method1 = new BeforeMethodIntercept();
        IMethodIntercept method2 = new AfterMethodIntercept();
        IMethodIntercept method3 = new AfterReturningMethodIntercept();
        List<Object> list = new ArrayList<Object>(3);
        list.add(method3);
        list.add(method2);
        list.add(method1);

        Object ret = new AopProxy(list).proceed();

    }


    public static class AopProxy extends ReflecMethInvocation{

        public AopProxy(List<Object> list){
            super(list);
        }

        public Object proceed(){
            Object ret = super.proceed();
            System.out.println(ret);
            return ret;
        }
    }
}
