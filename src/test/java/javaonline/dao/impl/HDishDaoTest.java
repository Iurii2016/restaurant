package javaonline.dao.impl;

import javaonline.dao.ICategoryDao;
import javaonline.dao.IDishDao;
import javaonline.dao.entity.Category;
import javaonline.dao.entity.Dish;
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
public class HDishDaoTest {

    private IDishDao dishDao;
    private ICategoryDao categoryDao;
    private CreateEntity createEntity;

    @Autowired
    public void setCreateEntity(CreateEntity createEntity) {
        this.createEntity = createEntity;
    }

    @Autowired
    public void setDishDao(IDishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Autowired
    public void setCategoryDao(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Test
    @Transactional
    @Rollback
    public void testAddAndGetAllCategories() throws Exception {
        Category category = createEntity.createCategory();
        categoryDao.addCategory(category);
        List<Category> categories = categoryDao.getAllCategories();
        assertEquals(category.getName(), categories.get(0).getName());
    }

    @Test
    @Transactional
    @Rollback
    public void testGetCategoryByName() throws Exception {
        Category category = createEntity.createCategory();
        categoryDao.addCategory(category);
        Category categoryDB = categoryDao.getCategoryByName(category.getName());
        assertEquals(category.getName(), categoryDB.getName());
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteCategory() throws Exception {
        Category category = createEntity.createCategory();
        categoryDao.addCategory(category);
        List<Category> categories = categoryDao.getAllCategories();
        assertEquals(1, categories.size());
        categoryDao.deleteCategoryByName(category.getName());
        List<Category> afterDeleteCategories = categoryDao.getAllCategories();
        assertEquals(0, afterDeleteCategories.size());
    }


    @Test
    @Transactional
    @Rollback
    public void testAddAndGetAllDishes() throws Exception {
        Category category = createEntity.createCategory();
        Dish dish = addDishToDB(category);
        List<Dish> dishes = dishDao.getAllDishes();
        assertEquals(dish.getName(),dishes.get(0).getName());
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateDish() throws Exception {
        Category category = createEntity.createCategory();
        Dish dish = addDishToDB(category);
        List<Dish> dishes = dishDao.getAllDishes();
        assertEquals(dish.getName(),dishes.get(0).getName());
        dish.setName("newName");
        dishDao.updateDish(dish);
        List<Dish> afterUpdateDishes = dishDao.getAllDishes();
        assertEquals("newName",afterUpdateDishes.get(0).getName());
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteDishByName() throws Exception {
        Category category = createEntity.createCategory();
        Dish dish = addDishToDB(category);
        List<Dish> dishes = dishDao.getAllDishes();
        assertEquals(1,dishes.size());
        dishDao.deleteDishByName(dish.getName());
        List<Dish> afterDeleteDishes = dishDao.getAllDishes();
        assertEquals(0,afterDeleteDishes.size());
    }

    private Dish addDishToDB(Category category) {
        categoryDao.addCategory(category);
        Dish dish = createEntity.createDish(category);
        dishDao.addDish(dish);
        return dish;
    }
}