package spring.BeanDefinitionRegistryPostProcessor;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    @Test
    public void test(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);

    }
}
