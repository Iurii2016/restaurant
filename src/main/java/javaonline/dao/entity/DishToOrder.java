package javaonline.dao.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "dishorders")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DishToOrder {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "order_id", nullable = false)
    private int orderId;

    @Column(name = "dish_id", nullable = false)
    private int dishId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DishToOrder)) return false;

        DishToOrder that = (DishToOrder) o;

        if (orderId != that.orderId) return false;
        return dishId == that.dishId;

    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + dishId;
        return result;
    }

    @Override
    public String toString() {
        return "DishToOrder{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", dishId=" + dishId +
                '}';
    }
}
