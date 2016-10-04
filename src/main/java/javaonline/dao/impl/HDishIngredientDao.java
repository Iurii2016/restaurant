package javaonline.dao.impl;

import javaonline.dao.IDishIngredientDao;
import javaonline.dao.entity.DishIngredient;
import javaonline.dao.entity.Ingredient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class HDishIngredientDao implements IDishIngredientDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void addIngredientToDish(DishIngredient dishIngredient) {
        sessionFactory.getCurrentSession().save(dishIngredient);
    }

    @Override
    @Transactional
    public void deleteIngredientByIngredientNameAndDishName(String ingredientName, String dishName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete DishIngredient i where i.dishId = " +
                "(select d.id from Dish d where d.name = :dishName) and i.ingredientId = " +
                "(select ing.id from Ingredient ing where ing.ingredient = :ingredientName)");
        query.setParameter("dishName", dishName);
        query.setParameter("ingredientName", ingredientName);
        query.executeUpdate();
    }
}
