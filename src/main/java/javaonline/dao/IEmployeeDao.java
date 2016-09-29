package javaonline.dao;

import javaonline.dao.entity.Employee;
import javaonline.dao.entity.Position;

import java.util.List;

public interface IEmployeeDao {
    void addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployeeByName(String name);

    void deleteEmployee(Employee employee);

    List<Employee> getEmployeeByName(String name);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(int id);

    List<Employee> getEmployeesByPosition(Position position);

    public List<Employee> orderBy(String orderBy);
}
