package com.victor.tank;

import com.victor.tank.cor.Collider;
import com.victor.tank.cor.ColliderChain;

import java.awt.*;
import java.util.ArrayList;

public class GameModel {
    
    Tank myTank = new Tank(200,200,Dir.DOWN,this,GroupEnum.GOOD);//主战坦克
    public ArrayList<GameObject> objects = new ArrayList<>();
    public Collider chain = new ColliderChain();



    void initEnemies(){
        //设置敌军数量
        for (int i=0;i<PropertyMgr.getInt("initTankCount");i++){
            Tank enemy = new Tank(30+i*100,100,Dir.DOWN,this,GroupEnum.BAD);
            objects.add(enemy);
        }
    }
    public GameModel(){
        initEnemies();
    }

    public void paint(Graphics g) {
        //显示子弹数
//        Color c = g.getColor();
//        g.setColor(Color.WHITE);
//        g.drawString("子弹的数量："+bullets.size(),10,60);
//        g.drawString("敌军的数量："+enemies.size(),10,75);
//        g.drawString("爆炸的数量："+explodes.size(),10,90);
//        g.setColor(c);

        //画出主战坦克
        myTank.paint(g);//面向对象@，让坦克自己画自己，定义速度方向

        //画出子弹
//        for (int i=0;i<bullets.size();i++){
//            //子弹碰撞！敌军！销毁
//            for (int j = 0; j <enemies.size(); j++) {
//                bullets.get(i).collideWith(enemies.get(j));//相遇则>敌军and子弹die>
//            }
//            bullets.get(i).paint(g);
//        }
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i+1; j < objects.size() ; j++) {
                if (chain.collide(objects.get(i),objects.get(j))) break;
            }
        }

        //画出敌军(已经筛选掉碰撞炸掉的坦克)
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

//        //爆炸
//        for (int i = 0; i < explodes.size(); i++) {
//            explodes.get(i).paint(g);
//        }
    }
}
