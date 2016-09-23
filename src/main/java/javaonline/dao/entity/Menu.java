package javaonline.dao.entity;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "menu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Menu {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "menuname_id")
    private MenuName menuNameId;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dishId;

    public boolean isNew() {
        return (this.id == 0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MenuName getMenuNameId() {
        return menuNameId;
    }

    public void setMenuNameId(MenuName menuNameId) {
        this.menuNameId = menuNameId;
    }

    public Dish getDishId() {
        return dishId;
    }

    public void setDishId(Dish dishId) {
        this.dishId = dishId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;

        Menu menu = (Menu) o;

        if (menuNameId != null ? !menuNameId.equals(menu.menuNameId) : menu.menuNameId != null) return false;
        return dishId != null ? dishId.equals(menu.dishId) : menu.dishId == null;

    }

    @Override
    public int hashCode() {
        int result = menuNameId != null ? menuNameId.hashCode() : 0;
        result = 31 * result + (dishId != null ? dishId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", menuNameId=" + menuNameId +
                ", dishId=" + dishId +
                '}';
    }
}
