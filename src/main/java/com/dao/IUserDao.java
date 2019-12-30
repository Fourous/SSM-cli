package com.dao;

import com.model.User;

public interface IUserDao {
    /**
     *
     * 指定用户ID进行查询
     * @param id
     * @return
     *
     */
    User selectUser(long id);

    /**
     * 插入数据用户
     * @param id
     * @param email
     * @param password
     * @param username
     * @return
     *
     */
    int insertUser(long id,String email,String password,String username);
}
