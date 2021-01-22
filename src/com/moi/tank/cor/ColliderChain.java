package com.moi.tank.cor;

import com.moi.tank.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @program:
 * @description: 责任链模式
 * @author: moi
 * @create: 2021/1/22 16:05
 **/
public class ColliderChain implements Collider{

    /**
     * 用链表的形式来表示能更好的理解责任链模式
     */
    static List<Collider> colliders = new LinkedList<>();

    static{
        colliders.add(new TankBulletCollider());
        colliders.add(new TankTankCollider());
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliders.size(); i++) {
            colliders.get(i).collide(o1,o2);
        }
        return true;
    }
}
