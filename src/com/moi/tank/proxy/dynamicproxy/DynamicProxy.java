package com.moi.tank.proxy.dynamicproxy;

import com.moi.tank.proxy.staticproxy.Movable;
import com.moi.tank.proxy.staticproxy.Tank;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program:
 * @description: JDk „Ó‘B´úÀí
 * @author: moi
 * @create: 2021/2/4 22:17
 **/
public class DynamicProxy {
    public static void main(String[] args) {
        Tank t = new Tank();
        Movable m = (Movable) Proxy.newProxyInstance(Tank.class.getClassLoader()
                , new Class[]{Movable.class}
                , new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("before.....");
                        Object o = method.invoke(t,args);
                        System.out.println("after.....");
                        return o;
                    }
                });
        m.move();
    }
}
