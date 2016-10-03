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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @RequestMapping(value = "/admin/allEmployees", method = RequestMethod.GET)
    public String allEmployees(Model model) {
        model.addAttribute("ListOfEmployee", employeeService.getAllEmployees());
        model.addAttribute("position", "all");
        return "admin/employee/allEmployees";
    }

    @RequestMapping(value = "/admin/addEmployee", method = RequestMethod.GET)
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("ListOfEmployee", IPositionDao.getAllPosition());
        return "admin/employee/addEmployee";
    }

    @RequestMapping(value = "/admin/addOrUpdateEmployee", method = RequestMethod.POST)
    public String saveOrUpdateEmployee(@ModelAttribute("employee") Employee employee, BindingResult result,
                                       Model model, final RedirectAttributes redirectAttributes) {

        boolean error = false;

        if (employee.getSurname().isEmpty()) {
            result.rejectValue("surname", "error.Surname");
            error = true;
        }

        if (employee.getName().isEmpty()) {
            result.rejectValue("name", "error.Name");
            error = true;
        }

        if (employee.getPosition() == null) {
            result.rejectValue("position", "error.Position");
            error = true;
        }

        if (error) {
            model.addAttribute("ListOfEmployee", IPositionDao.getAllPosition());
            return "admin/employee/addEmployee";
        }

        redirectAttributes.addFlashAttribute("css", "success");
        if (employee.isNew()) {
            redirectAttributes.addFlashAttribute("msg", "Employee added successfully!");
        } else {
            redirectAttributes.addFlashAttribute("msg", "Employee updated successfully!");
        }

        if (employee.getPosition().getName().equals("cook")) {
            Cook cook = new Cook();
            cook.setSurname(employee.getSurname());
            cook.setName(employee.getName());
            cook.setBirthday(employee.getBirthday());
            cook.setPhoneNumber(employee.getPhoneNumber());
            cook.setSalary(employee.getSalary());
            cook.setPosition(employee.getPosition());
            if (employeeService.getEmployeeById(employee.getId()) == null) {
                employeeService.addEmployee(cook);
            } else {
                cook.setId(employee.getId());
                employeeService.updateEmployee(cook);
            }
        } else if (employee.getPosition().getName().equals("waiter")) {
            Waiter waiter = new Waiter();
            waiter.setSurname(employee.getSurname());
            waiter.setName(employee.getName());
            waiter.setBirthday(employee.getBirthday());
            waiter.setPhoneNumber(employee.getPhoneNumber());
            waiter.setSalary(employee.getSalary());
            waiter.setPosition(employee.getPosition());
            if (employeeService.getEmployeeById(employee.getId()) == null) {
                employeeService.addEmployee(waiter);
            } else {
                waiter.setId(employee.getId());
                employeeService.updateEmployee(waiter);
            }
        } else {
            if (employeeService.getEmployeeById(employee.getId()) == null) {
                employeeService.addEmployee(employee);
            } else {
                employeeService.updateEmployee(employee);
            }
        }
        return "redirect:/admin/allEmployees";
    }

    @RequestMapping(value = "/admin/employee/{id}/update", method = RequestMethod.GET)
    public String showUpdateUserForm(@PathVariable("id") int id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("ListOfEmployee", IPositionDao.getAllPosition());
        return "admin/employee/addEmployee";
    }

    @RequestMapping(value = "/admin/employee/{id}/delete", method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
        Employee employee = employeeService.getEmployeeById(id);
        try {
            employeeService.deleteEmployee(employee);
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "Employee was deleted!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("css", "danger");
            redirectAttributes.addFlashAttribute("msg", "Employee with id " + employee.getId() + " can not be deleted. There is one or more references on it");
            return "redirect:/admin/allEmployees";
        }
        return "redirect:/admin/allEmployees";
    }

    @RequestMapping(value = "/admin/employee/orderBy/{field}", method = RequestMethod.GET)
    public String orderBy(@PathVariable String field, Model model) {
        model.addAttribute("ListOfEmployee", employeeService.orderBy(field));
        return "admin/employee/allEmployees";
    }

}
