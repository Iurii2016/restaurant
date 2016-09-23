package javaonline.dao.impl;

import javaonline.dao.IEmployeeDao;
import javaonline.dao.IPositionDao;
import javaonline.dao.entity.Employee;
import javaonline.dao.entity.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(locations = "classpath:hibernate-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class HEmployeeDaoTest {

    private IPositionDao positionDao;

    private IEmployeeDao employeeDao;

    public void setPositionDao(IPositionDao positionDao) {
        this.positionDao = positionDao;
    }

    public void setEmployeeDao(IEmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Test
    @Transactional
    @Rollback
    public void testAddPosition() throws Exception {
        Position position = new Position();
        position.setName("test position");
        positionDao.addPosition(position);
        List<Position> positions = positionDao.getAllPosition();
        assertEquals(position.getName(), positions.get(0).getName());
    }

    @Test
    @Transactional
    @Rollback
    public void testAddEmployee() throws Exception {
        Position position = new Position();
        position.setName("test position");
        positionDao.addPosition(position);

        Employee employee = new Employee();
        employee.setSurname("testSurname");
        employee.setName("testName");
        employee.setBirthday(new Date(20010101));
        employee.setSalary(10000.0F);
        employee.setPhoneNumber("+380670002233");
        employee.setPosition(position);

        employeeDao.addEmployee(employee);

        List<Position> positions = positionDao.getAllPosition();
        List<Employee> employees = employeeDao.getAllEmployees();

        assertEquals(1, positions.size());
        assertEquals(1, employees.size());

        assertEquals(position.getName(), positions.get(0).getName());
        assertEquals(employee.getSurname(), employees.get(0).getSurname());
    }

    @Test
    @Transactional
    @Rollback
    public void deleteEmployee() throws Exception {

    }

}