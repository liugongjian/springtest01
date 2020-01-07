package spring.BeanDefinitionRegistryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.stereotype.Component;


@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {


    //BeanDefinitionRegistry就是所有bean定义信息的保存中心
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("MyBeanDefinitionRegistryPostProcessor...postProcessBeanDefinitionRegistry...bean的数量："+registry.getBeanDefinitionCount());
        RootBeanDefinition beanDefinition = new RootBeanDefinition(SingletonBean.class);
        //AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(SingletonBean.class);
        registry.registerBeanDefinition("hello", beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("postProcessBeanFactory..postProcessBeanFactory...bean的数量："+beanFactory.getBeanDefinitionCount());
    }
}
