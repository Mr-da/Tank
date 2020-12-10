package com.victor.tank;

import java.awt.*;
import java.util.ArrayList;

public class GameModel {
    
    Tank myTank = new Tank(200,200,Dir.DOWN,this,GroupEnum.GOOD);//主战坦克
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<Tank> enemies = new ArrayList<Tank>();
    ArrayList<Explode> explodes = new ArrayList<Explode>();

    void initEnemies(){
        //设置敌军数量
        for (int i=0;i<PropertyMgr.getInt("initTankCount");i++){
            Tank enemy = new Tank(100+i*60,100,Dir.DOWN,this,GroupEnum.BAD);
            enemies.add(enemy);
        }
    }
    public GameModel(){
        initEnemies();
    }

    public void paint(Graphics g) {
        //显示子弹数
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量："+bullets.size(),10,60);
        g.drawString("敌军的数量："+enemies.size(),10,75);
        g.drawString("爆炸的数量："+explodes.size(),10,90);
        g.setColor(c);

        //画出主战坦克
        myTank.paint(g);//面向对象@，让坦克自己画自己，定义速度方向
//        if (enemies.size()<3){
//            for (int i=0;i<PropertyMgr.getInt("rebornEnemy");i++){
//                Tank enemy = new Tank(100+i*100,100,Dir.DOWN,this,GroupEnum.BAD);
//                enemies.add(enemy);
//            }
//        }

        //画出子弹
        for (int i=0;i<bullets.size();i++){
            //子弹碰撞！敌军！销毁
            //todo
            for (int j = 0; j <enemies.size(); j++) {
                bullets.get(i).collideWith(enemies.get(j));//相遇则>敌军and子弹die>
            }
            bullets.get(i).paint(g);
        }

        //画出敌军(已经筛选掉碰撞炸掉的坦克)
        for (int i = 0; i < enemies.size(); i++) {
            Tank t = enemies.get(i);
            t.paint(g);
        }

        //爆炸
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }
    }
}
