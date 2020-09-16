package com.example.demo.aop;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class CommonPropertiesUtil {
    private static final Properties properties = new Properties();
    static {
        try {
            properties.load(CommonPropertiesUtil.class.getClassLoader().getResourceAsStream("common.properties1"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private CommonPropertiesUtil(){}
    private static CommonPropertiesUtil CommonPropertiesUtil = null;
    public static CommonPropertiesUtil getInstance(){
        if(CommonPropertiesUtil==null){
            synchronized (CommonPropertiesUtil.class){
                if(CommonPropertiesUtil==null){
                    CommonPropertiesUtil = new CommonPropertiesUtil();
                }
            }
        }
        return CommonPropertiesUtil;
    }

    public Map<Object, Object> getConfig(){
        return properties;
    }

    public synchronized boolean setConfig(String key,String value){
        if(properties.containsKey(key)){
            String property = properties.getProperty(key);
            try {
                properties.setProperty(key,value);
                properties.store(new FileWriter(CommonPropertiesUtil.class.getClassLoader().getResource("common.properties").getFile()),null);
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
