package com.victor.tank.cor;

import com.victor.tank.GameObject;
import com.victor.tank.PropertyMgr;

import java.util.ArrayList;
import java.util.List;

public class ColliderChain implements Collider{
    List<Collider> collides = new ArrayList<>();
    public ColliderChain(){
        //配置类中的类名通过反射加进list
        String colliders = PropertyMgr.getString("colliders");
        String[] split = colliders.split(",");
        for (String cld : split) {
            try {
                collides.add((Collider) Class.forName(cld).getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public boolean collide(GameObject o1, GameObject o2) {
        return collideWith(o1,o2);
    }

    private boolean collideWith(GameObject o1, GameObject o2) {
        for (int i = 0; i < collides.size(); i++) {
            if (collides.get(i).collide(o1,o2)) return true;//相撞结束此次当前循环
        }
        return false;
    }


}
