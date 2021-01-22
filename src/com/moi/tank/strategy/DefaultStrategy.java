package com.moi.tank.strategy;

import com.moi.tank.*;

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
        Bullet b = new Bullet(tank.id, bX, bY, tank.dir, tank.group);

        GameModel.getInstance().add(b);

    }
}
