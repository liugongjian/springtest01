package spring.anotationUsage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import spring.anotationUsage.bean.Person;

import java.lang.annotation.Annotation;
import java.util.Map;

public class MainTest {

    @Test
    public void main() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        //Person bean = applicationContext.getBean(Person.class);
        Person bean = (Person) applicationContext.getBean("person");
        String[] names = applicationContext.getBeanNamesForType(Person.class);
        for (String name:names){
            System.out.println(name);
            //输出getPerson——以返回bean的方法名作为bean的名称
        }
    }

    //查看包扫描的bean有哪写
    @Test
    public void test01(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] names = applicationContext.getBeanDefinitionNames();
        for(String name : names){
            System.out.println(name);
        }
    }
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
    @Test
    public void test03(){
        String[] namesForTypes = applicationContext.getBeanNamesForType(Person.class);
        ConfigurableEnvironment environment = (ConfigurableEnvironment) applicationContext.getEnvironment();
        String osName = environment.getProperty("os.name");
        System.out.println(osName);
        for (String name: namesForTypes){
            System.out.println(name);
        }

        //运行的VM选项   -Dos.name=linux   就更改了linux的os.name
        Map<String,Person> persons = applicationContext.getBeansOfType(Person.class);
        System.out.println(persons);
    }

}
