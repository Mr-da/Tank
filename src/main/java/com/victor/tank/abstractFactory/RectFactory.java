package com.victor.tank.abstractFactory;

import com.victor.tank.*;

public class RectFactory extends GameFactory{
    @Override
    public BaseTank createTank(int x, int y, Dir dir, TankFrame tf, GroupEnum group) {
        return new RectTank( x, y, dir, tf, group);
    }

    @Override
    public BaseBullet createBullet(int x,int y,Dir dir,TankFrame tf,GroupEnum group) {
        return new RectBullet(x,y,dir,tf,group);
    }

    @Override
    public BaseExplode createExplode(int x,int y,TankFrame tf) {
        return new RectExplode(x,y,tf);
    }
}
