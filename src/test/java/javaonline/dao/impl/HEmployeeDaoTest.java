package javaonline.dao.impl;

import javaonline.dao.IEmployeeDao;
import javaonline.dao.IMenuNameDao;
import javaonline.dao.IPositionDao;
import javaonline.dao.entity.Employee;
import javaonline.dao.entity.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(locations = {"classpath:H-application-context-test.xml", "classpath:hibernate-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class HEmployeeDaoTest {

    private IEmployeeDao employeeService;
    private IPositionDao IPositionDao;

    @Autowired
    public void setEmployeeService(IEmployeeDao employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setIPositionDao(IPositionDao IPositionDao) {
        this.IPositionDao = IPositionDao;
    }

    @Test
    @Transactional
    @Rollback
    public void testAddPosition() throws Exception {
        Position position = createPosition("addPosition");
        List<Position> positions = IPositionDao.getAllPosition();
        assertEquals(position.getName(), positions.get(0).getName());
    }

    @Test
    @Transactional
    @Rollback
    public void testGetPositionByName() throws Exception {
        Position position = createPosition("getPosition");
        Position getPosition = IPositionDao.getPositionByName(position.getName());
        assertEquals(position.getName(), getPosition.getName());
    }

    @Test
    @Transactional
    @Rollback
    public void testDeletePosition() throws Exception {
        Position position = createPosition("deletePosition");
        List<Position> positions = IPositionDao.getAllPosition();
        assertEquals(1, positions.size());
        IPositionDao.deletePosition(position.getName());
        List<Position> positionAfterDelete = IPositionDao.getAllPosition();
        assertEquals(0, positionAfterDelete.size());
    }

    @Test
    @Transactional
    @Rollback
    public void testAddEmployee() throws Exception {
        Position position = createPosition("addPosition");
        Employee employee = createEmployee(position);
        List<Position> positions = IPositionDao.getAllPosition();
        List<Employee> employees = employeeService.getAllEmployees();
        assertEquals(1, positions.size());
        assertEquals(1, employees.size());
        assertEquals(position.getName(), positions.get(0).getName());
        assertEquals(employee.getSurname(), employees.get(0).getSurname());
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteEmployee() throws Exception {
        Position position = createPosition("addPosition");
        Employee employee = createEmployee(position);
        List<Employee> employees = employeeService.getAllEmployees();
        assertEquals(1, employees.size());
        employeeService.deleteEmployee(employee);
        List<Employee> afterDeleteEmployees = employeeService.getAllEmployees();
        assertEquals(0, afterDeleteEmployees.size());
    }


    @Test
    @Transactional
    @Rollback
    public void testGetEmployeeById() throws Exception {
        Position position = createPosition("addPosition");
        Employee employee = createEmployee(position);
        Employee employeeById = employeeService.getEmployeeById(employee.getId());
        assertEquals(employee.getSurname(), employeeById.getSurname());
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateEmployee() throws Exception {
        Position position = createPosition("addPosition");
        Employee employee = createEmployee(position);
        List<Employee> employees = employeeService.getAllEmployees();
        assertEquals(1, employees.size());
        employee.setName("updateName");
        employeeService.updateEmployee(employee);
        List<Employee> afterUpdateEmployees = employeeService.getAllEmployees();
        assertEquals(employee.getName(), afterUpdateEmployees.get(0).getName());
    }

    private Employee createEmployee(Position position) {
        Employee employee = new Employee();
        employee.setSurname("testSurname");
        employee.setName("testName");
        employee.setBirthday(new Date(20010101));
        employee.setSalary(10000.0F);
        employee.setPhoneNumber("+380670002233");
        employee.setPosition(position);
        employeeService.addEmployee(employee);
        return employee;
    }

    private Position createPosition(String name) {
        Position position = new Position();
        position.setName(name);
        IPositionDao.addPosition(position);
        return position;
    }
}