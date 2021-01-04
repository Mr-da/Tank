package com.victor.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

public class TankFrame extends Frame {
    public final static int FRAME_WIDTH = PropertyMgr.getInt("frameWidth"),FRAME_HEIGHT = PropertyMgr.getInt("frameHeight");//主窗口
    GameModel gm = GameModel.getInstance();

    public TankFrame(){
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);
        //监听键盘事件
        this.addKeyListener(new MyKeyListener());
        //窗口关闭，程序正常退出
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {//解决子弹闪烁问题
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
        gm.paint(g);
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
                    gm.myTank.fireEventHappen();//按下Ctrl就发射一颗子弹
                    break;
                case KeyEvent.VK_S:
                    gm.save();
                case KeyEvent.VK_L:
                    gm.load();
                default:
                    break;
            }
            setTankDir();

            //new Thread(()->new Audio("audio/tank_move.wav").play()).start();

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
            Tank myTank = gm.myTank;
            if (!left && !right && !up && !down) myTank.setMoving(false);
            else {
                gm.myTank.setMoving(true);

                if (left) myTank.setDir(Dir.LEFT);
                if (right) myTank.setDir(Dir.RIGHT);
                if (up) myTank.setDir(Dir.UP);
                if (down) myTank.setDir(Dir.DOWN);
            }
        }


    }


}
