# 关于Java和Spring学习的示例代码
## 一.  Spring-context容器中bean的生命周期
1. 实例化（Instantiate）——> 注入属性值（populate）——> 初始化（Initialize）——>使用bean——>销毁bean
2. 指定初始化和销毁的方法:   
  (1) @Bean(init-method,destroy-method)   
  (2) 通过Bean实现InitializingBean和DisposableBean  
  (3) JSR250规范中的：@PostConstruct和@PreDestroy  
  (4) BeanPostProcessor——**在以上所有方法的之前或之后生效**
