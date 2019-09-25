package com.fourous.dao;

import com.fourous.model.User;

public interface IUserDao {
    /**
     *
     * @param id
     * @return
     */
    User selectUser(long id);

    /**
     *
     * @param id
     * @param email
     * @param password
     * @param username
     * @return
     */
    int insertUser(long id,String email,String password,String username);
}
