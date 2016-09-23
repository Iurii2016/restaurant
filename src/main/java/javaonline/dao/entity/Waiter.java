package javaonline.dao.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Waiter extends Employee {
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "waiter_id")
    @Fetch(FetchMode.SELECT)
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Waite {\n");
        sb.append("  ID = {\n").append(getId()).append("\n");
        sb.append("  surname = {\n").append(getSurname()).append("\n");
        sb.append("  name = {\n").append(getName()).append("\n");
        sb.append("  birthday = {\n").append(getBirthday()).append("\n");
        sb.append("  phoneNumber = {\n").append(getPhoneNumber()).append("\n");
        sb.append("  salary = {\n").append(getSalary()).append("\n");
        orders.forEach(order -> sb.append("    ").append(order).append("\n"));
        sb.append("  }\n");
        sb.append("}\n");
        return sb.toString();
    }
}
