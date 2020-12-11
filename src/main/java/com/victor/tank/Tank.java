package com.victor.tank;

import com.victor.tank.strategy.DefaultFireStrategy;
import com.victor.tank.strategy.FireStrategy;
import com.victor.tank.strategy.FourDirFireStrategy;

import java.awt.*;
import java.util.Random;

public class Tank extends GameObject {
    public int x, y;
    public int oldX, oldY;
    public Dir dir;
    public GroupEnum group;
    public int SPEED = PropertyMgr.getInt("tankSpeed");
    private boolean moving = true;

    public static int WIDTH = ResourceMgr.goodTankD.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankD.getHeight();
    public Rectangle rect2 = new Rectangle();
    Random random = new Random();
    FireStrategy fs;

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }


    public Tank(int x, int y, Dir dir, GroupEnum group) {
        this.x = x;
        this.y = y;
        rect2.x = x;
        rect2.y = y;
        rect2.width = WIDTH;
        rect2.height = HEIGHT;
        //this.oldX = x;
        //this.oldY = y;
        this.dir = dir;
        this.group = group;
        if (group == GroupEnum.GOOD) {
            String goodStrategy = PropertyMgr.getString("fourDirFire");
            try {
                fs = (FourDirFireStrategy) Class.forName(goodStrategy).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //fs = new FourDirFireStrategy();
        } else {
            String badStrategy = PropertyMgr.getString("defaultFire");
            try {
                fs = (DefaultFireStrategy) Class.forName(badStrategy).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //fs = new DefaultFireStrategy();
        }
    }

    public void paint(Graphics g) {

        switch (dir) {//每个方向显示不同的图片
            case LEFT:
                g.drawImage(this.group == GroupEnum.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == GroupEnum.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == GroupEnum.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == GroupEnum.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;

        }
        oldX = x;
        oldY = y;//下次移动之前记录位置
        move();
    }

    private void randomDir() {
        dir = Dir.values()[random.nextInt(4)];
    }

    private void move() {
        if (moving) {
            switch (dir) {
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

        if (this.group == GroupEnum.BAD) {
            if (random.nextInt(100) > 98) fs.fire(this);
            this.SPEED = 2;
            if (random.nextInt(100) > 95) randomDir();
        }
        //边界
        boundCheck();
        rect2.x = this.x;
        rect2.y = this.y;
    }

    private void boundCheck() {
        if (x < 0) x = 0;
        if (y < 30) y = 30;
        if (x > TankFrame.FRAME_WIDTH - Tank.WIDTH - 2) x = TankFrame.FRAME_WIDTH - Tank.WIDTH - 2;
        if (y > TankFrame.FRAME_HEIGHT - Tank.HEIGHT - 2) y = TankFrame.FRAME_HEIGHT - Tank.HEIGHT - 2;
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
        fs.fire(this);
    }

    public void die() {
        GameModel.getInstance().objects.remove(this);
    }

    public void back() {
        x = oldX;
        y = oldY;
    }


}
