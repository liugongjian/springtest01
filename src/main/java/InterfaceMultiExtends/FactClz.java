package InterfaceMultiExtends;

public abstract class FactClz implements SonInt {
    public void dadMethod2() {
        System.out.println("I am dadMethod2 in"+this.getClass().getName());
        System.out.println("and I invoke method1:"+dadMethod1());
    }
}
