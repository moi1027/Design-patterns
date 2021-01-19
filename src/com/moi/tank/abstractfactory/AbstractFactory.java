package com.moi.tank.abstractfactory;

import com.moi.tank.*;

import java.util.UUID;

/**
 * @program:
 * @description: ���󹤳�
 * @author: moi
 * @create: 2021/1/19 20:21
 **/
public abstract class AbstractFactory {

    /**
     * ����̹��
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
     * �����ӵ�
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
     * ������ը
     * @param x
     * @param y
     * @return
     */
    public abstract BaseExplode createExplode(int x,int y);
}
