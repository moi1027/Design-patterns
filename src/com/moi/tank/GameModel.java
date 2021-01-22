package com.moi.tank;

import com.moi.tank.cor.Collider;
import com.moi.tank.cor.ColliderChain;
import org.omg.PortableInterceptor.INACTIVE;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @program:
 * @description: 门面模式
 * @author: moi
 * @create: 2021/1/20 20:48
 **/
public class GameModel {

    /**
     * 构架一个单例
     */
    static  GameModel INSTANCE = new GameModel();

    private GameModel(){
    }


    public static GameModel getInstance(){
        return INSTANCE;
    }


    static{
        INSTANCE.init();
    }


    Tank myTank;

    Collider chain = new ColliderChain();

    List<GameObject> objects = new ArrayList<>();

    private void init(){
        //初始化主站坦克
        myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD,false);

        Integer tankNums = Integer.parseInt((String)PropertiesMgr.getInstance().get("initTankCount"));

        //初始化敌人坦克
        for (int i = 0; i < tankNums; i++) {
            add(new Tank(50*i+100,20*i+50,Dir.DOWN, Group.BAD,true));
        }
    }


    public  void add(GameObject go){
        this.objects.add(go);
    }

    public void remove(GameObject go) {
        this.objects.remove(go);
    }


    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.setColor(c);

        myTank.paint(g);
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }
        // 互相碰撞
        for (int i = 0; i < objects.size(); i++) {
            // Comparator.compare(o1,o2)
            for (int j = i + 1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                // for
                chain.collide(o1, o2);
            }
        }


    }


}
