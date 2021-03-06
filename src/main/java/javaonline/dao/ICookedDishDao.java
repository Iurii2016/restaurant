package javaonline.dao;

import javaonline.dao.entity.CookedDish;

import java.util.List;

public interface ICookedDishDao {
    void addCookedDish(CookedDish cookedDish);

    List<CookedDish> getCookedDishes();

    List<CookedDish> orderBy(String orderBy);

    List<CookedDish> getCookedDishesByOrderId(int id);
}
