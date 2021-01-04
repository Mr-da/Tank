package com.victor.tank.observer;

import com.victor.tank.Tank;

public class TankFireEvent {
    Tank tank;
    public Tank getSource() {
        return tank;
    }
    public TankFireEvent(Tank tank){
        this.tank = tank;
    }
}
