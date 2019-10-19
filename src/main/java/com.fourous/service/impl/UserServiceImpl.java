package com.fourous.service.impl;

import com.fourous.dao.IUserDao;
import com.fourous.model.User;
import com.fourous.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
* @author fourous
* @date: 2019/10/19
* @description: 服务接口实现
*/
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Override
    public User selectUser(long userId) {
        return this.userDao.selectUser(userId);
    }

    @Override
    public boolean insertUser(long id,String email,String password,String username){
        int num=this.userDao.insertUser(id,email,password,username);
        if(num>0){
            return true;
        }else{
            return false;
        }
    }
}

