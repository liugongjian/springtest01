package SqlMultiInterface;

public class BdbOperate implements IAdbOperation  {
    @Override
    public void select() {
        System.out.println("operating b db");
    }
}

