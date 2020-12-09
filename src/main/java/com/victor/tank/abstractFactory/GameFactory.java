package com.victor.tank.abstractFactory;

import com.victor.tank.Dir;
import com.victor.tank.GroupEnum;
import com.victor.tank.TankFrame;

public abstract class GameFactory {
    public abstract BaseTank createTank(int x, int y, Dir dir, TankFrame tf, GroupEnum group);

    public abstract BaseBullet createBullet(int x, int y, Dir dir, TankFrame tf, GroupEnum group);


    public abstract BaseExplode createExplode(int x, int y, TankFrame tf);
}
