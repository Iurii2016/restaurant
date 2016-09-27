package javaonline.dao.impl;

import javaonline.dao.ICategoryDao;
import javaonline.dao.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class HCategoryDao implements ICategoryDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void addCategory(Category category) {
        sessionFactory.getCurrentSession().save(category);
    }

    @Override
    @Transactional
    public void deleteCategoryByName(String category) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete Category c where c.category = :category");
        query.setParameter("category", category).executeUpdate();

    }

    @Override
    @Transactional
    public List<Category> getAllCategories() {
        return sessionFactory.getCurrentSession().createQuery("from Category c").list();
    }

    @Override
    @Transactional
    public Category getCategoryByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Category c where c.name = :name");
        return (Category) query.setParameter("name", name).uniqueResult();
    }

    @Override
    @Transactional
    public Category getCategoryById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Category c where c.id = :id");
        return (Category) query.setParameter("id", id).uniqueResult();
    }
}
