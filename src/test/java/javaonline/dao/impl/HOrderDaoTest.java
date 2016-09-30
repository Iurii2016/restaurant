package javaonline.dao.impl;

import javaonline.dao.IEmployeeDao;
import javaonline.dao.IOrderDao;
import javaonline.dao.IPositionDao;
import javaonline.dao.entity.Employee;
import javaonline.dao.entity.Order;
import javaonline.dao.entity.OrderStatus;
import javaonline.dao.entity.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(locations = {"classpath:H-application-context-test.xml", "classpath:hibernate-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class HOrderDaoTest {

    private IOrderDao orderDao;
    private IEmployeeDao employeeDao;
    private IPositionDao positionDao;
    private CreateEntity createEntity;

    @Autowired
    public void setOrderDao(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Autowired
    public void setEmployeeDao(IEmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Autowired
    public void setPositionDao(IPositionDao positionDao) {
        this.positionDao = positionDao;
    }

    @Autowired
    public void setCreateEntity(CreateEntity createEntity) {
        this.createEntity = createEntity;
    }

    @Test
    @Transactional
    @Rollback
    public void testAddAndGetAllOrders() throws Exception {
        Order order = addOrder();
        List<Order> orders = orderDao.getAllOrders();
        assertEquals(order.getId(), orders.get(0).getId());
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteOrder() throws Exception {
        Order order = addOrder();
        List<Order> orders = orderDao.getAllOrders();
        assertEquals(1, orders.size());
        orderDao.deleteOrder(order);
        List<Order> afterDeleteOrders = orderDao.getAllOrders();
        assertEquals(0, afterDeleteOrders.size());
    }

    @Test
    @Transactional
    @Rollback
    public void testSetClosedStatus() throws Exception {
        Order order = addOrder();
        List<Order> orders = orderDao.getAllOrders();
        assertEquals(OrderStatus.opened, orders.get(0).getStatus());
        order.setStatus(OrderStatus.closed);
        orderDao.update(order);
        List<Order> afterSetClosedStatusOrders = orderDao.getAllOrders();
        assertEquals(OrderStatus.closed, afterSetClosedStatusOrders.get(0).getStatus());
    }


    private Order addOrder() {
        Position position = createEntity.createPosition();
        positionDao.addPosition(position);
        Employee employee = createEntity.createEmployee(position);
        employeeDao.addEmployee(employee);
        Order order = createEntity.createOrder(employee);
        orderDao.addOrder(order);
        return order;
    }

}