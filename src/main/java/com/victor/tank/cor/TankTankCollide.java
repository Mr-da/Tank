package com.victor.tank.cor;

import com.victor.tank.*;

public class TankTankCollide implements Collider{
    GameModel gm;
    public TankTankCollide(GameModel gm){
        this.gm = gm;
    }
    @Override
    public void collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank){
            collideWith((Tank)o1,(Tank) o2);
        }
    }

    void collideWith(Tank t1, Tank t2){
            if(t1.rect2.intersects(t2.rect2)) {
                turnBack(t1);
                turnBack(t2);
            }
    }

    private void turnBack(Tank t) {
        switch (t.dir){
            case UP:
                t.dir = Dir.DOWN;
                break;
            case DOWN:
                t.dir = Dir.UP;
                break;
            case LEFT:
                t.dir = Dir.RIGHT;
                break;
            case RIGHT:
                t.dir = Dir.LEFT;
                break;
            default:
                break;

        }
    }

}
