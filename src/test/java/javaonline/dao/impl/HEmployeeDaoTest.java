package javaonline.dao.impl;

import javaonline.dao.IEmployeeDao;
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

import java.util.List;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(locations = {"classpath:H-application-context-test.xml", "classpath:hibernate-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class HEmployeeDaoTest {

    private IEmployeeDao employeeService;
    private IPositionDao positionDao;
    private CreateEntity createEntity;

    @Autowired
    public void setCreateEntity(CreateEntity createEntity) {
        this.createEntity = createEntity;
    }

    @Autowired
    public void setEmployeeService(IEmployeeDao employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setPositionDao(IPositionDao positionDao) {
        this.positionDao = positionDao;
    }

    @Test
    @Transactional
    @Rollback
    public void testAddAdnGetPosition() throws Exception {
        Position position = createEntity.createPosition();
        positionDao.addPosition(position);
        List<Position> positions = positionDao.getAllPosition();
        assertEquals(position.getName(), positions.get(0).getName());
    }

    @Test
    @Transactional
    @Rollback
    public void testGetPositionByName() throws Exception {
        Position position = createEntity.createPosition();
        positionDao.addPosition(position);
        Position getPosition = positionDao.getPositionByName(position.getName());
        assertEquals(position.getName(), getPosition.getName());
    }

    @Test
    @Transactional
    @Rollback
    public void testDeletePosition() throws Exception {
        Position position = createEntity.createPosition();
        positionDao.addPosition(position);
        List<Position> positions = positionDao.getAllPosition();
        assertEquals(1, positions.size());
        positionDao.deletePosition(position.getName());
        List<Position> positionAfterDelete = positionDao.getAllPosition();
        assertEquals(0, positionAfterDelete.size());
    }

    @Test
    @Transactional
    @Rollback
    public void testAddAndGetEmployee() throws Exception {
        Position position = createEntity.createPosition();
        Employee employee = addEmployeeToDB(position);
        List<Position> positions = positionDao.getAllPosition();
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
        Position position = createEntity.createPosition();
        Employee employee = addEmployeeToDB(position);
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
        Position position = createEntity.createPosition();
        Employee employee = addEmployeeToDB(position);
        Employee employeeById = employeeService.getEmployeeById(employee.getId());
        assertEquals(employee.getSurname(), employeeById.getSurname());
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateEmployee() throws Exception {
        Position position = createEntity.createPosition();
        Employee employee = addEmployeeToDB(position);
        List<Employee> employees = employeeService.getAllEmployees();
        assertEquals(1, employees.size());
        employee.setName("updateName");
        employeeService.updateEmployee(employee);
        List<Employee> afterUpdateEmployees = employeeService.getAllEmployees();
        assertEquals(employee.getName(), afterUpdateEmployees.get(0).getName());
    }

    private Employee addEmployeeToDB(Position position) {
        positionDao.addPosition(position);
        Employee employee = createEntity.createEmployee(position);
        employeeService.addEmployee(employee);
        return employee;
    }

}