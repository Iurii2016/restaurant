package javaonline.dao.entity;

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
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dishId;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredientId;

    @Column(name = "quantity", nullable = false)
    private float quantity;


    @Enumerated(EnumType.STRING)
    @Column(name = "unit", nullable = false)
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
        if (ingredientId != null ? !ingredientId.equals(that.ingredientId) : that.ingredientId != null) return false;
        return unit == that.unit;

    }

    @Override
    public int hashCode() {
        int result = ingredientId != null ? ingredientId.hashCode() : 0;
        result = 31 * result + (quantity != +0.0f ? Float.floatToIntBits(quantity) : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        return result;
    }
}
