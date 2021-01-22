package com.moi.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;

/**
 * @program:
 * @description:
 * @author: moi
 * @create: 2020/12/14 16:50
 **/
public class TankFrame  extends Frame {

    //建一个单例 饿汉式单例
    public static final TankFrame INSTANCE = new TankFrame();
    static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960;

    GameModel gameModel = GameModel.INSTANCE;

    private TankFrame(){
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setVisible(true);
        setTitle("tank war");
        this.addKeyListener(new KeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g){
        gameModel.paint(g);
    }

    class KeyListener extends KeyAdapter{

        Boolean bl = false;
        Boolean br = false;
        Boolean bu = false;
        Boolean bd = false;

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bl = true;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_RIGHT:
                    br = true;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_UP:
                    bu = true;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_DOWN:
                    bd = true;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_CONTROL:
                    gameModel.myTank.fire();
                    break;
            }
            new Thread(()->new Audio("audio/tank_move.wav").play()).start();

        }

        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bl = false;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_RIGHT:
                    br = false;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_UP:
                    bu = false;
                    setMainTankDir();
                    break;
                case KeyEvent.VK_DOWN:
                    bd = false;
                    setMainTankDir();
                    break;
            }
        }


        private void setMainTankDir() {
            //save the old dir
            Dir dir = gameModel.myTank.getDir();

            if (!bl && !bu && !br && !bd) {
                gameModel.myTank.setMoving(false);
                //Client.INSTANCE.send(new TankStopMsg(getMainTank()));
            } else {

                if (bl) {
                    gameModel.myTank.setDir(Dir.LEFT);
                }
                if (bu) {
                    gameModel.myTank.setDir(Dir.UP);
                }
                if (br) {
                    gameModel.myTank.setDir(Dir.RIGHT);
                }
                if (bd) {
                    gameModel.myTank.setDir(Dir.DOWN);
                }
                //发出坦克移动的消息
                //if(!myTank.isMoving())
                    //Client.INSTANCE.send(new TankStartMovingMsg(getMainTank()));

                gameModel.myTank.setMoving(true);

//                if(dir != myTank.getDir()) {
//                    Client.INSTANCE.send(new TankDirChangedMsg(myTank));
//                }
            }


        }

    }

}
