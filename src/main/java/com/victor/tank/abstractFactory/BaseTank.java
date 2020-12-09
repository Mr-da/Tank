package com.victor.tank.abstractFactory;


import com.victor.tank.GroupEnum;

import java.awt.*;

public class BaseTank {
    public GroupEnum group;
    public int x,y;
    public Rectangle rect2;

    public Rectangle getRect2() {
        return rect2;
    }

    public void paint(Graphics g) {
    }

    public void die() {
    }
}
