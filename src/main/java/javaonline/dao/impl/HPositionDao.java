package javaonline.dao.impl;

import javaonline.dao.IPositionDao;
import javaonline.dao.entity.Position;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class HPositionDao implements IPositionDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void addPosition(Position position) {
        sessionFactory.getCurrentSession().save(position);
    }

    @Override
    @Transactional
    public void deletePosition(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE Position where name = :name");
        query.setParameter("name", name);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public List<Position> getAllPosition() {
        return sessionFactory.getCurrentSession().createQuery("select p from Position p").list();
    }

    @Override
    @Transactional
    public Position getPositionByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from Position p where p.name = :name");
        return (Position) query.setParameter("name", name).uniqueResult();
    }

    @Override
    @Transactional
    public Position gerPositionById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from Position p where p.id = :id");
        return (Position) query.setParameter("id", id).uniqueResult();
    }
}
