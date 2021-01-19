package com.moi.tank.strategy;

import com.moi.tank.*;
import com.moi.tank.abstractfactory.BaseBullet;

/**
 * @program:
 * @description:
 * @author: moi
 * @create: 2021/1/18 16:50
 **/
public class MyTankFireStrategy implements FireStrategy{
    @Override
    public void fire(Tank tank) {
        int bX = tank.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = tank.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        Dir[] values = Dir.values();
        for (Dir dir:values) {
            BaseBullet b = tank.tf.abstractFactory.createBullet(tank.id, bX, bY, dir, tank.group, tank.tf);
            tank.tf.bullets.add(b);
        }
        new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
