package javaonline.dao.impl;

import javaonline.dao.IUserRoleDao;
import javaonline.dao.entity.UserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HUserRoleDao implements IUserRoleDao{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<UserRole> getUserRole(String userName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT r from UserRole r where r.user =:userName");
        query.setParameter("userName", userName);
        return query.list();
    }
}
