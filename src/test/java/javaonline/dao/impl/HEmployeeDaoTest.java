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
        Position position = new Position();
        position.setName("test position");
        IPositionDao.addPosition(position);
        List<Position> positions = IPositionDao.getAllPosition();
        assertEquals(position.getName(), positions.get(0).getName());
    }

    @Test
    @Transactional
    @Rollback
    public void testAddEmployee() throws Exception {
        Position position = new Position();
        position.setName("test position");
        IPositionDao.addPosition(position);

        Employee employee = new Employee();
        employee.setSurname("testSurname");
        employee.setName("testName");
        employee.setBirthday(new Date(20010101));
        employee.setSalary(10000.0F);
        employee.setPhoneNumber("+380670002233");
        employee.setPosition(position);

        employeeService.addEmployee(employee);

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
    public void deletePosition() throws Exception {
        Position position = new Position();
        position.setName("test position");
        IPositionDao.addPosition(position);

        List<Position> positions = IPositionDao.getAllPosition();

        assertEquals(position.getName(), positions.get(0).getName());

        IPositionDao.deletePosition(position.getName());
        List<Position> positionAfterDelete = IPositionDao.getAllPosition();

        assertEquals(0, positionAfterDelete.size());
    }

}