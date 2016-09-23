package javaonline.dao;

import javaonline.dao.entity.Ingredient;

import java.util.List;

public interface IIngredientDao {

    void addIngredient(Ingredient ingredient);

    void deleteIngredientByName(String ingredient);

    List<Ingredient> getAllIngredients();

    Ingredient getIngredientByName(String name);
}
