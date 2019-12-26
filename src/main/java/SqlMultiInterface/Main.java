package SqlMultiInterface;

import org.springframework.beans.factory.annotation.Autowired;

public class Main {


    public static void main(String[] args) {
            AdbOperate ao = new AdbOperate();
            ISelectRepo  is = ao;
            is.select();
    }
}
