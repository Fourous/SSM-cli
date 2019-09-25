package com.fourous.dao;


import com.fourous.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// 加载spring配置文件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class IUserDaoTest {

    @Autowired
    private IUserDao dao;

    @Test
    public void testSelectUser() throws Exception {
        long id = 1;
        User user = dao.selectUser(id);
        System.out.println(user.getUsername());
    }
    @Test
    public void testInsertUser() throws Exception{
        long id = 2;
        String email = "fourous";
        String username = "adila";
        String password = "1104";
        int  istrue = dao.insertUser(id,email,username,password);
        System.out.println("this is reason"+istrue);
    }

}
