package recursiveUsage;

import java.util.List;

public class ReflecMethInvocation {

    List<Object> list;
    int index = -1;

    public ReflecMethInvocation(List<Object> list) {

        this.list = list;
    }

    public Object proceed(){
        if (this.index == this.list.size() - 1) {
            System.out.println("Method invoked");
            return 1;
        }
        Object interceptorAdvice = this.list.get(++this.index);
        return ((IMethodIntercept)interceptorAdvice).invoke(this);
    }
}
