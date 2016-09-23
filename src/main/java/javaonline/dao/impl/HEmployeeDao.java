package javaonline.dao.impl;

import javaonline.dao.IEmployeeDao;
import javaonline.dao.entity.Employee;
import javaonline.dao.entity.Position;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class HEmployeeDao implements IEmployeeDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void addEmployee(Employee employee) {
        sessionFactory.getCurrentSession().save(employee);
    }

    @Override
    @Transactional
    public void updateEmployee(Employee employee) {
        sessionFactory.getCurrentSession().update(employee);
    }

    @Override
    @Transactional
    public void deleteEmployeeByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        Predicate condition = criteriaBuilder.equal(employeeRoot.get("name"), name);
        criteriaQuery.where(condition);
        List<Employee> employees = session.createQuery(criteriaQuery).getResultList();
        employees.forEach(session::delete);
    }

    @Override
    @Transactional
    public void deleteEmployee(Employee employee) {
        sessionFactory.getCurrentSession().remove(employee);
    }

    @Override
    @Transactional
    public List<Employee> getEmployeeByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        Predicate condition = criteriaBuilder.equal(employeeRoot.get("name"), name);
        criteriaQuery.where(condition);
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        criteriaQuery.select(employeeRoot);
        List<Employee> employees = session.createQuery(criteriaQuery).getResultList();
        return employees;
    }

    @Override
    @Transactional
    public Employee getEmployeeById(int id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        Predicate condition = criteriaBuilder.equal(employeeRoot.get("id"), id);
        criteriaQuery.where(condition);
        Employee employee = session.createQuery(criteriaQuery).uniqueResult();
        return employee;
    }

    @Override
    @Transactional
    public List<Employee> getEmployeesByPosition(Position position) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        Predicate condition = criteriaBuilder.equal(employeeRoot.get("position"), position);
        criteriaQuery.where(condition);
        session.createQuery(criteriaQuery).getResultList();
        return session.createQuery(criteriaQuery).getResultList();
    }
}
