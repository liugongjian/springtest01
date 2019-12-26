package SqlMultiInterface;

public class AdbOperate implements IAdbOperation  {
    @Override
    public void select() {
        System.out.println("operating a db");
    }
}
