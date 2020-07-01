package cn.appservice.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class MyBatisUtil {
    private static SqlSessionFactory sessionFactory;
    //类加载时执行静态块，静态块只执行一次，以后不再执行
    static {
        //配置文件（SqlMapConfig.xml）

    }


    public static SqlSession getSession(){

        if (sessionFactory == null) {
            String resource = "mybatis/SqlMapConfig-Chat.xml";
            //加载配置文件到输入流
            InputStream inputStream = null;
            try {
                inputStream = Resources.getResourceAsStream(resource);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //创建会话工厂
            sessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
        }
        SqlSession session=sessionFactory.openSession();
        return  session;
    }



}
