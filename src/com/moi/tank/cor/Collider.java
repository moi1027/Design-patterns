package com.moi.tank.cor;

import com.moi.tank.GameModel;
import com.moi.tank.GameObject;

/**
 * @program:
 * @description: 调停者模式
 * @author: moi
 * @create: 2021/1/22 15:43
 **/
public interface Collider {

    /**
     * 碰撞检测
     * @return
     */
    boolean collide(GameObject o1, GameObject o2);

}
