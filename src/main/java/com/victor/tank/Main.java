package com.victor.tank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();//1.新建一个面板，面板调用画笔
        //2.自动刷新画笔
        while (true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
