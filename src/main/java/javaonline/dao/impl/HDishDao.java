package javaonline.dao.impl;

import javaonline.dao.IDishDao;
import javaonline.dao.entity.Dish;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class HDishDao implements IDishDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void addDish(Dish dish) {
        sessionFactory.getCurrentSession().save(dish);
    }

    @Override
    @Transactional
    public void updateDish(Dish dish) {
        sessionFactory.getCurrentSession().update(dish);
    }

    @Override
    @Transactional
    public void deleteDishByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete Dish where name = :name");
        query.setParameter("name", name);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public Dish getDishByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select d from Dish d where d.name = :name");
        query.setParameter("name", name);
        return (Dish) query.uniqueResult();
    }

    @Override
    @Transactional
    public Dish getDishById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select d from Dish d where d.id = :id");
        query.setParameter("id", id);
        return (Dish) query.uniqueResult();
    }

    @Override
    @Transactional
    public List<Dish> getAllDishes() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select d from Dish d").list();
    }
}
