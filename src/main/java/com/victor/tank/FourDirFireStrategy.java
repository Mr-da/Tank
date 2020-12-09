package com.victor.tank;

public  class FourDirFireStrategy implements FireStrategy{

    @Override
    public void fire(Tank t) {
        int Bx = t.x+Tank.WIDTH/2-Bullet.WIDTH/2;
        int By = t.y+Tank.HEIGHT/2-Bullet.HEIGHT/2;
        for (Dir dir : Dir.values()) {
            new Bullet(Bx,By,dir,t.tf,t.group);
        }
        if(t.group == GroupEnum.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
