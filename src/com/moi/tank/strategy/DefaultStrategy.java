package com.moi.tank.strategy;

import com.moi.tank.Audio;
import com.moi.tank.Bullet;
import com.moi.tank.Group;
import com.moi.tank.Tank;
import com.moi.tank.abstractfactory.BaseBullet;

/**
 * @program:
 * @description: 默认的开火策略
 * @author: moi
 * @create: 2021/1/18 16:37
 **/
public class DefaultStrategy implements FireStrategy{
    @Override
    public void fire(Tank tank) {
        int bX = tank.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = tank.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        BaseBullet b = tank.tf.abstractFactory.createBullet(tank.id, bX, bY, tank.dir, tank.group, tank.tf);

        tank.tf.bullets.add(b);

    }
}
