package javaonline.dao;

import javaonline.dao.entity.DishToOrder;

import java.util.List;

public interface IDishToOrderDao {

    void addDishToOrder(int orderId, int dishId);

    void deleteDishFromOrder(int orderId, int dishId);

    List<DishToOrder> getAllDishFromOrder(int orderId);


}
