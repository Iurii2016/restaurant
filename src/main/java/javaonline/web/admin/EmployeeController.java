package javaonline.web.admin;

import javaonline.dao.IEmployeeDao;
import javaonline.dao.IPositionDao;
import javaonline.dao.entity.Cook;
import javaonline.dao.entity.Employee;
import javaonline.dao.entity.Position;
import javaonline.dao.entity.Waiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    private IEmployeeDao employeeService;
    private IPositionDao IPositionDao;

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Position.class, "position", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(IPositionDao.getPositionByName(text));
            }
        });
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, "birthday", new CustomDateEditor(sdf, true));
    }

    @Autowired
    public void setEmployeeService(IEmployeeDao employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setIPositionDao(IPositionDao IPositionDao) {
        this.IPositionDao = IPositionDao;
    }

    @RequestMapping(value = "/employeeStructure", method = RequestMethod.GET)
    public String employeeStructure(Map<String, Object> model) {
        model.put("ListOfPositions", IPositionDao.getAllPosition());
        return "admin/employee/employeeStructure";
    }

    @RequestMapping(value = "/allEmployees", method = RequestMethod.GET)
    public String allEmployees(Map<String, Object> model) {
        model.put("ListOfEmployee", employeeService.getAllEmployees());
        model.put("position", "all");
        return "admin/employee/allEmployees";
    }


    @RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
    public ModelAndView addEmployee() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/employee/addEmployee");
        modelAndView.addObject("employee", new Employee());
        List<Position> positions = IPositionDao.getAllPosition();
        modelAndView.addObject("listOfPositions", positions);
        return modelAndView;
    }

    @RequestMapping(value = "/employee/{id}/update", method = RequestMethod.GET)
    public ModelAndView showUpdateUserForm(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Employee employee = employeeService.getEmployeeById(id);
        modelAndView.addObject("employee", employee);
        modelAndView.setViewName("admin/employee/addEmployee");
        List<Position> positions = IPositionDao.getAllPosition();
        modelAndView.addObject("listOfPositions", positions);
        return modelAndView;
    }

    @RequestMapping(value = "/addOrUpdateEmployee", method = RequestMethod.POST)
    public ModelAndView newEmployee(@ModelAttribute("employee") Employee employee) {
        ModelAndView modelAndView = new ModelAndView();
        if (employee.getPosition().getName().equals("cook")) {
            Cook cook = new Cook();
            cook.setSurname(employee.getSurname());
            cook.setName(employee.getName());
            cook.setBirthday(employee.getBirthday());
            cook.setPhoneNumber(employee.getPhoneNumber());
            cook.setSalary(employee.getSalary());
            cook.setPosition(employee.getPosition());
            if (employeeService.getEmployeeById(employee.getId()) == null){
                employeeService.addEmployee(cook);
            }else {
                cook.setId(employee.getId());
                employeeService.updateEmployee(cook);}
            modelAndView.addObject("message", "Cook " + employee.getName() + " was added");
        } else if (employee.getPosition().getName().equals("waiter")) {
            Waiter waiter = new Waiter();
            waiter.setSurname(employee.getSurname());
            waiter.setName(employee.getName());
            waiter.setBirthday(employee.getBirthday());
            waiter.setPhoneNumber(employee.getPhoneNumber());
            waiter.setSalary(employee.getSalary());
            waiter.setPosition(employee.getPosition());
            if (employeeService.getEmployeeById(employee.getId()) == null){
                employeeService.addEmployee(waiter);
            }else {
                waiter.setId(employee.getId());
                employeeService.updateEmployee(waiter);
            }
            modelAndView.addObject("message", "Waiter " + employee.getName() + " was added");
        } else {
            if (employeeService.getEmployeeById(employee.getId()) == null){
                employeeService.addEmployee(employee);
            }else {employeeService.updateEmployee(employee);}
            modelAndView.addObject("message", "Employee " + employee.getName() + " was added");
        }
        modelAndView.setViewName("admin/successfulOperation");
        return modelAndView;
    }

    @RequestMapping(value = "/employee/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteEmployee(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/successfulOperation");
        Employee employee = employeeService.getEmployeeById(Integer.valueOf(id));
        employeeService.deleteEmployee(employee);
        modelAndView.addObject("message", "Employee " + employee + " was deleted");
        return modelAndView;
    }

//    @RequestMapping(value = "/dishesOfCook/{id}", method = RequestMethod.GET)
//    public ModelAndView dishesOfCook(@PathVariable("id") String id) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("admin/employee/allCookedDishes");
//        modelAndView.addObject("position", "cook");
//        modelAndView.addObject("cook", employeeService.getEmployeeById(Integer.valueOf(id)));
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/getEmployeeByName", method = RequestMethod.GET)
//    public ModelAndView getEmployeeByName(@RequestParam("getEmployeeByName") String getEmployeeByName) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("ListOfEmployee", employeeService.getEmployeeByName(getEmployeeByName));
//        modelAndView.setViewName("admin/employee/allEmployees");
//        modelAndView.addObject("position", "all");
//        return modelAndView;
//    }
//
//
//    @RequestMapping(value = "/getEmployeeByPosition", method = RequestMethod.GET)
//    public ModelAndView getEmployeeByPosition(@RequestParam("getEmployeeByPosition") String getEmployeeByPosition) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("ListOfEmployee", employeeService.getEmployeesByPosition(
//                IPositionDao.getPositionByName(getEmployeeByPosition)));
//        modelAndView.addObject("position", getEmployeeByPosition);
//        modelAndView.setViewName("admin/employee/allEmployees");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/deleteEmployeeByName", method = RequestMethod.GET)
//    public ModelAndView deleteEmployeeByName(@RequestParam("deleteEmployeeByName") String deleteEmployeeByName) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("admin/successfulOperation");
//        employeeService.deleteEmployeeByName(deleteEmployeeByName);
//        modelAndView.addObject("message", "Employee " + deleteEmployeeByName + " was deleted");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/orderOfWaiter/{id}", method = RequestMethod.GET)
//    public ModelAndView orderOfWaiter(@PathVariable("id") String id) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("admin/employee/allOrders");
//        modelAndView.addObject("position", "waiter");
//        modelAndView.addObject("waiter", employeeService.getEmployeeById(Integer.valueOf(id)));
//        return modelAndView;
//    }
}
