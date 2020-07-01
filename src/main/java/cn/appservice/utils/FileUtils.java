package cn.appservice.utils;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class FileUtils {
    public Properties getConf(String file) {
        Properties conf   = new Properties();
        URL fileURL=this.getClass().getResource(file);
        System.out.println(fileURL);
        InputStream is=this.getClass().getResourceAsStream(file);
        try {
            conf.load(is);
        } catch (Exception e) {
        }
        return conf;
    }
}
