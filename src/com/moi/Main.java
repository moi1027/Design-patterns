package com.moi;

import com.moi.tank.*;


public class Main {

    public static void main(String[] args){
        TankFrame tf = TankFrame.INSTANCE;
        new Thread(()->new Audio("audio/war1.wav").loop()).start();
        tf.addTank(new Tank(500, 500, Dir.DOWN, Group.BAD, tf));

        new Thread(()-> {
            while(true) {
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tf.repaint();
            }
        }).start();

    }
}
