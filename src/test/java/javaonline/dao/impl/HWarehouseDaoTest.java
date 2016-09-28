package javaonline.dao.impl;

import javaonline.dao.IIngredientDao;
import javaonline.dao.IWarehouseDao;
import javaonline.dao.entity.Ingredient;
import javaonline.dao.entity.Unit;
import javaonline.dao.entity.Warehouse;
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

    public void testGetIngredientByName() throws Exception {
        Ingredient ingredient = createIngredient();
        ingredientDao.addIngredient(ingredient);
        Ingredient ingredientFromDB = ingredientDao.getIngredientByName(ingredient.getIngredient());
        assertEquals(ingredient.getIngredient(), ingredientFromDB.getIngredient());
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteIngredient() throws Exception {
        Ingredient ingredient = createIngredient();
        ingredientDao.addIngredient(ingredient);
        List<Ingredient> ingredients = ingredientDao.getAllIngredients();
        assertEquals(1, ingredients.size());
        ingredientDao.deleteIngredientByName(ingredient.getIngredient());
        List<Ingredient> afterDeleteIngredients = ingredientDao.getAllIngredients();
        assertEquals(0, afterDeleteIngredients.size());
    }

    @Test
    @Transactional
    @Rollback
    public void testGetWarehouseBalance() throws Exception {
        Ingredient ingredient = createIngredient();
        ingredientDao.addIngredient(ingredient);
        Warehouse warehouse = createWarehouse(ingredient);
        warehouseDao.addIngredientIntoWarehouse(warehouse);
        List<Warehouse> warehouses = warehouseDao.getWarehouseBalance();
        assertEquals(warehouse.getIngredientId(), warehouses.get(0).getIngredientId());
    }

    @Test
    @Transactional
    @Rollback
    public void testGetBalanceById() throws Exception {
        Ingredient ingredient = createIngredient();
        ingredientDao.addIngredient(ingredient);
        Warehouse warehouse = createWarehouse(ingredient);
        warehouseDao.addIngredientIntoWarehouse(warehouse);
        Warehouse warehouseFromDB = warehouseDao.getBalanceByID(warehouse.getId());
        assertEquals(warehouse.getIngredientId(), warehouseFromDB.getIngredientId());
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteIngredientFromWarehouse() throws Exception {
        Ingredient ingredient = createIngredient();
        ingredientDao.addIngredient(ingredient);
        Warehouse warehouse = createWarehouse(ingredient);
        warehouseDao.addIngredientIntoWarehouse(warehouse);
        List<Warehouse> warehouses = warehouseDao.getWarehouseBalance();
        assertEquals(1,warehouses.size());
        warehouseDao.deleteIngredientFromWarehouse(warehouse.getIngredientId().getIngredient());
        List<Warehouse> afterDeleteWarehouses = warehouseDao.getWarehouseBalance();
        assertEquals(0, afterDeleteWarehouses.size());
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateWarehouseBalance() throws Exception {
        Ingredient ingredient = createIngredient();
        ingredientDao.addIngredient(ingredient);
        Warehouse warehouse = createWarehouse(ingredient);
        warehouseDao.addIngredientIntoWarehouse(warehouse);
        List<Warehouse> warehouses = warehouseDao.getWarehouseBalance();
        assertEquals(1,warehouses.size());
        warehouse.setQuantity(20.0F);
        warehouseDao.updateWarehouseBalance(warehouse);
        List<Warehouse> afterDeleteWarehouses = warehouseDao.getWarehouseBalance();
        assertEquals(warehouse.getQuantity(), afterDeleteWarehouses.get(0).getQuantity(),0);
    }

    private Ingredient createIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredient("testIngredient");
        return ingredient;
    }

    private Warehouse createWarehouse(Ingredient ingredient) {
        Warehouse warehouse = new Warehouse();
        warehouse.setIngredientId(ingredient);
        warehouse.setQuantity(10.0F);
        warehouse.setUnit(Unit.kg);
        return warehouse;
    }

}