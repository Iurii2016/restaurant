package javaonline.dao.entity;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "dishcooking")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CookedDish {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", unique = true)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cook_id")
    private Employee employeeId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dish_id")
    private Dish dishId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order orderId;

    @Column(name = "date")
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Dish getDishId() {
        return dishId;
    }

    public void setDishId(Dish dishId) {
        this.dishId = dishId;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CookedDish)) return false;

        CookedDish that = (CookedDish) o;

        if (dishId != null ? !dishId.equals(that.dishId) : that.dishId != null) return false;
        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;
        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;

    }

    @Override
    public int hashCode() {
        int result = dishId != null ? dishId.hashCode() : 0;
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CookedDish{" +
                "id=" + id +
                ", dishId=" + dishId +
                ", employeeId=" + employeeId +
                ", orderId=" + orderId +
                ", date=" + date +
                '}';
    }
}
