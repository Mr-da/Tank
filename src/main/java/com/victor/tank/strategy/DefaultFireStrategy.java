package com.victor.tank.strategy;

import com.victor.tank.*;
import com.victor.tank.decorator.RectDecorator;
import com.victor.tank.decorator.TailDecorator;

public  class DefaultFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank t) {
        int Bx = t.x+Tank.WIDTH/2- Bullet.WIDTH/2;
        int By = t.y+Tank.HEIGHT/2-Bullet.HEIGHT/2;
        //list add一次子弹，add一次装饰器
        //Bullet bullet = new Bullet(Bx, By, t.getDir(), t.group);
        //objects.get(i).paint()->rect.paint(tail)->paint(bullet)==
        //最后面那个paint方法里面paint游戏实体类之后x/y已改变
        GameModel.getInstance().add(
                new Bullet(Bx, By, t.getDir(), t.group));//子弹被画了两次
        if(t.group == GroupEnum.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
