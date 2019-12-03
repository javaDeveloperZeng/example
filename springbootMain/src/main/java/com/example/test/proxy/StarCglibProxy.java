package com.example.test.proxy;/**
 * @title: StarCglibProxy
 * @projectName springbootMain
 * @description: TODO
 * @author ZLS
 * @date 2019/12/312:42
 */

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 *@Description TODO
 *@Author ZLS
 *@Date 2019/12/3 12:42
 **/
public class StarCglibProxy implements MethodInterceptor {
    // 根据一个类型产生代理类，此方法不要求一定放在MethodInterceptor中
    public Object CreatProxyedObj(Class<?> clazz)
    {
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(clazz);

        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable
    {
        // 这里可以做增强
        System.out.println("Cglib收钱-前");
        Object result = arg3.invokeSuper(arg0, arg2);
        // 这里可以做增强
        System.out.println("Cglib收钱-后");
        return result;
    }

}
