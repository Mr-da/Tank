package com.victor.tank.strategy;

import com.victor.tank.Tank;

import java.io.Serializable;

public interface FireStrategy extends Serializable {
    void fire(Tank t);
}
