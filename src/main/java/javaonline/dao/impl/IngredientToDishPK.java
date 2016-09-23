package javaonline.dao.impl;

import javaonline.dao.entity.Dish;
import javaonline.dao.entity.Ingredient;

import java.io.Serializable;

public class IngredientToDishPK implements Serializable {
    protected Dish dishId;
    protected Ingredient ingredientId;

    public IngredientToDishPK() {
    }

    public IngredientToDishPK(Dish dishId, Ingredient ingredientId) {
        this.dishId = dishId;
        this.ingredientId = ingredientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IngredientToDishPK)) return false;

        IngredientToDishPK that = (IngredientToDishPK) o;

        if (dishId != null ? !dishId.equals(that.dishId) : that.dishId != null) return false;
        return ingredientId != null ? ingredientId.equals(that.ingredientId) : that.ingredientId == null;

    }

    @Override
    public int hashCode() {
        int result = dishId != null ? dishId.hashCode() : 0;
        result = 31 * result + (ingredientId != null ? ingredientId.hashCode() : 0);
        return result;
    }
}
