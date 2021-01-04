package com.victor.tank.observer;

import com.victor.tank.Tank;

public class TankFireObserver implements FireEventListener{
    @Override
    public void handleEvent(TankFireEvent e) {
        Tank tank = e.getSource();
        tank.fire();
    }
}
