package javaonline.dao.impl;

import javaonline.dao.IEmployeeDao;
import javaonline.dao.IIngredientDao;
import javaonline.dao.IPositionDao;
import javaonline.dao.IWarehouseDao;
import javaonline.dao.entity.Ingredient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@ContextConfiguration(locations = {"classpath:H-application-context-test.xml", "classpath:hibernate-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class HWarehouseDaoTest {

    private IIngredientDao ingredientDao;
    private IWarehouseDao warehouseDao;

    @Autowired
    public void setIngredientDao(IIngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    @Autowired
    public void setWarehouseDao(IWarehouseDao warehouseDao) {
        this.warehouseDao = warehouseDao;
    }

    @Test
    @Transactional
    @Rollback
    public void testGetAllIngredient() throws Exception {
        Ingredient ingredient = createIngredient();
        ingredientDao.addIngredient(ingredient);

        List<Ingredient> ingredients = ingredientDao.getAllIngredients();

        assertEquals(ingredient.getIngredient(), ingredients.get(0).getIngredient());

    }

    private Ingredient createIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredient("testIngredient");
        return ingredient;
    }

    @Test
    @Transactional
    @Rollback
    public void addIngredientIntoWarehouse() throws Exception {

    }

    @Test
    @Transactional
    @Rollback
    public void deleteIngredientFromWarehouse() throws Exception {

    }

}