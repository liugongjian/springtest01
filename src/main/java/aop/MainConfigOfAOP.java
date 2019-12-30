package aop;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 *
 * 这个是开启xml格式的切面功能
 * <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
 * @EnableAspectJAutoProxy是基于注解的
 *
 */

@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAOP {


    @Bean
    public MathCalculator calculator(){
        return new MathCalculator();
    }

    @Bean
    public LogAspects aspects(){
        return new LogAspects();
    }

}
