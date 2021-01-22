package com.moi.tank;

import java.awt.*;
import java.util.Random;
import java.util.UUID;

/**
 * @program:
 * @description: 子弹类
 * @author: moi
 * @create: 2021/1/7 21:16
 **/
public class Bullet extends GameObject{


    private final static int SPEED = 6;



    public static int WIDTH = ResourceMgr.bulletD.getWidth();

    public static int HEIGHT = ResourceMgr.bulletD.getHeight();

    private UUID id = UUID.randomUUID();
    private UUID playerId;




    /**
     * 矩形
     */
    public Rectangle rect = new Rectangle();

    GameModel gm = GameModel.INSTANCE;


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

    public Bullet(UUID playerId, int x, int y, Dir dir, Group group){
        this.playerId = playerId;
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group =group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    /**
     * 子弹与坦克的碰撞检测 废弃 由调停者模式来实现了解耦合
     * @param tank
     */
    public void collideWith(Tank tank) {
        if(this.playerId.equals(tank.getId())) return;
        if(this.living && tank.isLiving() && tank.getGroup() != this.getGroup()&&this.rect.intersects(tank.rect)) {
            tank.die();
            this.die();
            this.gm.remove(tank);
            if(tank.getGroup() == Group.GOOD){
                this.gm.myTank = new Tank(new Random().nextInt(1080), new Random().nextInt(960), Dir.DOWN, Group.GOOD,false);
            }
            //Client.INSTANCE.send(new TankDieMsg(this.id, tank.getId()));
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
            gm.remove(this);
        }

        switch(dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }

        move();
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
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
