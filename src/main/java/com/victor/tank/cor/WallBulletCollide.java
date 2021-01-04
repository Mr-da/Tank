package com.victor.tank.cor;

import com.victor.tank.*;

public class WallBulletCollide implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall){
            return collideWith((Bullet)o1,(Wall) o2);
        }else if (o1 instanceof Wall && o2 instanceof Bullet){
            return collideWith((Bullet) o2,(Wall) o1);
        }
        return false;
    }

    boolean collideWith(Bullet b, Wall w){
            if(b.rect1.intersects(w.rect)) {
                b.die();
                //explode
                //int eX = b.x - Explode.WIGHT/2+Bullet.WIDTH/2;
                //int eY = b.y - Explode.HEIGHT;
                //new Explode(eX,eY);
               return true;
            }
            return false;
    }

}
