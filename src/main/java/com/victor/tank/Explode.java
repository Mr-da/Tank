package com.victor.tank;

import java.awt.*;

public class Explode extends GameObject{
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
    private int x,y;
    private boolean living;
    private int step = 0;


    public Explode(int x, int y){
        this.x = x;
        this.y = y;
        new Thread(()->new Audio("audio/explode.wav").play()).start();
        GameModel.getInstance().add(this);
    }

    public void paint(Graphics g){
        if (step<ResourceMgr.explodes.length) g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        else GameModel.getInstance().remove(this);
    }

    public int getHeight() {
        return HEIGHT;
    }
    public int getWidth(){
        return WIDTH;
    }

}
