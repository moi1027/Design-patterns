package com.moi.tank.decorator;

import com.moi.tank.GameObject;
import com.moi.tank.Group;
import com.moi.tank.ResourceMgr;

import java.awt.*;

/**
 * @program:
 * @description:
 * @author: moi
 * @create: 2021/1/25 22:02
 **/
public class RectDecorator extends TankDecorator {

    public RectDecorator(GameObject go){
        super(go);
    }



    //装饰之后无法检测碰撞，需要写碰撞类
    @Override
    public void paint(Graphics g) {
        this.x = go.x;
        this.y = go.y;

        go.paint(g);

        Color c = g.getColor();
        g.setColor(Color.RED);
        g.drawRect(super.go.x, super.go.y, ResourceMgr.badTankU.getWidth() +4, ResourceMgr.badTankU.getHeight()+4);
        g.setColor(c);
    }
}
