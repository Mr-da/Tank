package com.victor.tank.abstractFactory;

import com.victor.tank.Dir;
import com.victor.tank.GroupEnum;
import com.victor.tank.TankFrame;

import java.awt.*;

public class BaseBullet {
    int x,y;
    Dir dir;//方向
    TankFrame tf;
    GroupEnum group;
    public void collideWith(BaseTank baseTank) {
    }

    public void paint(Graphics g) {
    }
}
