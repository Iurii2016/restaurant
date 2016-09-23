package javaonline.dao;


import javaonline.dao.entity.Dish;

import java.util.List;

public interface IDishDao {
    void addDish(Dish dish);

    void updateDish(Dish dish);

    void deleteDishByName(String name);

    Dish getDishByName(String name);

    Dish getDishById(int id);

    List<Dish> getAllDishes();
}
