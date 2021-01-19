package com.moi.tank;

import com.moi.tank.abstractfactory.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

/**
 * @program:
 * @description:
 * @author: moi
 * @create: 2020/12/14 16:50
 **/
public class TankFrame  extends Frame {

    //��һ������ ����ʽ����
    public static final TankFrame INSTANCE = new TankFrame();

    /**
     * ����һ����׼����
     */
    public AbstractFactory abstractFactory = new DefaultFactory();

    //��Ϸ���ڵĿ��
    static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960;

    Random r = new Random();


    BaseTank myTank = abstractFactory.createTank(r.nextInt(GAME_WIDTH), r.nextInt(GAME_HEIGHT), Dir.DOWN, Group.GOOD,false,this);

    Map<UUID, BaseTank> tanks = new HashMap<>();
    public List<BaseBullet> bullets = new ArrayList<>();
    List<BaseExplode> explodes = new ArrayList<>();




    public void addBullet(Bullet b) {
        bullets.add(b);
    }

    public void addTank(BaseTank t) {
        tanks.put(t.getId(), t);
    }

    public BaseTank findTankByUUID(UUID id) {
        return tanks.get(id);
    }

    public BaseBullet findBulletByUUID(UUID id) {
        for(int i=0; i<bullets.size(); i++) {
            if(bullets.get(i).getId().equals(id)) {
                return bullets.get(i);
            }
        }

        return null;
    }

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
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("bullets:" + bullets.size(), 10, 60);
        g.drawString("tanks:" + tanks.size(), 10, 80);
        g.drawString("explodes" + explodes.size(), 10, 100);
        g.setColor(c);

        myTank.paint(g);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        //java8 stream api
        tanks.values().stream().forEach((e)->e.paint(g));

        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }
        //collision detect
        Collection<BaseTank> values = tanks.values();
        for(int i=0; i<bullets.size(); i++) {
            for(BaseTank t : values ) {
                bullets.get(i).collideWith(t);
            }
            bullets.get(i).collideWith(myTank);
        }


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
                    myTank.fire();
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
            Dir dir = myTank.getDir();

            if (!bl && !bu && !br && !bd) {
                myTank.setMoving(false);
                //Client.INSTANCE.send(new TankStopMsg(getMainTank()));
            } else {

                if (bl) {
                    myTank.setDir(Dir.LEFT);
                }
                if (bu) {
                    myTank.setDir(Dir.UP);
                }
                if (br) {
                    myTank.setDir(Dir.RIGHT);
                }
                if (bd) {
                    myTank.setDir(Dir.DOWN);
                }
                //����̹���ƶ�����Ϣ
                //if(!myTank.isMoving())
                    //Client.INSTANCE.send(new TankStartMovingMsg(getMainTank()));

                myTank.setMoving(true);

//                if(dir != myTank.getDir()) {
//                    Client.INSTANCE.send(new TankDirChangedMsg(myTank));
//                }
            }


        }

    }

}
