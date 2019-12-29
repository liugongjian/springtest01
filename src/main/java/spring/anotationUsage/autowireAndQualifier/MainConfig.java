package spring.anotationUsage.autowireAndQualifier;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("spring.anotationUsage.autowireAndQualifier")
public class MainConfig {

    @org.springframework.context.annotation.Bean("autowireBean2")
    public AutowireBean autowireBean(){
        AutowireBean bean = new AutowireBean();
        bean.setId("2");
        return bean;
    }
}
