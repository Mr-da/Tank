package com.victor.tank.strategy;

import com.victor.tank.Audio;
import com.victor.tank.Bullet;
import com.victor.tank.GroupEnum;
import com.victor.tank.Tank;
import com.victor.tank.strategy.FireStrategy;

public  class DefaultFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank t) {
        int Bx = t.x+Tank.WIDTH/2- Bullet.WIDTH/2;
        int By = t.y+Tank.HEIGHT/2-Bullet.HEIGHT/2;
        new Bullet(Bx,By,t.getDir(),t.gm,t.group);
        if(t.group == GroupEnum.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
