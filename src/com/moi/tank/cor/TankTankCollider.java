package com.moi.tank.cor;

import com.moi.tank.GameObject;
import com.moi.tank.Tank;

/**
 * @program:
 * @description: 坦克之间的碰撞的处理
 * @author: moi
 * @create: 2021/1/22 16:26
 **/
public class TankTankCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Tank){
            Tank t1 = (Tank)o1;
            Tank t2 = (Tank)o2;
            if(t1.rect.intersects(t2.rect)){
                t1.back();
                t2.back();
            }
        }
        return true;
    }
}
