package com.victor.tank.observer;

import java.io.Serializable;

public interface FireEventListener extends Serializable {
    void handleEvent(TankFireEvent e);
}
