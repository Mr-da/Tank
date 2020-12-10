package com.victor.tank.cor;

import com.victor.tank.*;

public class TankBulletCollide implements Collider {
    GameModel gm;
    @Override
    public void collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank){
            collideWith((Bullet) o1,(Tank) o2);
        }else if (o1 instanceof Tank && o2 instanceof Bullet){
            collideWith((Bullet) o2,(Tank) o1);
        }else {
            return;
        }
    }

    public TankBulletCollide(GameModel gm){
        this.gm = gm;
    }

    void collideWith(Bullet b, Tank t){
        if (t.group!=b.getGroup()){
            if(b.rect1.intersects(t.rect2)) {
                t.die();
                b.die();
                int eX = t.getX()+Tank.WIDTH- Explode.WIGHT;
                int eY = t.getY()+Tank.HEIGHT-Explode.HEIGHT;
                gm.objects.add(new Explode(eX,eY,gm));
            }
        }
    }
}
