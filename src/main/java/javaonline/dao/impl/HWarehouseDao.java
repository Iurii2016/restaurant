package javaonline.dao.impl;

import javaonline.dao.IWarehouseDao;
import javaonline.dao.entity.Warehouse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class HWarehouseDao implements IWarehouseDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void addIngredientIntoWarehouse(Warehouse warehouse) {
        sessionFactory.getCurrentSession().save(warehouse);
    }

    @Override
    @Transactional
    public void updateWarehouseBalance(Warehouse warehouse) {
        sessionFactory.getCurrentSession().update(warehouse);
    }

    @Override
    @Transactional
    public void changeIngredientQuantity(String ingredient, float quantity) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Warehouse w where w.ingredientId = " +
                "(select i.id from Ingredient i where i.ingredient = :name)");
        query.setParameter("name", ingredient);
        Warehouse warehouse = (Warehouse) query.uniqueResult();
        warehouse.setQuantity(quantity);
        session.save(warehouse);

    }

    @Override
    @Transactional
    public List<Warehouse> getEndingIngredients(float quantity) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Warehouse w where w.quantity <= :quantity");
        query.setParameter("quantity", quantity);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Warehouse> getWarehouseBalance() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Warehouse").list();
    }

    @Override
    @Transactional
    public Warehouse getBalanceByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Warehouse w where w.ingredientId = " +
                "(select i.id from Ingredient i where i.ingredient = :name)");
        query.setParameter("name", name);
        return (Warehouse) query.uniqueResult();
    }

    @Override
    @Transactional
    public Warehouse getBalanceByID(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Warehouse w where w.id = :id");
        query.setParameter("id", id);
        return (Warehouse) query.uniqueResult();
    }

    @Override
    @Transactional
    public void deleteIngredientFromWarehouse(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE Warehouse w where w.ingredientId = " +
                "(select i.id from Ingredient i where i.ingredient = :name)");
        query.setParameter("name", name);
        query.executeUpdate();
    }

}
