package com.moi.tank.strategy;

import com.moi.tank.Tank;

/**
 * @program: 开火的策略模式
 * @author: moi
 * @create: 2021/1/18 16:34
 **/
public interface FireStrategy {

    /**
     * 开火的接口
     */
    void fire(Tank tank);

}
