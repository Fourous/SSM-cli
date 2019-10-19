package com.fourous.service;

import com.fourous.model.User;
/**
* @author fourous
* @date: 2019/10/19
* @description: User服务接口
*/
public interface IUserService {
    /**
     * 通过ID查询用户服务接口
     * @param userId
     * @return
     */
    public User selectUser(long userId);

    /**
     * 插入用户接口
     * @param id
     * @param email
     * @param password
     * @param username
     * @return
     */
    public boolean insertUser(long id,String email,String password,String username);

}
