package com.victor.tank;

import java.awt.*;

public abstract class GameObject {
    public int x,y;
    int width,height;
    public abstract void paint(Graphics g);
}
