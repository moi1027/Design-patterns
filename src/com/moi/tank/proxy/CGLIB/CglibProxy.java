package com.moi.tank.proxy.CGLIB;

/**
 * @program:
 * @description: CGLIB Proxy
 * @author: moi
 * @create: 2021/2/5 22:55
 **/
public class CglibProxy {


    /**
     * ���ͣ�����ȱ��jar���Ͳ��ܣ�cglib��̬�����jdk��̬�������ߵ�������
     *      jdk����������Ҫʵ��һ���ӿڣ��������ڲ�Ϊasmʵ�֣�ֱ�Ӹ��Ķ�������
     *      cglib��̬����ֱ�Ӵ����༴�ɣ��ڲ��ᴴ��һ������̳��Դ����࣬���ӷ���
     *      ������Щfinal���ε����޷�����������޷���cglib�����д���
     *�ܽ᣺�����໥��������Ҳ�����ƣ�jdk����ֱ���޸Ķ������������cglib�����˵���ӷ��㡣
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
//û��ʵ��һ���ӿ�
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

