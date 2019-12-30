package aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainTest {

    @Test
    public void test1(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
        MathCalculator mc = ac.getBean(MathCalculator.class);
        mc.div(1,1);
        ((AnnotationConfigApplicationContext) ac).close();
    }
}
