package com.victor.tank.decorator;

import com.victor.tank.Bullet;
import com.victor.tank.GameObject;

import java.awt.*;

public class TailDecorator extends GODecorator{
    int step = 0;
    public TailDecorator(GameObject o) {
        super(o);
    }

    @Override
    public void paint(Graphics g) {
        step++;
        this.x = o.x;
        this.y = o.y;
        o.paint(g);//调用了paint之后这里改变了x/y
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawLine(this.x,this.y, o.x+o.getWidth(),o.y+o.getHeight());
        g.setColor(c);
    }

    @Override
    public int getWidth() {
        return o.getWidth();
    }

    @Override
    public int getHeight() {
        return o.getHeight();
    }

}
