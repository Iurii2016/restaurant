package javaonline.dao.impl;

import javaonline.dao.entity.*;

import java.sql.Date;

public class CreateEntity {

    Employee createEmployee(Position position){
        Employee employee = new Employee();
        employee.setSurname("testSurname");
        employee.setName("testName");
        employee.setBirthday(new Date(20010101));
        employee.setSalary(10000.0F);
        employee.setPhoneNumber("+380670002233");
        employee.setPosition(position);
        return employee;
    }

    Position createPosition() {
        Position position = new Position();
        position.setName("SomePosition");
        return position;
    }

    Category createCategory() {
        Category category = new Category();
        category.setName("shashlik");
        return category;
    }

    Dish createDish(Category category) {
        Dish dish = new Dish();
        dish.setName("Chicken shashlik");
        dish.setCategoryId(category);
        dish.setPrice(70.0F);
        dish.setWeight(100);
        return dish;
    }

    MenuName createMenuName() {
        MenuName menuName = new MenuName();
        menuName.setName("grill");
        return menuName;
    }

    Menu createMenu(MenuName menuName, Dish dish) {
        Menu menu = new Menu();
        menu.setDishId(dish);
        menu.setMenuNameId(menuName);
        return menu;
    }

    Order createOrder(Employee employee){
        Order order = new Order();
        order.setEmployeeId(employee);
        order.setTableNumber(1);
        order.setDate(new Date(20160929));
        order.setStatus(OrderStatus.opened);
        return order;
    }

    CookedDish createCookedDish(Dish dish, Employee employee, Order order){
        CookedDish cookedDish = new CookedDish();
        cookedDish.setDishId(dish);
        cookedDish.setEmployeeId(employee);
        cookedDish.setOrderId(order);
        order.setDate(new Date(20160929));
        return cookedDish;
    }

    Ingredient createIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredient("testIngredient");
        return ingredient;
    }

    Warehouse createWarehouse(Ingredient ingredient) {
        Warehouse warehouse = new Warehouse();
        warehouse.setIngredientId(ingredient);
        warehouse.setQuantity(10.0F);
        warehouse.setUnit(Unit.kg);
        return warehouse;
    }

}
