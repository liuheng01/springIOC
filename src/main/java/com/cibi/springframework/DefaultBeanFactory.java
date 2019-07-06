

package com.cibi.springframework;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Autor:hash
 * Date: 2019/4/20
 * Description:默认bean工厂用于管理bean,返回bean
 */
public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegistry {

    private final Logger log = LoggerFactory.getLogger(DefaultBeanFactory.class);

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private Map<String, Object> beanMap = new ConcurrentHashMap<>();

    @Override
    public void beanDefinitionRegistry(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionRegistException {

        Objects.requireNonNull(beanName, "beanName is null");
        Objects.requireNonNull(beanDefinition, "beanDefinition is null");
        if (!beanDefinition.validate()) {

            throw new BeanDefinitionRegistException("beanDefinition is illeagl");
        }

        if (this.containsBeanDefinition(beanName)) {
            log.error("beanName is exist {}", beanName);
            throw new BeanDefinitionRegistException("beanName  is exist ");
        }

        this.beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return this.beanDefinitionMap.get(beanName);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return this.beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public Object getBean(String beanName) throws Exception {
        return this.doGetBean(beanName);
    }

    protected Object doGetBean(String beanName) throws Exception {

        log.info("beanName is {}", beanName);
        Objects.requireNonNull(beanName, "beanName is null");

        Object instance = beanMap.get(beanName);

        if (instance != null) {

            return instance;
        }

        BeanDefinition bd = this.getBeanDefinition(beanName);
        Objects.requireNonNull(bd, "beanDefinition is null");
        Class<?> type = bd.getBeanClass();
        if (type != null) {

            if (StringUtils.isBlank(bd.getFactoryMethodName())) {

                // 构造方法构造对象
                instance = createInstanceByConstructor(bd);
            } else {
                // 静态工厂构造对象
                instance = createInstanceByStaticFactoryMethod(bd);
            }
        } else {

            // 工厂bean方法构造对象
            instance = createInstanceByFactoryBean(bd);
        }

        // 初始化
        this.doInit(bd, instance);

        // 初始化单例map
        if (bd.isSingleton()) {

            beanMap.put(beanName, instance);
        }

        return instance;
    }

    // 构造方法构建对象
    private Object createInstanceByConstructor(BeanDefinition bd) throws Exception {

        return bd.getBeanClass().newInstance();
    }

    // 静态工厂方法
    private Object createInstanceByStaticFactoryMethod(BeanDefinition bd) throws Exception {

        Class<?> type = bd.getBeanClass();
        Object instance = null;
        try {
            Method method = type.getMethod(bd.getFactoryMethodName(), null);
            instance = method.invoke(type, null);
        } catch (Exception e) {
            log.error("factoryMethod init bean error");
            throw e;
        }
        return instance;
    }

    private Object createInstanceByFactoryBean(BeanDefinition bd) {

        Object instance = null;
        try {
            Object factoryBean = this.doGetBean(bd.getFactoryBeanName());
            Method m = factoryBean.getClass().getMethod(bd.getFactoryMethodName(), null);
            instance = m.invoke(factoryBean, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return instance;
    }

    private void doInit(BeanDefinition bd, Object instance) throws Exception {

        if (StringUtils.isNotBlank(bd.getInitMethodName())) {

            Method m = instance.getClass().getMethod(bd.getInitMethodName(), null);
            m.invoke(instance, null);
        }
    }
}
