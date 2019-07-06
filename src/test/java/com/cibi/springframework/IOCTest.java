package com.cibi.springframework;

import com.cibi.springframework.bean.ABean;
import com.cibi.springframework.bean.BBean;
import com.cibi.springframework.bean.CBean;
import org.junit.AfterClass;
import org.junit.Test;

/**
 * Autor:hash
 * Date: 2019/4/21
 * Description:
 */
public class IOCTest {

    static DefaultBeanFactory bf = new DefaultBeanFactory();

    /**
     * 构造方式构造
     *
     * @throws Exception
     */
    @Test
    public void testRegistry() throws Exception {

        GenericBeandefinition bd = new GenericBeandefinition();
        bd.setBeanClass(ABean.class);
        bd.setScope(BeanDefinition.SCOPE_SINGLETON);
        bd.setInitMethodName("init");
        bf.beanDefinitionRegistry("aBean", bd);
    }

    /**
     * 工厂方法注册bean
     *
     * @throws Exception
     */
    @Test
    public void testStaticFacotory() throws Exception {
        GenericBeandefinition bd = new GenericBeandefinition();
        bd.setBeanClass(BBean.class);
        String bfname = "factory";
        bd.setFactoryMethodName("getBean");
        bf.beanDefinitionRegistry(bfname, bd);
    }

    @Test
    public void testFactoryMethod() throws Exception {
        GenericBeandefinition bd = new GenericBeandefinition();
        bd.setBeanClass(CBean.class);
        String fbname = "cfactory";
        bf.beanDefinitionRegistry(fbname, bd);

        bd = new GenericBeandefinition();
        bd.setFactoryBeanName(fbname);
        bd.setFactoryMethodName("getBean");
        bd.setScope(BeanDefinition.SCOPE_PROTOTYPE);

        bf.beanDefinitionRegistry("factoryCbean", bd);

    }

    @AfterClass
    public static void testGetBean() throws Exception {

        for (int i = 0; i < 3; i++) {
            ABean ab = (ABean) bf.getBean("aBean");
            ab.doSomething();
        }

        for (int i = 0; i < 3; i++) {
            BBean ab = (BBean) bf.getBean("factory");
            ab.doSomething();
        }

        for (int i = 0; i < 3; i++) {
            CBean ab = (CBean) bf.getBean("factoryCbean");
            ab.doSomething();
        }

    }
}
