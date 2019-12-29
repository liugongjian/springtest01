package spring.anotationUsage.autowireAndQualifier;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MainTest {


    /**
     * @Autowired:自动注入原理：
     * 1. 默认有限按照【类型】去容器中找, 如果类型有重复的，会再根据bean的名字来查找，这种情况可以用@Qualifier+@Autowired来区别
     */
    @Test
    public void test1(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class);
        Bean bean1 =  ac.getBean(Bean.class);
//        AutowireBean bean2 = ac.getBean(Bean.class).getAutowireBean();
        System.out.println(bean1.getAutowireBean().getId());
//        System.out.println(bean2);
    }

    @Test
    public void test2() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class clz = Class.forName("spring.anotationUsage.autowireAndQualifier.AutowireBean");
        Object instance1 = clz.newInstance();
        System.out.println(instance1);
        Constructor constructor = clz.getConstructor();
        Object instance2 = constructor.newInstance();
        System.out.println(instance2);

    }
}
