package com.victor.tank;

import java.awt.*;

public class Bullet {
    public static final int SPEED = PropertyMgr.getInt("bulletSpeed");
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT=ResourceMgr.bulletD.getHeight();
    private int x,y;
    private Dir dir;//方向
    private TankFrame tf;
    private GroupEnum group;

    private boolean living = true;//是否存活
    private Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);



    public GroupEnum getGroup() {
        return group;
    }

    public void setGroup(GroupEnum g) {
        this.group = g;
    }

    public Bullet(int x, int y, Dir dir, TankFrame tf, GroupEnum group){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        tf.bullets.add(this);
    }

    public void paint(Graphics g){
        if(!living) tf.bullets.remove(this);//越界删掉
//        Color c = g.getColor();
//        g.setColor(Color.RED);
//        g.fillOval(x,y,WIDTH,HEIGHT);//画圆
//        g.setColor(c);
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
        if (x > tf.FRAME_WIDTH || y > tf.FRAME_HEIGHT || x<0 || y<0) this.living = false;//越界死亡
        rect1.x = this.x;rect1.y = this.y;
    }

    //碰撞
    public void collideWith(Tank tank) {
        if (tank.getGroup()!=this.getGroup()){
            if(this.rect1.intersects(tank.rect2)) {
                tank.die();
                this.die();
                int eX = tank.getX()+Tank.WIDTH-Explode.WIGHT;
                int eY = tank.getY()+Tank.HEIGHT-Explode.HEIGHT;
                tf.explodes.add(new Explode(eX,eY,tf));
            }
        }
    }

    private void die() {
        this.living = false;
    }
}
