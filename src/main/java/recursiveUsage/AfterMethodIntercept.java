package recursiveUsage;

public class AfterMethodIntercept implements IMethodIntercept{
    @Override
    public Object invoke(ReflecMethInvocation mi) {
        try {
            return mi.proceed();
        }
        finally {
            System.out.println("After Method invoked");
        }
    }
}
