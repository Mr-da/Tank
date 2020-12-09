package com.victor.tank.abstractFactory;

import com.victor.tank.*;

import java.awt.*;
import java.util.Random;

public class RectTank extends BaseTank {
    Dir dir;
    TankFrame tf;
    public   int SPEED = PropertyMgr.getInt("tankSpeed");
    private boolean moving =  true;

    public static  int WIDTH = 50;
    public static  int HEIGHT= 50;
    public Rectangle rect2 = new Rectangle(this.x, this.y, RectTank.WIDTH, RectTank.HEIGHT);
    Random random = new Random();
    FireStrategy fs;

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


    @Override
    public Rectangle getRect2() {
        return rect2;
    }

    public RectTank(int x, int y, Dir dir, TankFrame tf, GroupEnum group){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        if (group==GroupEnum.GOOD){
            fs = new FourDirFireStrategy();
        }else {
            fs = new DefaultFireStrategy();
        }
    }

    public void paint(Graphics g){

        Color c = g.getColor();
        g.setColor(Color.BLUE);
        g.fillRect(x,y,WIDTH,HEIGHT);
        g.setColor(c);
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
                default:
                    break;
            }
        }

        if (this.group==GroupEnum.BAD ){
            if(random.nextInt(100)>98) fire(this);
            this.SPEED = 2;
            if (random.nextInt(100)>95)randomDir();
        }
        //边界
        boundCheck();
        rect2.x = this.x;rect2.y = this.y;
    }

    private void boundCheck() {
        if (x<0) x = 0;
        if (y<30) y = 30;
        if (x>TankFrame.FRAME_WIDTH- RectTank.WIDTH -2)     x = TankFrame.FRAME_WIDTH- RectTank.WIDTH -2;
        if (y>TankFrame.FRAME_HEIGHT- RectTank.HEIGHT-2)     y = TankFrame.FRAME_HEIGHT- RectTank.HEIGHT-2;
    }


    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Dir getDir() {
        return dir;
    }


    //发射子弹
    public void fire(RectTank t) {
        int Bx = t.x+Tank.WIDTH/2-Bullet.WIDTH/2;
        int By = t.y+Tank.HEIGHT/2-Bullet.HEIGHT/2;
        tf.gf.createBullet(Bx,By,t.getDir(),t.tf,t.group);
        if(t.group == GroupEnum.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }

    public void die() {
        tf.enemies.remove(this);
    }


}
