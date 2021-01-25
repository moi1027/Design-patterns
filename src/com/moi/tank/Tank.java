package com.moi.tank;

import com.moi.tank.strategy.DefaultStrategy;
import com.moi.tank.strategy.MyTankFireStrategy;

import java.awt.*;
import java.util.Random;
import java.util.UUID;

/**
 * @program:
 * @description: 坦克類
 * @author: moi
 * @create: 2020/12/30 17:44
 **/
public class Tank extends GameObject{


    private static final int SPEED = 5;
    public static int WIDTH = ResourceMgr.goodTankU.getWidth();

    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();

    public UUID id = UUID.randomUUID();

    public Rectangle rect = new Rectangle();

    private Random random = new Random();
    /*public int x;
    public int y;*/


    /**
     * 上一步位置的坐标
     */
    public int oldX,oldY;

    public int getOldX() {
        return oldX;
    }

    public void setOldX(int oldX) {
        this.oldX = oldX;
    }

    public int getOldY() {
        return oldY;
    }

    public void setOldY(int oldY) {
        this.oldY = oldY;
    }

    public Dir dir = Dir.DOWN;

    public boolean moving = false;

    public GameModel gm = GameModel.INSTANCE;

    private boolean living = true;

    public boolean isLiving() {
        return living;
    }


    public void back(){
        this.x = oldX;
        this.y = oldY;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }
    public Group group = Group.BAD;
    public Tank(int x, int y, Dir dir, Group group,Boolean moving) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.moving = moving;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }



    /**
     * 边界检测
     */
    private void boundsCheck() {
        if (this.x < 2) {
            x = 2;
        }
        if (this.y < 28){
            y = 28;
        }
        if (this.x > TankFrame.GAME_WIDTH- Tank.WIDTH -2){
            x = TankFrame.GAME_WIDTH - Tank.WIDTH -2;
        }
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT -2 ){
            y = TankFrame.GAME_HEIGHT -Tank.HEIGHT -2;
        }
    }


    public void die() {
        this.living = false;
        if(this.getGroup() == Group.BAD) {
            gm.remove(this);
        }
        int eX = this.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
        int eY = this.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
        gm.add(new Explode(eX, eY));
    }

    public void fire() {
        if(this.group == Group.GOOD){
            new MyTankFireStrategy().fire(this);
        }else{
            new DefaultStrategy().fire(this);
        }
    }

    public Dir getDir() {
        return dir;
    }


    public Group getGroup() {
        return group;
    }


    public UUID getId() {
        return id;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public boolean isMoving() {
        return moving;
    }

    private void move() {
        if(!living){
            return;
        }

        if(!moving) {
            return ;
        }


        /**
         * 给上一步赋值
         */
        oldX = x;
        oldY = y;

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

        if(this.group == Group.BAD && random.nextInt(100) > 95) {
            this.fire();
        }

        if(this.group == Group.BAD && random.nextInt(100) > 95) {
            randomDir();
        }

        boundsCheck();
        //update rect
        rect.x = this.x;
        rect.y = this.y;

    }

    @Override
    public  int getWidth() {
        return WIDTH;
    }



    @Override
    public  int getHeight() {
        return HEIGHT;
    }



    @Override
    public void paint(Graphics g) {
        //uuid on head
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.setColor(c);

        //draw a rect if dead!
        if(!living) {
            moving = false;
            Color cc = g.getColor();
            g.setColor(Color.WHITE);
            g.drawRect(x, y, WIDTH, HEIGHT);
            g.setColor(cc);
            return;
        }


        switch(dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
        }

        move();



    }

    private void randomDir() {

        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }


    public void setGroup(Group group) {
        this.group = group;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }


}
