package javaonline.dao.impl;

import javaonline.dao.IIngredientDao;
import javaonline.dao.entity.Ingredient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class HIngredientDao implements IIngredientDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void addIngredient(Ingredient ingredient) {
        sessionFactory.getCurrentSession().save(ingredient);
    }

    @Override
    @Transactional
    public void deleteIngredientByName(String ingredient) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete Ingredient i where i.ingredient = :ingredient");
        query.setParameter("ingredient", ingredient).executeUpdate();
    }

    @Override
    @Transactional
    public List<Ingredient> getAllIngredients() {
        return sessionFactory.getCurrentSession().createQuery("select i from Ingredient i").list();
    }

    @Override
    @Transactional
    public Ingredient getIngredientByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Ingredient i where i.ingredient = :name");
        query.setParameter("name", name);
        return (Ingredient) query.uniqueResult();
    }
}
