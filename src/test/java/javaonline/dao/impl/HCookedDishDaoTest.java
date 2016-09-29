package javaonline.dao.impl;

import javaonline.dao.*;
import javaonline.dao.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(locations = {"classpath:H-application-context-test.xml", "classpath:hibernate-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class HCookedDishDaoTest {

    private IOrderDao orderDao;
    private IEmployeeDao employeeDao;
    private IPositionDao positionDao;
    private ICategoryDao categoryDao;
    private IDishDao dishDao;
    private ICookedDishDao cookedDishDao;
    private CreateEntity createEntity;

    @Autowired
    public void setCategoryDao(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Autowired
    public void setCookedDishDao(ICookedDishDao cookedDishDao) {
        this.cookedDishDao = cookedDishDao;
    }

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

    @Autowired
    public void setDishDao(IDishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Test
    @Transactional
    @Rollback
    public void testAddAndGetCookedDishes() throws Exception {
        Position position = createEntity.createPosition();
        positionDao.addPosition(position);
        Employee employee = createEntity.createEmployee(position);
        employeeDao.addEmployee(employee);
        Order order = createEntity.createOrder(employee);
        orderDao.addOrder(order);
        Category category = createEntity.createCategory();
        categoryDao.addCategory(category);
        Dish dish = createEntity.createDish(category);
        dishDao.addDish(dish);
        CookedDish cookedDish = createEntity.createCookedDish(dish, employee, order);
        cookedDishDao.addCookedDish(cookedDish);
        List<CookedDish> cookedDishes = cookedDishDao.getCookedDishes();
        assertEquals(cookedDish.getDishId(), cookedDishes.get(0).getDishId());
    }

}