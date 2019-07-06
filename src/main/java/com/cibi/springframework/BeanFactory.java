package com.cibi.springframework;

/**
 * Autor:hash
 * Date: 2019/4/20
 * Description:bean工厂，整个工厂往外暴露接口
 */
public interface BeanFactory {

    Object getBean(String beanName) throws Exception;
}
