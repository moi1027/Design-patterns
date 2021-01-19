package com.moi.tank.abstractfactory;

import com.moi.tank.*;

import java.util.UUID;

/**
 * @program:
 * @description: 抽象工厂
 * @author: moi
 * @create: 2021/1/19 20:21
 **/
public abstract class AbstractFactory {

    /**
     * 创建坦克
     * @param x
     * @param y
     * @param dir
     * @param group
     * @param moving
     * @param tf
     * @return
     */
    public abstract BaseTank createTank(int x, int y, Dir dir, Group group, Boolean moving, TankFrame tf);

    /**
     * 创建子弹
     * @param playerId
     * @param x
     * @param y
     * @param dir
     * @param group
     * @param tf
     * @return
     */
    public abstract BaseBullet createBullet(UUID playerId, int x, int y, Dir dir, Group group, TankFrame tf);

    /**
     * 创建爆炸
     * @param x
     * @param y
     * @return
     */
    public abstract BaseExplode createExplode(int x,int y);
}
