package com.victor.tank;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Explode extends GameObject{
    public static int WIGHT = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
    private int x,y;
    private boolean living;
    private int step = 0;
    private GameModel gm;


    public Explode(int x, int y, GameModel gm){
        this.x = x;
        this.y = y;
        this.gm = gm;
        new Thread(()->new Audio("audio/explode.wav").play()).start();

        gm.objects.add(this);
    }

    public void paint(Graphics g){
        if (step<ResourceMgr.explodes.length) g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        else gm.objects.remove(this);
    }

}
