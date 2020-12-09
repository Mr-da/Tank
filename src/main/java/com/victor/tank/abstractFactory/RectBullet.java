package com.victor.tank.abstractFactory;

import com.victor.tank.*;

import java.awt.*;

public class RectBullet extends BaseBullet {
    public static final int SPEED = PropertyMgr.getInt("bulletSpeed");
    public static final int WIDTH = 20;
    public static final int HEIGHT= 20;
    public int x,y;
    public Dir dir;//方向
    public TankFrame tf;
    public GroupEnum group;

    private boolean living = true;//是否存活
    private Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);



    public GroupEnum getGroup() {
        return group;
    }


    public RectBullet(int x, int y, Dir dir, TankFrame tf, GroupEnum group){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        tf.bullets.add(this);
    }

    public void paint(Graphics g){
        if(!living) tf.bullets.remove(this);//越界删掉

        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillOval(x,y,WIDTH,HEIGHT);//画圆
        g.setColor(c);

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
    public void collideWith(BaseTank tank) {
        if (tank.group!=this.getGroup()){
            if(this.rect1.intersects(tank.getRect2())) {
                tank.die();
                this.die();
                int eX = tank.x+RectTank.WIDTH-RectExplode.WIDTH;
                int eY = tank.y+RectTank.HEIGHT-RectExplode.HEIGHT;
                tf.explodes.add(tf.gf.createExplode(eX,eY,tf));
            }
        }
    }

    private void die() {
        this.living = false;
    }
}
