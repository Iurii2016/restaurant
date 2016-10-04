package javaonline.dao.impl;

import javaonline.dao.IMenuDao;
import javaonline.dao.entity.Menu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HMenuDao implements IMenuDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void addDishInMenu(Menu menu) {
        sessionFactory.getCurrentSession().save(menu);
    }

    @Override
    @Transactional
    public void updateMenu(Menu menu) {
        sessionFactory.getCurrentSession().update(menu);
    }

    @Override
    @Transactional
    public void deleteDishesByMenuName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete Menu where menuNameId = (select m from MenuName m where m.name = :name)");
        query.setParameter("name", name);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void deleteMenuByID(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete Menu m where m.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public Menu getMenuById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Menu m where m.id = :id");
        query.setParameter("id", id);
        return (Menu) query.uniqueResult();
    }

    @Override
    @Transactional
    public List<Menu> getAllMenu() {
        return sessionFactory.getCurrentSession().createQuery("from Menu").list();
    }

    @Override
    @Transactional
    public List<Menu> orderBy(String orderBy) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Menu> criteriaQuery = criteriaBuilder.createQuery(Menu.class);
        Root<Menu> root = criteriaQuery.from(Menu.class);
        criteriaQuery.select(root);
        if (orderBy.equals("menuNameId") || orderBy.equals("dishId")) {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(orderBy).get("name")));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(orderBy)));
        }
        session.createQuery(criteriaQuery).getResultList();
        return session.createQuery(criteriaQuery).getResultList();
    }
}
