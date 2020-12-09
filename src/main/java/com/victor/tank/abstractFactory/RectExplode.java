package com.victor.tank.abstractFactory;

import com.victor.tank.Audio;
import com.victor.tank.ResourceMgr;
import com.victor.tank.TankFrame;

import java.awt.*;

public class RectExplode extends BaseExplode {
    public static int WIDTH = 50;
    public static int HEIGHT = 50;
    private int x,y;
    private int step = 0;
    private TankFrame tf;


    public RectExplode(int x, int y, TankFrame tf){
        this.x = x;
        this.y = y;
        this.tf = tf;
        //new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,WIDTH,HEIGHT);
        g.setColor(c);
        tf.explodes.remove(this);
    }

}
