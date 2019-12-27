package spring.anotationUsage;


import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import spring.anotationUsage.bean.Color;
import spring.anotationUsage.bean.Person;
import spring.anotationUsage.condition.LinuxCondition;
import spring.anotationUsage.condition.WindowsCondition;


//配置类==配置文件xml
//包扫描xml配置：<context:component-scan base-package="com.atguigu"></context:component-scan>
//现在用@ComponentScan
//除了exclude，还有include——只包含某个包
//ComponentScan和ComponentScans只能用其中一个

//@ComponentScan(value="spring.anotationUsage.MVCAnotationBean",
//        excludeFilters ={@ComponentScan.Filter(type=FilterType.ANNOTATION,classes={Service.class})
//})
//@ComponentScan(value="spring.anotationUsage.MVCAnotationBean",
//        excludeFilters ={@ComponentScan.Filter(type=FilterType.ANNOTATION,classes={Controller.class})
//        })

@Configuration
@ComponentScans(
        value = {
        @ComponentScan(value="spring.anotationUsage.MVCAnotationBean",
                excludeFilters ={
                      //@ComponentScan.Filter(type=FilterType.ANNOTATION,classes={Service.class}),
                        @ComponentScan.Filter(type = FilterType.CUSTOM,classes = {MyCustomTypeFilter.class})
        }),
        @ComponentScan(value="spring.anotationUsage.MVCAnotationBean",
                excludeFilters ={@ComponentScan.Filter(type=FilterType.ANNOTATION,classes={Controller.class})
                })
        }
)
@Import(Color.class) //导入外部包的时候，不能使用@Component @Controller等注解，那么可以使用这种方法，直接注册bean到当前容器；
// 导入多个就是@Import({Color.class,...});
// 2.或者实现ImportSelector接口放入@Import注解中——可以实现批量导入一批，或者有条件的导入一批：@Import({MyImportSelector.class}) MyImportSelector实现了ImportSelector接口；
// 3.或者自己实现ImportBeanDefinitionRegistry:直接插入BeanDefinition来注册bean，@Import({MyImportBeanDefinitionRegistry.class});
// 4.或者使用FactoryBean
public class MainConfig {

    //容器中注册一个bean；类型为返回值类型，id默认是用方法名作为id
    //如果后来追加了bean名称，就改为追加的名字
    //Prototype 在容器启动时不会创建bean，只会在getBean的时候创建，request：同一个request创建一个bean，session：同一个session创建一个bean
    //@Lazy懒加载：容器启动不创建bean，在第一次获取bean的时候创建

    //@Scope("prototype")
    @Lazy
    @Bean("person")
    public Person getPerson(){
        return new Person("lisi");
    }

    //@Conditional:按照一定的条件进行判断，满足条件的给容器中注册bean
    //如果是windows系统，容器中注册“bill”
    //如果是linux，就是“linus”
    @Conditional({WindowsCondition.class})
    @Bean("bill")
    public Person person01(){
        return new Person("Bill Gates");
    }

    @Conditional({LinuxCondition.class})
    @Bean("linus")
    public Person person02(){
        return new Person("Linus");
    }

    //该bean返回的实际是Color，因为实现了FactoryBean的接口
    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }
}
