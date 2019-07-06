package com.cibi.springframework;

/**
 * Autor:hash
 * Date: 2019/4/20
 * Description:
 */
public class BeanDefinitionRegistException extends Exception {


    public BeanDefinitionRegistException(String msg) {
        super(msg);
    }

    public BeanDefinitionRegistException(String msg, Throwable e) {
        super(msg, e);
    }
}
