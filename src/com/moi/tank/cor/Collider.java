package com.moi.tank.cor;

import com.moi.tank.GameModel;
import com.moi.tank.GameObject;

/**
 * @program:
 * @description: ��ͣ��ģʽ
 * @author: moi
 * @create: 2021/1/22 15:43
 **/
public interface Collider {

    /**
     * ��ײ���
     * @return
     */
    boolean collide(GameObject o1, GameObject o2);

}
