package com.moi.tank.cor;

import com.moi.tank.*;

import java.util.Random;

/**
 * @program:
 * @description:
 * @author: moi
 * @create: 2021/1/22 15:44
 **/
public class TankBulletCollider implements Collider{
    @Override
    public boolean collide(GameObject o1,GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Bullet) {
            Tank tank = (Tank) o1;
            Bullet bullet = (Bullet) o2;
            if (tank.getGroup() == bullet.getGroup()) {
                //由于为相同属性，所以通过这个检测，检测下一个
                return true;
            }
            if (tank.isLiving() && tank.rect.intersects(bullet.rect)) {
                tank.die();
                bullet.die();
                return false;

            }
        }else if (o1 instanceof Tank && o2 instanceof Bullet) {
            return collide(o2, o1);
        }

        return true;
    }
}
