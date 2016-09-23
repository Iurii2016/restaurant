package javaonline.dao.impl;

import javaonline.dao.ICookedDishDao;
import javaonline.dao.entity.CookedDish;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class HCookedDishDao implements ICookedDishDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void addCookedDish(CookedDish cookedDish) {
        sessionFactory.getCurrentSession().save(cookedDish);
    }

    @Override
    @Transactional
    public List<CookedDish> getCookedDishes() {
        return sessionFactory.getCurrentSession().createQuery("select c from CookedDish c").list();
    }
}
