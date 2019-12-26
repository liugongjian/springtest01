package abstrUsage;

public abstract class abstractClz {

    public void method(){
        String a = "before ";
        System.out.println(abstrMethod(a));
    }

    public abstract String abstrMethod(String a);
}
