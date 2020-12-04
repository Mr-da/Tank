package com.victor.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x,y;
    private Dir dir;
    private TankFrame tf;



    private GroupEnum group;
    public   int SPEED = 4;

    public static final int WIDTH = ResourceMgr.tankU.getWidth();
    public static final int HEIGHT=ResourceMgr.tankU.getHeight();
    Random random = new Random();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }



    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private boolean moving =  false;

    public Tank(int x,int y,Dir dir,TankFrame tf,GroupEnum group){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
    }

    public void paint(Graphics g){
        if(this.group==GroupEnum.GOOD){
            switch (dir){//每个方向显示不同的图片
                case LEFT:
                    g.drawImage(ResourceMgr.tankL, x, y, null);
                    break;
                case RIGHT:
                    g.drawImage(ResourceMgr.tankR, x, y, null);
                    break;
                case DOWN:
                    g.drawImage(ResourceMgr.tankD, x, y, null);
                    break;
                case UP:
                    g.drawImage(ResourceMgr.tankU, x, y, null);
                    break;

            }
        }

        if(this.group==GroupEnum.BAD){
            switch (dir){//每个方向显示不同的图片
                case LEFT:
                    g.drawImage(ResourceMgr.tankL, x, y, null);
                    break;
                case RIGHT:
                    g.drawImage(ResourceMgr.tankR, x, y, null);
                    break;
                case DOWN:
                    g.drawImage(ResourceMgr.tankD, x, y, null);
                    break;
                case UP:
                    g.drawImage(ResourceMgr.tankU, x, y, null);
                    break;

            }
        }

        move();

    }

    private void randomDir() {
        dir = Dir.values()[random.nextInt(4)];
    }

    private void move() {
        if (moving){
            switch (dir){
                case LEFT:
                    x -= SPEED;
                    break;
                case RIGHT:
                    x += SPEED;
                    break;
                case DOWN:
                    y += SPEED;
                    break;
                case UP:
                    y -= SPEED;
                    break;
            }
        }

        if (this.group==GroupEnum.BAD && random.nextInt(100)>95){
            fire();
            this.SPEED = 2;
            this.moving = true;
            randomDir();
        }

        boundCheck();
    }

    private void boundCheck() {
        if (x<0) x = 0;
        if (y<30) y = 30;
        //if (x>TankFrame.FRAME_WIDTH- Tank.WIDTH -2)     x = TankFrame.WIDTH- Tank.WIDTH -2;
        if (y>TankFrame.FRAME_HEIGHT-Tank.HEIGHT-2)     y = TankFrame.FRAME_HEIGHT-Tank.HEIGHT-2;
    }


    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Dir getDir() {
        return dir;
    }
    public Explode getExplode() {
        return explode;
    }

    public void setExplode(Explode explode) {
        this.explode = explode;
    }

    private Explode explode;

    public GroupEnum getGroup() {
        return group;
    }

    public void setGroup(GroupEnum group) {
        this.group = group;
    }
    //发射子弹
    public void fire() {
        int Bx = this.x+WIDTH/2-Bullet.WIDTH/2;
        int By = this.y+HEIGHT/2-Bullet.HEIGHT/2;
        tf.bullets.add(new Bullet(Bx,By,this.getDir(),tf,this.group));
        if(this.group == GroupEnum.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }

    public void die() {
        tf.enemies.remove(this);
    }


}
