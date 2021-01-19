package com.moi;

import com.moi.tank.*;

import java.util.Random;


public class Main {

    public static void main(String[] args){
        TankFrame tf = TankFrame.INSTANCE;
        new Thread(()->new Audio("audio/war1.wav").loop()).start();
        for (int i = 0; i < 10; i++) {
            tf.addTank(tf.abstractFactory.createTank(new Random().nextInt(1080), new Random().nextInt(960), Dir.DOWN, Group.BAD,true,tf));
        }


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
