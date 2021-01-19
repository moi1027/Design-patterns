package com.moi.tank.abstractfactory;

import com.moi.tank.*;

import java.util.UUID;

/**
 * @program:
 * @description:
 * @author: moi
 * @create: 2021/1/19 20:24
 **/
public class DefaultFactory extends AbstractFactory {
    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, Boolean moving, TankFrame tf) {
        return new Tank(x,y,dir,group,moving,tf);
    }

    @Override
    public BaseBullet createBullet(UUID playerId, int x, int y, Dir dir, Group group, TankFrame tf) {
        return new Bullet(playerId,x,y,dir,group,tf);
    }

    @Override
    public BaseExplode createExplode(int x,int y) {
        return new Explode(x,y);
    }
}
