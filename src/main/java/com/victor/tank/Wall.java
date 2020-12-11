package com.victor.tank;

import java.awt.*;

public class Wall extends GameObject{

    public Rectangle rect=new Rectangle();

    public Wall(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        rect.x = x;
        rect.y = y;
        rect.width = width;
        rect.height = height;
        GameModel.getInstance().objects.add(this);
    }

    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.GRAY);
        g.fillRect(x,y,width,height);
        g.setColor(c);
    }

}
