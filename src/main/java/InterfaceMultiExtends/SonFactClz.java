package InterfaceMultiExtends;

import org.springframework.beans.factory.annotation.Autowired;

public class SonFactClz extends FactClz {
    public String dadMethod1() {

        return "dadMethod1 now!";
    }


    public static void main(String[] args) {
        SonFactClz sonFactClz = new SonFactClz();
        sonFactClz.dadMethod2();
    }
}
