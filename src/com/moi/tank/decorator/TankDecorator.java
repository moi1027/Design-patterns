package com.moi.tank.decorator;

import com.moi.tank.GameObject;

import java.awt.*;

/**
 * @program:
 * @description: tank◊∞ Œ¿‡
 * @author: moi
 * @create: 2021/1/25 20:54
 **/
public abstract class TankDecorator extends GameObject {


    GameObject go;
    public TankDecorator(GameObject go) {

        this.go = go;
    }


    @Override
    public abstract void paint(Graphics g) ;

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
