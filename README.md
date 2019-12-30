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
##五. AOP原理   
###1.@EnableAspectJautoProxy是最主要的注解，作用是：    
(1)@Import(AspectJautoProxyRegistar.class) 导入了这个外部类    
(2)利用AspectJautoProxyRegistar实现ImportBeanDefinitionRegistrar接口，自定义注入了一个bean
(3)这个bean是**AnnotationAwareAspectJAutoProxyCreator**实际是一个**BeanPostProcessor**    
(4) AnnotationAwareAspectJAutoProxyCreator extends....AbstractAutoProxyCreator implements **SmartInstantiationAwreBeanPostProcessor**,**BeanFactoryAware**   
(5) 创建容器-->AbstractApplicationContext.refresh()    
(6) registerBeanPostProcessors(beanFactory)：实际就是创建BeanPostProcessor对象，保存在容器中     
(7) 创建internalAutoProxyCreator的BeanPostProcessor【annotationAwareAspectJAutoProxyCreator】：   
(7.1) 创建bean   
(7.2) populateBean    
(7.3) initializeBean：初始化bean   
(7.3.1) invokeAwareMethods():处理Aware接口的方法回调    
(7.3.2) applyBeanPostProcessorBeforeInitialization()
(7.3.3) invokeInitMethods():执行自定义的初始化方法
(7.3.4) applyBeanPostProcessorAfterInitialization()           
(7.4) BeanPostProcessors(AnnotationAwareAspectJAutoProxyCreator)创建成功        
(8) 把BeanPostProcessor注册到BeanFactory中：beanFactory.addBeanPostProcessor(postProcessor)             

###2.AnnotationAwareAspectJAutoProxyCreator是如何使用的    
**BeanPostProcessor是在Bean对象创建完成，初始化前后调用的**
**InstantiationAwareBeanPostProcessor是在创建Bean实例之前，先尝试用后置处理器返回对象的**——AnnotationAwareAspectJAutoProxyCreator     
refresh()->finishBeanFactoryInitialization(beanFactory),完成beanFactory初始化，创建剩下的singlton实例（非lazy）：     
（1）getBean()->doGetBean()->getSingleton()->如果之前没创建过，调用createBean()
 (2) createBean():    
 (2.1) resolveBeforInstantiation(beanName,mdbToUse)：解析BeforeInstantiation，希望BeanPostProcessor能在此返回一个代理对象，如果能返回代理对象就使用，如果不能就调用doCreateBean()，和第一部分创建bean的流程是一样的   
 （3）每一个bean创建之前，调用**postProcessBeforeInstantiation()**:     
  (4) MathCalculator（被切类）和LogAspect（切面类）：    
  (4.1) 判断当前bean是否在advisedBeans中（保存了所有需要增强的bean——被切的类实例），第一次初始化是不在里面的     
  (4.2) 判断当前bean是否是基础类型的Advice/Pointcut/Advisor/AopInfrastructureBean  判断是否是切面类（@Aspect）     
  (4.3) 判断是否跳过——获取增强器（Advisor）就是@Before @After等通知的方法——InstantiationModelAwarePointcutAdvisor; 该步会返回false    
  (4.4) **postProcessAfterInstantiation()** 中return wrapIfNessesary()     
  (4.4.1) wrapIfNessesary()做了获取当前bean的所有增强器（通知方法）--》找到当前bean能使用的增强器——》给增强器排序——》增强器封装成一个Object[]，而bean保存在advisedBeans中    
  （4.4.2） 如果当前bean需要增强，创建当前bean的**代理**对象—》获取所有增强器（通知方法）——》保存到proxyFactory——》创建对象，**Spring自主决定创建**：JdkDynamicAopProxy(config):jdk动态代理、ObjenesisCglibAopProxy(config):cglib    
  （4.4.3） 首选jdk代理，如果没有实现接口，则使用cglib   
  （4.4.5） wrapIfNessesary()返回当前组件使用cglib增强了的代理对象，以后该对象的执行都会走代理对象的方法           
  
  
 