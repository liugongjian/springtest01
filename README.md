# 关于Java和Spring学习的示例代码
## 一.  Spring-context容器中bean的生命周期
1. 实例化（Instantiate）——> 注入属性值（populate）——> 初始化（Initialize）——>使用bean——>销毁bean
2. 指定初始化和销毁的方法:   
  (1) @Bean(init-method,destroy-method)   
  (2) 通过Bean实现InitializingBean和DisposableBean  
  (3) JSR250规范中的：@PostConstruct和@PreDestroy  
  (4) BeanPostProcessor——**在以上所有方法的之前或之后生效**
##二. @Autowired用法
表示从容器中获取bean注入到当前位置，用法包括   
1.标注在方法位置上   
@Autowired    
public void setBean（Car car）//注入Car    
2.标注在构造器上:如果只有一个构造器，参数的@Autowired可以省略，自动从容器中获取    
@Autowired    
public Boss（Car car）    
**3.标注在参数位置**    
public void setBean(@Autowired Car car)
##三.自定义Spring工作机制
使用Aware类的接口，就可以自定义spring底层运行的一些方法
##四.Profile    
1.@Profile("test")加到生成Bean的地方     
1.1    
运行VM参数位置声明：-Dspring.profiles.active=test     
1.2     
applicationContext.getEnriroment.setActiveProfiles("test","dev");    
applicationContext.register(MainConfigOfProfile.class);    
applicationContext.refresh();     
    
2.@Profile("test")加到类定义上，整个类就不会（会）被加载   

