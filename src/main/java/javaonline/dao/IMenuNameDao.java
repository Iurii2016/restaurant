package javaonline.dao;

import javaonline.dao.entity.MenuName;

import java.util.List;

public interface IMenuNameDao {
    void addMenuName(MenuName menuName);

    void deleteMenuNameByName(String name);

    List<MenuName> getAllMenuName();

    MenuName getMenuNameByName(String name);

    MenuName getMenuNameById(long id);
}
