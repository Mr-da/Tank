package com.victor.tank.cor;

import com.victor.tank.GameObject;
import com.victor.tank.Tank;
import com.victor.tank.Wall;

public class TankWallCollide implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall){
            collideWith((Tank)o1,(Wall) o2);
        }else if (o1 instanceof Wall && o2 instanceof Tank){
            collideWith((Tank) o2,(Wall) o1);
        }
        return false;
    }

    void collideWith(Tank t, Wall w){
            if(t.rect2.intersects(w.rect)) {
               t.back();
            }
    }

}
