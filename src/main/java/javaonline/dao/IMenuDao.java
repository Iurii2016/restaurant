package javaonline.dao;

import javaonline.dao.entity.Menu;

import java.util.List;

public interface IMenuDao {

    void addDishInMenu(Menu menu);

    void updateMenu(Menu menu);

    void deleteDishesByMenuName(String name);

    void deleteDishFromMenu(String name);

    void deleteMenuByID(int id);

    List<Menu> getMenuByName(String name);

    Menu getMenuById(int id);

    List<Menu> getAllMenu();
}
