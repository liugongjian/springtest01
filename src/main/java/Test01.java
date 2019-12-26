import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Test01 {
    @Test
    public void studentTest(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student s1 = applicationContext.getBean("s1", Student.class);
        Student s2 = applicationContext.getBean("s2", Student.class);
        System.out.println(s1); System.out.println(s2);

        DefaultListableBeanFactory db = new DefaultListableBeanFactory();

    }

}