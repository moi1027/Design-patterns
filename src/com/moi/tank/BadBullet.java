package com.moi.tank;

import com.moi.tank.abstractfactory.BaseBullet;
import com.moi.tank.abstractfactory.BaseTank;

import java.awt.*;
import java.util.Random;
import java.util.UUID;

/**
 * @program:
 * @description:
 * @author: moi
 * @create: 2021/1/19 21:09
 **/
public class BadBullet extends BaseBullet {

    private final static int SPEED = 6;



    public static int WIDTH = ResourceMgr.bulletD.getWidth();

    public static int HEIGHT = ResourceMgr.bulletD.getHeight();

    private UUID id = UUID.randomUUID();
    private UUID playerId;




    /**
     * 矩形
     */
    Rectangle rect = new Rectangle();

    TankFrame tf;



    public TankFrame getTf() {
        return tf;
    }

    public void setTf(TankFrame tf) {
        this.tf = tf;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPlayerId() {
        return playerId;
    }



    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public BadBullet(UUID playerId, int x, int y, Dir dir, Group group, TankFrame tf){
        this.playerId = playerId;
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group =group;
        this.tf = tf;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    /**
     * 子弹与坦克的碰撞检测
     * @param tank
     */
    @Override
    public void collideWith(BaseTank tank) {
        if(this.playerId.equals(tank.getId())) return;
        if(this.living && tank.isLiving() && tank.getGroup() != this.getGroup()&&this.rect.intersects(tank.rect)) {
            tank.die();
            this.die();
            this.tf.tanks.remove(tank.getId());
            if(tank.getGroup() == Group.GOOD){
                this.tf.myTank = tf.abstractFactory.createTank(new Random().nextInt(1080), new Random().nextInt(960), Dir.DOWN, Group.GOOD,false,this.tf);
            }
        }

    }

    public void die() {
        this.living = false;
    }

    private void move() {

        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        //update rect
        rect.x = this.x;
        rect.y = this.y;

        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            living = false;
        }

    }
    @Override
    public void paint(Graphics g) {
        if(!living) {
            tf.bullets.remove(this);
        }

        Color c = g.getColor();
        g.setColor(Color.BLUE);
        g.fillRect(x,y,10,10);
        g.setColor(c);
//        switch(dir) {
//            case LEFT:
//                g.drawImage(ResourceMgr.bulletL, x, y, null);
//                break;
//            case UP:
//                g.drawImage(ResourceMgr.bulletU, x, y, null);
//                break;
//            case RIGHT:
//                g.drawImage(ResourceMgr.bulletR, x, y, null);
//                break;
//            case DOWN:
//                g.drawImage(ResourceMgr.bulletD, x, y, null);
//                break;
//        }

        move();
    }

    /**
     * 子弹的坐标
     */
    private int x;
    private int y;

    private Dir dir;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    private boolean living = true;

    private Group group = Group.BAD;
}
