package com.moi.tank.proxy.staticproxy;

/**
 * @program:
 * @description:
 * @author: moi
 * @create: 2021/2/4 22:13
 **/
public class LogProxyTank implements Movable{

    Tank tank;

    public  LogProxyTank(Movable movable){
        this.tank = (Tank) movable;
    }

    /**
     * 手动通过创建类来实现代理
     */
    @Override
    public void move() {
        System.out.println("start....");
        tank.move();
        System.out.println("end....");
    }

    public static void main(String[] args) {
        new LogProxyTank(new Tank()).move();
    }
}
