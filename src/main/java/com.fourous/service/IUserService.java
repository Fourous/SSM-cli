package com.fourous.service;

import com.fourous.model.User;

public interface IUserService {

    public User selectUser(long userId);

    public boolean insertUser(long id,String email,String password,String username);

}
