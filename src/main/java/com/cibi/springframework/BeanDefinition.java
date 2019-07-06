package com.cibi.springframework;

import org.apache.commons.lang3.StringUtils;

/**
 * Autor:hash
 * Date: 2019/4/8
 * Description: bean定义接口，用户包装普通类，增强bean
 */
public interface BeanDefinition {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    Class<?> getBeanClass();

    String getScope();

    boolean isSingleton();

    boolean isPrototype();

    String getFactoryBeanName();

    String getFactoryMethodName();

    String getInitMethodName();

    String getDestoryMethodName();

    default boolean validate() {
        if (this.getBeanClass() == null) {
            if (StringUtils.isBlank(this.getFactoryBeanName()) || StringUtils.isBlank(this.getFactoryMethodName())) {

                return false;
            }
        }
        if (this.getBeanClass() != null && StringUtils.isNotBlank(getFactoryBeanName())) {

            return false;
        }

        return true;
    }

}
