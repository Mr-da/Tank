package com.victor.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

public class TankFrame extends Frame {
    public final static int FRAME_WIDTH = 1080,FRAME_HEIGHT = 960;//主窗口

    Tank myTank = new Tank(200,200,Dir.DOWN,this,GroupEnum.GOOD);//主战坦克
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<Tank> enemies = new ArrayList<Tank>();
    ArrayList<Explode> explodes = new ArrayList<Explode>();

    private Random random = new Random();

    public TankFrame(){
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);
        //设置敌军数量
        for (int i=0;i<6;i++){
            Tank enemy = new Tank(100+i*60,100,Dir.DOWN,this,GroupEnum.BAD);
            enemies.add(enemy);
        }

        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(FRAME_WIDTH, FRAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
//        g.fillRect(x,y,50,50);
//        x += 10;
        //显示子弹数
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量："+bullets.size(),10,60);
        g.drawString("敌军的数量："+enemies.size(),10,75);
        g.drawString("爆炸的数量："+explodes.size(),10,90);
        g.setColor(c);

        //画出主战坦克
        myTank.paint(g);//面向对象@，让坦克自己画自己，定义速度方向

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
            if (random.nextInt(100)>95) t.fire();//坦克对应>>子弹group
        }

        //爆炸
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

    }




    class MyKeyListener extends KeyAdapter{
        //四个方向是否按下
        boolean left = false;
        boolean right = false;
        boolean up = false;
        boolean down = false;
        @Override
        public void keyPressed(KeyEvent e) {//键盘按下改变方向
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    left = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = true;
                    break;
                case KeyEvent.VK_UP:
                    up = true;
                    break;
                case KeyEvent.VK_DOWN:
                    down = true;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();//按下Ctrl就发射一颗子弹
                    break;
                default:
                    break;
            }
            setTankDir();

        }



        @Override
        public void keyReleased(KeyEvent e) {//键盘松开取消按下的状态
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    left = false;
                case KeyEvent.VK_RIGHT:
                    right = false;
                case KeyEvent.VK_UP:
                    up = false;
                case KeyEvent.VK_DOWN:
                    down = false;
                default:
                    break;
            }
            setTankDir();
        }

        private void setTankDir() {
            if (!left && !right && !up && !down) myTank.setMoving(false);
            else {
                myTank.setMoving(true);

                if (left) myTank.setDir(Dir.LEFT);
                if (right) myTank.setDir(Dir.RIGHT);
                if (up) myTank.setDir(Dir.UP);
                if (down) myTank.setDir(Dir.DOWN);
            }
        }

    }


}
