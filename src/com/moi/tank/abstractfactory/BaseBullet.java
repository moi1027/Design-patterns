package com.moi.tank.abstractfactory;

import com.moi.tank.Tank;

import java.awt.*;

/**
 * @program:
 * @description: 子弹的基类
 * @author: moi
 * @create: 2021/1/19 20:28
 **/
public abstract class BaseBullet {


    public abstract Object getId();

    public abstract void paint(Graphics g);

    public abstract void collideWith(BaseTank t);
}
