package javaonline.web.admin;

import javaonline.dao.ICookedDishDao;
import javaonline.dao.IEmployeeDao;
import javaonline.dao.IOrderDao;
import javaonline.dao.IPositionDao;
import javaonline.dao.entity.Employee;
import javaonline.dao.entity.Position;
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
import java.util.List;

@Controller
public class EmployeeController {

    private IEmployeeDao employeeService;
    private IPositionDao positionDao;


    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Position.class, "position", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(positionDao.getPositionByName(text));
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
    public void setPositionDao(IPositionDao positionDao) {
        this.positionDao = positionDao;
    }

    @RequestMapping(value = "/admin/allEmployees", method = RequestMethod.GET)
    public String allEmployees(Model model) {
        model.addAttribute("listOfEmployee", employeeService.getAllEmployees());
        return "admin/employee/allEmployees";
    }

    @RequestMapping(value = "/admin/addEmployee", method = RequestMethod.GET)
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("listOfPositions", positionDao.getAllPosition());
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
            model.addAttribute("listOfPositions", positionDao.getAllPosition());
            return "admin/employee/addEmployee";
        }

        redirectAttributes.addFlashAttribute("css", "success");
        if (employee.isNew()) {
            redirectAttributes.addFlashAttribute("msg", "Employee was added successfully!");
        } else {
            redirectAttributes.addFlashAttribute("msg", "Employee was updated successfully!");
        }

        if (employeeService.getEmployeeById(employee.getId()) == null) {
            employeeService.addEmployee(employee);
        } else {
            employeeService.updateEmployee(employee);
        }
        return "redirect:/admin/allEmployees";
    }

    @RequestMapping(value = "/admin/employee/{id}/update", method = RequestMethod.GET)
    public String showUpdateUserForm(@PathVariable("id") int id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("listOfPositions", positionDao.getAllPosition());
        return "admin/employee/addEmployee";
    }

    @RequestMapping(value = "/admin/employee/{id}/delete", method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("css", "success");
        Employee employee = employeeService.getEmployeeById(id);
        employeeService.deleteEmployee(employee);
        redirectAttributes.addFlashAttribute("msg", "Employee was deleted successfully!");
        return "redirect:/admin/allEmployees";
    }

    @RequestMapping(value = "/admin/employee/orderBy/{field}", method = RequestMethod.GET)
    public String orderBy(@PathVariable String field, Model model) {
        model.addAttribute("listOfEmployee", employeeService.orderBy(field));
        return "admin/employee/allEmployees";
    }

}
