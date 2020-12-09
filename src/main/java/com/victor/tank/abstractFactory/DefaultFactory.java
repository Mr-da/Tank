package com.victor.tank.abstractFactory;

import com.victor.tank.*;

public class DefaultFactory extends GameFactory {
    @Override
    public BaseTank createTank(int x, int y, Dir dir, TankFrame tf, GroupEnum group) {
        return new Tank( x, y, dir, tf, group);
    }

    @Override
    public BaseBullet createBullet(int x,int y,Dir dir,TankFrame tf,GroupEnum group) {
        return new Bullet(x,y,dir,tf,group);
    }

    @Override
    public BaseExplode createExplode(int x,int y,TankFrame tf) {
        return new Explode(x,y,tf);
    }
}
