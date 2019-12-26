package abstrUsage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class invoker {

    public static void main(String[] args) {
        abstractClz ainstance = new extendsClz();
        ainstance.method();
         ConfigurableApplicationContext fs = new AnnotationConfigApplicationContext();
        System.out.println(fs.CONFIG_LOCATION_DELIMITERS);
    }

}
