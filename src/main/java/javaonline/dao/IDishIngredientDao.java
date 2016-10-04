package javaonline.dao;

import javaonline.dao.entity.DishIngredient;
import javaonline.dao.entity.Ingredient;

import java.util.List;

public interface IDishIngredientDao {

    void addIngredientToDish(DishIngredient dishIngredient);

    void deleteIngredientByIngredientNameAndDishName(String ingredientName, String dishName);
}
