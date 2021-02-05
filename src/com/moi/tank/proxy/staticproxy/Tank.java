package com.moi.tank.proxy.staticproxy;

import java.util.concurrent.TimeUnit;

/**
 * @program:
 * @description:
 * @author: moi
 * @create: 2021/2/4 22:11
 **/
public class Tank implements Movable{
    @Override
    public void move() {
        System.out.println("tank move.....");
        try {
            //模拟坦克移动了10秒钟
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
