package javaonline.dao.impl;

import javaonline.dao.IMenuNameDao;
import javaonline.dao.entity.MenuName;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class HMenuNameDao implements IMenuNameDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void addMenuName(MenuName menuName) {
        sessionFactory.getCurrentSession().save(menuName);
    }

    @Override
    @Transactional
    public void deleteMenuNameByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete MenuName where name = :name");
        query.setParameter("name", name).executeUpdate();
    }

    @Override
    @Transactional
    public List<MenuName> getAllMenuName() {
        return sessionFactory.getCurrentSession().createQuery("select m from MenuName m").list();
    }

    @Override
    @Transactional
    public MenuName getMenuNameByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select m from MenuName m where m.name = :name");
        return (MenuName) query.setParameter("name", name).uniqueResult();
    }

    @Override
    @Transactional
    public MenuName getMenuNameById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select m from MenuName m where m.id = :id");
        return (MenuName) query.setParameter("id", id).uniqueResult();
    }
}
