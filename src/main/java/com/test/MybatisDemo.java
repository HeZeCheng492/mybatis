package com.test;

import com.test.pojo.Brand;
import com.test.pojo.User;
import com.test.port.BrandMapper;
import com.test.port.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisDemo {
    public static void main(String[] args) throws IOException {
        int id=1;
        // 加载mybatis的核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 执行sql
        //List<User> users = sqlSession.selectList("(namespace).selectAll");
        // 优化：获取接口代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //执行方法
        List<User> users = userMapper.selectAll();
        User user = userMapper.selectById(id);
        System.out.println(user);

        sqlSession.close();
    }
}
