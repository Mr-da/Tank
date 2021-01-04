package com.victor.tank;

import java.awt.*;
import java.io.Serializable;

public abstract class GameObject implements Serializable {
    public int x,y;
    public int width,height;
    public abstract void paint(Graphics g);

    public abstract int getWidth();

    public abstract int getHeight();
}
