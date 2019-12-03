package com.example.test.proxy;/**
 * @title: StarProxy
 * @projectName springbootMain
 * @description: TODO
 * @author ZLS
 * @date 2019/12/312:22
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *@Description TODO
 *@Author ZLS
 *@Date 2019/12/3 12:22
 **/
public class StarJdkProxy implements InvocationHandler {
    //目标类，也就是被代理的对象
    private Object target;

    public  StarJdkProxy(Object target){
        this.target=target;
    }
   
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 这里可以做增强
        System.out.println("JDK收钱-前");
        Object result = method.invoke(target, args);
        // 这里可以做增强
        System.out.println("JDK收钱-后");
        return result;
    }

    //生成代理类
    public Object CreatProxyedObj(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }
}
