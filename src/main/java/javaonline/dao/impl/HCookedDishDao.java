package javaonline.dao.impl;

import javaonline.dao.ICookedDishDao;
import javaonline.dao.entity.CookedDish;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        return sessionFactory.getCurrentSession().createQuery("from CookedDish").list();
    }

    @Override
    @Transactional
    public List<CookedDish> orderBy(String orderBy) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<CookedDish> criteriaQuery = criteriaBuilder.createQuery(CookedDish.class);
        Root<CookedDish> root = criteriaQuery.from(CookedDish.class);
        criteriaQuery.select(root);
        if(orderBy.equals("dishId")){
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(orderBy).get("name")));
        }else if(orderBy.equals("employeeId")){
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(orderBy).get("name")));
        }else {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(orderBy)));
        }
        session.createQuery(criteriaQuery).getResultList();
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    @Transactional
    public List<CookedDish> getCookedDishesByOrderId(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CookedDish c where c.orderId = (from Order o where o.id =:id)");
        query.setParameter("id", id);
        return query.list();
    }
}
