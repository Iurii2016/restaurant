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
public class Cook extends Employee{
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "cook_id")
    @Fetch(FetchMode.SELECT)
    private List<CookedDish> cookedDishes;

    public List<CookedDish> getCookedDishes() {
        return cookedDishes;
    }

    public void setCookedDishes(List<CookedDish> cookedDishes) {
        this.cookedDishes = cookedDishes;
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
        cookedDishes.forEach(cookedDish -> sb.append("    ").append(cookedDish).append("\n"));
        sb.append("  }\n");
        sb.append("}\n");
        return sb.toString();
    }
}
