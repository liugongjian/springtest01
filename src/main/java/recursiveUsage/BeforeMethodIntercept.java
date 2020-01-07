package recursiveUsage;

public class BeforeMethodIntercept implements IMethodIntercept{
    @Override
    public Object invoke(ReflecMethInvocation mi) {
        System.out.println("Before method invoked");
        return mi.proceed();
    }
}
