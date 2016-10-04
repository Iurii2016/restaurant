package javaonline.dao;

import javaonline.dao.entity.Warehouse;

import java.util.List;

public interface IWarehouseDao {
    void addIngredientIntoWarehouse(Warehouse warehouse);

    void updateWarehouseBalance(Warehouse warehouse);

    List<Warehouse> getWarehouseBalance();

    Warehouse getBalanceByID(int id);

    void deleteIngredientFromWarehouse(String name);

    List<Warehouse> orderBy(String orderBy);
}
