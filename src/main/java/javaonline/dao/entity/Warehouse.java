package javaonline.dao.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "warehouse")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Warehouse {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "ingredient_id", unique = true)
    private Ingredient ingredientId;

    @Column(name = "quantity")
    private float quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit")
    private Unit unit;

    public boolean isNew() {
        return (this.id == 0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if (!(o instanceof Warehouse)) return false;

        Warehouse warehouse = (Warehouse) o;

        if (Float.compare(warehouse.quantity, quantity) != 0) return false;
        if (ingredientId != null ? !ingredientId.equals(warehouse.ingredientId) : warehouse.ingredientId != null)
            return false;
        return unit == warehouse.unit;

    }

    @Override
    public int hashCode() {
        int result = ingredientId != null ? ingredientId.hashCode() : 0;
        result = 31 * result + (quantity != +0.0f ? Float.floatToIntBits(quantity) : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        return result;
    }
}
