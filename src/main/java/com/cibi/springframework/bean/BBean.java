package com.cibi.springframework.bean;

/**
 * Autor:hash
 * Date: 2019/4/21
 * Description:静态工厂构造bean
 */
public class BBean {

    public static BBean getBean() {

        return new BBean();
    }

    public void doSomething() {

        System.out.println("BBean do something");
    }

    public void init() {

        System.out.println("BBean do init");
    }

}
