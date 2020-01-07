package recursiveUsage;

public class AfterReturningMethodIntercept implements IMethodIntercept {
    @Override
    public Object invoke(ReflecMethInvocation mi) {
        Object retVal = mi.proceed();
        System.out.println("After Returning Method Invoked");
        return retVal;
    }
}
