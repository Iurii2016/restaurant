package javaonline.dao;

import javaonline.dao.entity.CookedDish;

import java.util.List;

public interface ICookedDishDao {
    void addCookedDish(CookedDish cookedDish);

    List<CookedDish> getCookedDishes();
}
