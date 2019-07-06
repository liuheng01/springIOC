package com.cibi.springframework;

/**
 * Autor:hash
 * Date: 2019/4/20
 * Description:bean定义注册接口
 */
public interface BeanDefinitionRegistry {


    void beanDefinitionRegistry(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionRegistException;

    BeanDefinition getBeanDefinition(String beanName);

    boolean containsBeanDefinition(String beanName);
}
