package javaonline.dao.impl;

import javaonline.dao.IUserDao;
import javaonline.dao.entity.User;
import org.hibernate.SessionFactory;

import java.util.List;

public class HUserDao implements IUserDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findByUserName(String username) {
        List<User> users;

        users = sessionFactory.getCurrentSession()
                .createQuery("from User u where u.username= :username")
                .setParameter("username", username)
                .list();

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void registerUser(User user) {

    }
}
