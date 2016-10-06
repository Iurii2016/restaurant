package javaonline.dao.impl;

import javaonline.dao.IOrderDao;
import javaonline.dao.entity.Order;
import javaonline.dao.entity.OrderStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
    public void deleteOrder(Order order) {
        if (order.getDishes() !=null) {
            order.getDishes().clear();
        }
        sessionFactory.getCurrentSession().remove(order);
    }

    @Override
    @Transactional
    public void update(Order order) {
        sessionFactory.getCurrentSession().update(order);
    }

    @Override
    @Transactional
    public List<Order> getAllOrders() {
        return sessionFactory.getCurrentSession().createQuery("from Order").list();
    }

    @Override
    @Transactional
    public Order getOrderById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Order o where o.id = :id");
        query.setParameter("id", id);
        return (Order) query.uniqueResult();
    }

    @Override
    @Transactional
    public List<Order> orderBy(String orderBy) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);
        criteriaQuery.select(root);
        if(orderBy.equals("employeeId")){
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(orderBy).get("name")));
        }else {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(orderBy)));
        }
        session.createQuery(criteriaQuery).getResultList();
        return session.createQuery(criteriaQuery).getResultList();
    }
}
