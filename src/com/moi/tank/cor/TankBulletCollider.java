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
                //����Ϊ��ͬ���ԣ�����ͨ�������⣬�����һ��
                return true;
            }
            if (tank.isLiving() && tank.rect.intersects(bullet.rect)) {
                tank.die();
                bullet.die();
                int eX = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
                int eY = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
                new Explode(eX, eY);
                return false;

            }
        }else if (o1 instanceof Tank && o2 instanceof Bullet) {
            return collide(o2, o1);
        }

        return true;
    }
}
