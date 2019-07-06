package com.cibi.springframework.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Autor:hash
 * Date: 2019/4/21
 * Description:成员工厂方式构造bean
 */
public class CBean {

    private Logger log = LoggerFactory.getLogger(CBean.class);

    public CBean getBean() {

        return new CBean();
    }

    public void doSomething() {

        System.out.println("CBean do something");
    }

    public void init() {

        System.out.println("CBean do init");
    }
}
