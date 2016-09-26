package javaonline.dao;

import javaonline.dao.entity.Warehouse;

import java.util.List;

public interface IWarehouseDao {
    void addIngredientIntoWarehouse(Warehouse warehouse);

    void updateWarehouseBalance(Warehouse warehouse);

    void changeIngredientQuantity(String ingredient, float quantity);

    List getEndingIngredients(float quantity);

    List<Warehouse> getWarehouseBalance();

    Warehouse getBalanceByName(String name);

    public Warehouse getBalanceByID(int id);

    void deleteIngredientFromWarehouse(String name);
}
