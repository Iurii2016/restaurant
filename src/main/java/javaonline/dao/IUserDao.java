package javaonline.dao;

import javaonline.dao.entity.User;

public interface IUserDao {
    User findByUserName(String username);

    void registerUser(User user);
}
