package com.moi.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @program:
 * @description: 资源管理器
 * @author: moi
 * @create: 2021/1/18 16:20
 **/
public class PropertiesMgr {

    private static PropertiesMgr propertiesMgr = new PropertiesMgr();

    private static Properties props = new Properties();

    private PropertiesMgr(){}

    static {
        try {
            //指定配置文件的路径
            props.load(PropertiesMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PropertiesMgr getInstance(){
        return propertiesMgr;
    }

    public Object get(Object key){
        return props.get(key);
    }

    public static void main(String[] args) {
        System.out.println(propertiesMgr.get("initTankCount"));
    }




}
