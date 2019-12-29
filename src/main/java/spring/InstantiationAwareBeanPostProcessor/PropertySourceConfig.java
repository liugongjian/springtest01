package spring.InstantiationAwareBeanPostProcessor;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


//读取properties配置文件，保存在运行环境中的变量，然后就可以在工程中用@Value
@PropertySource(value={"classpath:/mysqldb.properties"})
@Configuration
public class PropertySourceConfig {
}
