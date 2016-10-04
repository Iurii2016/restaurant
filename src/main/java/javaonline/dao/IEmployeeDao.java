package javaonline.dao;

import javaonline.dao.entity.Employee;
import javaonline.dao.entity.Position;

import java.util.List;

public interface IEmployeeDao {
    void addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(int id);

    List<Employee> orderBy(String orderBy);

    List<Employee> getWaiters();
}
