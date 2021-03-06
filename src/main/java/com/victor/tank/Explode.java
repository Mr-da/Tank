package com.victor.tank;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Explode {
    public static int WIGHT = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
    private int x,y;
    private boolean living;
    private int step = 0;
    private TankFrame tf;


    public Explode(int x,int y,TankFrame tf){
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g){
        if (step<ResourceMgr.explodes.length) g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        else tf.explodes.remove(this);
    }

}
