package javaonline.dao;

import javaonline.dao.entity.Order;

import java.util.List;

public interface IOrderDao {

    void addOrder(Order order);

    void deleteOrder(Order order);

    void update(Order order);

    List getAllOrders();

    Order getOrderById(int id);

    List<Order> orderBy(String orderBy);
}
