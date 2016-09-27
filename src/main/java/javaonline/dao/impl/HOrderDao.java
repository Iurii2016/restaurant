package javaonline.dao.impl;

import javaonline.dao.IOrderDao;
import javaonline.dao.entity.Order;
import javaonline.dao.entity.OrderStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class HOrderDao implements IOrderDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void addOrder(Order order) {
        order.setStatus(OrderStatus.opened);
        sessionFactory.getCurrentSession().save(order);
    }

    @Override
    @Transactional
    public void deleteOpenedOrder(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete Order o where o.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void setClosedStatus(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Order o where o.id = :id");
        query.setParameter("id", id);
        Order order = (Order) query.uniqueResult();
        order.setStatus(OrderStatus.closed);
        session.update(order);
    }

    @Override
    @Transactional
    public List<Order> getAllOrders() {
        return sessionFactory.getCurrentSession().createQuery("from Order").list();
    }

    @Override
    @Transactional
    public List<Order> getOpenedOrders() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Order o where o.status = javaonline.model.OrderStatus.opened");
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Order> getClosedOrders() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Order o where o.status = javaonline.model.OrderStatus.closed");
        return query.getResultList();
    }

    @Override
    @Transactional
    public Order getOrderById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Order o where o.id = :id");
        query.setParameter("id", id);
        return (Order) query.uniqueResult();
    }
}
