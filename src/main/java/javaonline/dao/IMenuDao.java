package javaonline.dao;

import javaonline.dao.entity.Menu;

import java.util.List;

public interface IMenuDao {

    void addDishInMenu(Menu menu);

    void updateMenu(Menu menu);

    void deleteDishesByMenuName(String name);

    void deleteMenuByID(int id);

    Menu getMenuById(int id);

    List<Menu> getAllMenu();

    List<Menu> orderBy(String orderBy);
}
