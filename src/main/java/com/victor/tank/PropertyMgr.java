package com.victor.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
    static Properties properties = new Properties();
    static void loadConfig(){
        try {
            properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getString(String s){
        loadConfig();
        return (String)properties.get(s);
    }
    public static int getInt(String s){
        loadConfig();
        return Integer.parseInt((String)properties.get(s));
    }
    public static void main(String[] args) {
        System.out.println(new PropertyMgr().getString("initTankCount"));
        System.out.println(new PropertyMgr().getInt("frameWidth"));
        System.out.println(new PropertyMgr().getInt("tankSpeed"));

    }
}
