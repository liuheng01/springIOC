package com.cibi.springframework;

/**
 * Autor:hash
 * Date: 2019/4/20
 * Description: 生成bean定义
 */
public class GenericBeandefinition implements BeanDefinition {

    private Class<?> beanClass;
    private String scope = BeanDefinition.SCOPE_SINGLETON;
    private String factoryBeanName;
    private String factoryMethodName;
    private String initMethodName;
    private String destoryMethodName;

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }

    public void setFactoryMethodName(String factoryMethodName) {
        this.factoryMethodName = factoryMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public void setDestoryMethodName(String destoryMethodName) {
        this.destoryMethodName = destoryMethodName;
    }

    @Override
    public Class<?> getBeanClass() {
        return this.beanClass;
    }

    @Override
    public String getScope() {
        return this.scope;
    }

    @Override
    public boolean isSingleton() {
        return BeanDefinition.SCOPE_SINGLETON.equals(this.scope);
    }

    @Override
    public boolean isPrototype() {
        return BeanDefinition.SCOPE_PROTOTYPE.equals(this.scope);
    }

    @Override
    public String getFactoryBeanName() {
        return this.factoryBeanName;
    }

    @Override
    public String getFactoryMethodName() {
        return this.factoryMethodName;
    }

    @Override
    public String getInitMethodName() {
        return this.initMethodName;
    }

    @Override
    public String getDestoryMethodName() {
        return this.destoryMethodName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenericBeandefinition that = (GenericBeandefinition) o;

        if (beanClass != null ? !beanClass.equals(that.beanClass) : that.beanClass != null) return false;
        if (scope != null ? !scope.equals(that.scope) : that.scope != null) return false;
        if (factoryBeanName != null ? !factoryBeanName.equals(that.factoryBeanName) : that.factoryBeanName != null)
            return false;
        if (factoryMethodName != null ? !factoryMethodName.equals(that.factoryMethodName) : that.factoryMethodName != null)
            return false;
        if (initMethodName != null ? !initMethodName.equals(that.initMethodName) : that.initMethodName != null)
            return false;
        return destoryMethodName != null ? destoryMethodName.equals(that.destoryMethodName) : that.destoryMethodName == null;
    }

    @Override
    public int hashCode() {
        int result = beanClass != null ? beanClass.hashCode() : 0;
        result = 31 * result + (scope != null ? scope.hashCode() : 0);
        result = 31 * result + (factoryBeanName != null ? factoryBeanName.hashCode() : 0);
        result = 31 * result + (factoryMethodName != null ? factoryMethodName.hashCode() : 0);
        result = 31 * result + (initMethodName != null ? initMethodName.hashCode() : 0);
        result = 31 * result + (destoryMethodName != null ? destoryMethodName.hashCode() : 0);
        return result;
    }
}
