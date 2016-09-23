package javaonline.dao;

import javaonline.dao.entity.UserRole;

import java.util.List;

public interface IUserRoleDao {
    List<UserRole> getUserRole(String userName);
}
