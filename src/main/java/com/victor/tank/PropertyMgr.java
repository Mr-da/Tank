package com.victor.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
    static Properties properties = new Properties();
    public static String getString(String s){
        try {
             properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
             return (String)properties.get(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static int getInt(String s){
        try {
            properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
            return Integer.parseInt((String)properties.get(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static void main(String[] args) {
        System.out.println(new PropertyMgr().getString("initTankCount"));
        System.out.println(new PropertyMgr().getInt("frameWidth"));
        System.out.println(new PropertyMgr().getInt("tankSpeed"));
    }
}
