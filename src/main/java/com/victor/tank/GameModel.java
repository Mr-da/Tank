package com.victor.tank;

import com.victor.tank.cor.Collider;
import com.victor.tank.cor.ColliderChain;

import java.awt.*;
import java.util.ArrayList;

public class GameModel {
    private GameModel(){};
    private static GameModel gameModel = new GameModel();
    public static GameModel getInstance(){
        return gameModel;
    }
    static {
        gameModel.init();
    }

    Tank myTank = new Tank(200,200,Dir.DOWN,GroupEnum.GOOD);//主战坦克
    public ArrayList<GameObject> objects = new ArrayList<>();
    public Collider chain = new ColliderChain();

    void init(){
        //设置敌军数量
        for (int i=0;i<PropertyMgr.getInt("initTankCount");i++){
            Tank enemy = new Tank(30+i*100,50,Dir.DOWN, GroupEnum.BAD);
            objects.add(enemy);
        }
        // 初始化墙
        new Wall(150, 150, 200, 40);
        //objects.add(new Wall(550, 150, 200, 40));
        new Wall(300, 300, 40, 200);
        new Wall(550, 300, 40, 200);
    }


    public void paint(Graphics g) {
        //画出主战坦克
        myTank.paint(g);//面向对象@，让坦克自己画自己，定义速度方向
        //碰撞判断-责任链模式
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i+1; j < objects.size() ; j++) {
                if (chain.collide(objects.get(i),objects.get(j))) break;
            }
        }
        //画出所有实体(已经筛选掉碰撞炸掉的实体)
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }
    }
}
