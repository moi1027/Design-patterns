package com.moi.tank.abstractfactory;

import com.moi.tank.Dir;
import com.moi.tank.Group;

import java.awt.*;
import java.util.UUID;

/**
 * @program:
 * @description: 
 * @author: moi
 * @create: 2021/1/19 20:52
 **/
public abstract class BaseTank {
    public Rectangle rect = new Rectangle();;

    public abstract UUID getId();

    public abstract void paint(Graphics g);

    public abstract boolean isLiving();

    public abstract void fire();

    public abstract Dir getDir();

    public abstract void setMoving(boolean b);

    public abstract void setDir(Dir left);

    public abstract Group getGroup();

    public abstract void die();
}
