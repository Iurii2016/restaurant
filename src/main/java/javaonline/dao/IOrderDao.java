package javaonline.dao;

import javaonline.dao.entity.Order;

import java.util.List;

public interface IOrderDao {

    void addOrder(Order order);

    void deleteOpenedOrder(int id);

    void setClosedStatus(int id);

    List<Order> getAllOrders();

    List<Order> getOpenedOrders();

    List<Order> getClosedOrders();

    Order getOrderById(int id);

    public List<Order> orderBy(String orderBy);
}
