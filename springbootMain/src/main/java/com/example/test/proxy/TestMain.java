package com.example.test.proxy;/**
 * @title: TestMain
 * @projectName springbootMain
 * @description: TODO
 * @author ZLS
 * @date 2019/12/312:28
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Proxy;

/**
 *@Description TODO
 *@Author ZLS
 *@Date 2019/12/3 12:28
 **/
public class TestMain {
    @Test
    public void testJdkProxy(){
        Star liuDeHua=new LiuDeHua();
        StarJdkProxy starProxy= new StarJdkProxy(liuDeHua);
        Star proxyObject=(Star) starProxy.CreatProxyedObj();
        proxyObject.dance();
        proxyObject.sing();
    }
    @Test
    public void testJdkProxy2(){
        WangFei wangFei=new WangFei();
        StarJdkProxy starProxy= new StarJdkProxy(wangFei);
        WangFei proxyObject=(WangFei) starProxy.CreatProxyedObj();
        proxyObject.sing();
    }
    @Test
    public void testCglibProxy(){
        Star liuDeHua=new LiuDeHua();
        StarCglibProxy starCglibProxy=new StarCglibProxy();
        Star proxyObject=(Star) starCglibProxy.CreatProxyedObj(liuDeHua.getClass());
        proxyObject.dance();
    }
    @Test
    public void testCglibProxy2(){
        WangFei wangFei=new WangFei();
        StarCglibProxy starCglibProxy=new StarCglibProxy();
        WangFei proxyObject=(WangFei) starCglibProxy.CreatProxyedObj(wangFei.getClass());
        proxyObject.sing();
    }

}
