package com.victor.tank;

import com.victor.tank.decorator.GODecorator;
import com.victor.tank.decorator.RectDecorator;

import java.awt.*;

public class Bullet extends GameObject{
    public static final int SPEED = PropertyMgr.getInt("bulletSpeed");
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT=ResourceMgr.bulletD.getHeight();
    private Dir dir;//方向
    private GroupEnum group;

    private boolean living = true;//是否存活
    public Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);



    public GroupEnum getGroup() {
        return group;
    }

    public Bullet(int x, int y, Dir dir, GroupEnum group){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        GameModel.getInstance().add(this);
    }



    public void paint(Graphics g){
        if(!living) GameModel.getInstance().remove(this);//越界删掉
        switch(dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }
        move();
    }

    private void move() {
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
        if (x > TankFrame.FRAME_WIDTH || y > TankFrame.FRAME_HEIGHT || x<0 || y<0) this.living = false;//越界死亡
        rect1.x = this.x;rect1.y = this.y;
    }



    public void die() {
        this.living = false;
    }

    @Override
    public int getHeight() {
        return Bullet.HEIGHT;
    }
    @Override
    public int getWidth(){
        return Bullet.WIDTH;
    }
}
