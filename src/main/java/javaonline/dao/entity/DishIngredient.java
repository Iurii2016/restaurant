package javaonline.dao.entity;

import javaonline.dao.impl.IngredientToDishPK;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "dish_ingredients")
@IdClass(IngredientToDishPK.class)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DishIngredient {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dish_id")
    private Dish dishId;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredientId;

    @Column(name = "quantity")
    private float quantity;


    @Enumerated(EnumType.STRING)
    @Column(name = "unit")
    private Unit unit;

    public Dish getDishId() {
        return dishId;
    }

    public void setDishId(Dish dishId) {
        this.dishId = dishId;
    }

    public Ingredient getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Ingredient ingredientId) {
        this.ingredientId = ingredientId;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DishIngredient)) return false;

        DishIngredient that = (DishIngredient) o;

        if (Float.compare(that.quantity, quantity) != 0) return false;
        if (dishId != null ? !dishId.equals(that.dishId) : that.dishId != null) return false;
        if (ingredientId != null ? !ingredientId.equals(that.ingredientId) : that.ingredientId != null) return false;
        return unit == that.unit;

    }

    @Override
    public int hashCode() {
        int result = dishId != null ? dishId.hashCode() : 0;
        result = 31 * result + (ingredientId != null ? ingredientId.hashCode() : 0);
        result = 31 * result + (quantity != +0.0f ? Float.floatToIntBits(quantity) : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DishIngredient{" +
                "dishId=" + dishId +
                ", ingredientId=" + ingredientId +
                ", quantity=" + quantity +
                ", unit=" + unit +
                '}';
    }
}
