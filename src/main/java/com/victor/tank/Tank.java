package com.victor.tank;

import java.awt.*;

public class Tank {
    private int x,y;
    private Dir dir;
    private TankFrame tf;

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

    private GroupEnum group;
    private static final int SPEED = 10;

    public static final int WIDTH = ResourceMgr.tankD.getWidth();
    public static final int HEIGHT=ResourceMgr.tankD.getHeight();

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
        move();//通过按钮让它动
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
    }



    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Dir getDir() {
        return dir;
    }
    //发射子弹
    public void fire() {
        int Bx = this.x+WIDTH/2-Bullet.WIDTH/2;
        int By = this.y+HEIGHT/2-Bullet.HEIGHT/2;
        tf.bullets.add(new Bullet(Bx,By,this.getDir(),tf,this.group));
    }

    public void die() {
        tf.enemies.remove(this);
    }
}
