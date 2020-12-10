package com.victor.tank.cor;

import com.victor.tank.GameModel;
import com.victor.tank.GameObject;

import java.util.ArrayList;
import java.util.List;

public class ColliderChain implements Collider{
    GameModel gm;
    List<Collider> collides = new ArrayList<>();
    public ColliderChain(GameModel gm){
        this.gm = gm;
        collides.add(new TankBulletCollide());
        collides.add(new TankTankCollide());
        System.out.println("chain:"+this+";===gm:"+gm);
    }
    public void collide(GameObject o1, GameObject o2) {
        collideWith(o1,o2);
    }

    private void collideWith(GameObject o1, GameObject o2) {
        for (int i = 0; i < collides.size(); i++) {
            collides.get(i).collide(o1,o2);
        }
    }


}
