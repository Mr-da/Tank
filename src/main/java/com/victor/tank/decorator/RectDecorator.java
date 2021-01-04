package com.victor.tank.decorator;

import com.victor.tank.Bullet;
import com.victor.tank.GameModel;
import com.victor.tank.GameObject;

import java.awt.*;

public class RectDecorator extends GODecorator{
    int step = 0;
    public RectDecorator(GameObject o) {
        super(o);
    }

    @Override
    public void paint(Graphics g) {
        step++;
        this.x = o.x;
        this.y = o.y;
        o.paint(g);//调用后面的paint的时候调用了实体类move方法位置改变了
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawRect(o.x,o.y, o.getWidth(),o.getHeight());
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
