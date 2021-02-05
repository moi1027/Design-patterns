package com.moi.tank.proxy.CGLIB;

/**
 * @program:
 * @description: CGLIB Proxy
 * @author: moi
 * @create: 2021/2/5 22:55
 **/
public class CglibProxy {


    /**
     * 解释：由于缺少jar包就不跑，cglib动态代理和jdk动态代理两者的区别是
     *      jdk代理的类必须要实现一个接口，但是其内部为asm实现，直接更改二进制码
     *      cglib动态代理直接代理类即可，内部会创建一个子类继承自代理类，更加方便
     *      但是有些final修饰的类无法创建子类就无法用cglib来进行代理
     *总结：两者相互都有优势也有劣势，jdk可以直接修改二进制码更加灵活，cglib相对来说更加方便。
     *
     */
//    public static void main(String[] args) {
//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(Tank.class);
//        enhancer.setCallback(new TimeMethodInterceptor());
//        Tank tank = (Tank)enhancer.create();
//        tank.move();
//    }
}

//class TimeMethodInterceptor implements MethodInterceptor {
//
//    @Override
//    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//
//        System.out.println(o.getClass().getSuperclass().getName());
//        System.out.println("before");
//        Object result = null;
//        result = methodProxy.invokeSuper(o, objects);
//        System.out.println("after");
//        return result;
//    }
//}
//
//没有实现一个接口
//class Tank {
//    public void move() {
//        System.out.println("Tank moving claclacla...");
//        try {
//            Thread.sleep(new Random().nextInt(10000));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}

