package com.example.demo.aop;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class CommonCfgProp {
    private static final Properties properties = new Properties();
    private  String file;
    private CommonCfgProp(String file){
        try {
            this.file=file;
            properties.load(CommonCfgProp.class.getClassLoader().getResourceAsStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static CommonCfgProp CommonCfgUtil = null;
    public static CommonCfgProp getInstance(String file){
        if(CommonCfgUtil==null){
            synchronized (CommonCfgProp.class){
                if(CommonCfgUtil==null){
                    CommonCfgUtil = new CommonCfgProp(file);
                }
            }
        }
        return CommonCfgUtil;
    }

    public Map<Object, Object> getConfig(){
        return properties;
    }

    public synchronized boolean setConfig(String key,String value){
        if(properties.containsKey(key)){
            String property = properties.getProperty(key);
            try {
                properties.setProperty(key,value);
                properties.store(new FileWriter(CommonCfgProp.class.getClassLoader().getResource("common.properties").getFile()),null);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                properties.setProperty(key,property);
            }
        }
        return false;
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }

}
