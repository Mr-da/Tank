package com.victor.tank.decorator;

import com.victor.tank.GameObject;

import java.awt.*;

public abstract class GODecorator extends GameObject {
    GameObject o;
    public GODecorator(GameObject o){
        this.o = o;
    }
    public void paint(Graphics g){};
}
