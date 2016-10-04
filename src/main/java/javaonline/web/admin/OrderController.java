package javaonline.web.admin;

import javaonline.dao.ICookedDishDao;
import javaonline.dao.IDishDao;
import javaonline.dao.IEmployeeDao;
import javaonline.dao.IOrderDao;
import javaonline.dao.entity.*;
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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class OrderController {

    private IOrderDao ordersDaoService;
    private IEmployeeDao employeeDao;
    private IDishDao dishDao;
    private ICookedDishDao cookedDishDao;

    @Autowired
    public void setCookedDishDao(ICookedDishDao cookedDishDao) {
        this.cookedDishDao = cookedDishDao;
    }

    @Autowired
    public void setDishDao(IDishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Autowired
    public void setEmployeeDao(IEmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Autowired
    public void setOrdersDaoService(IOrderDao ordersDaoService) {
        this.ordersDaoService = ordersDaoService;
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Employee.class, "employeeId", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(employeeDao.getEmployeeById(Integer.valueOf(text)));
            }
        });
        binder.registerCustomEditor(Dish.class, "dishes", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(dishDao.getDishByName(text));
            }
        });
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, "date", new CustomDateEditor(sdf, true));
    }

    @RequestMapping(value = "/admin/getAllOrders", method = RequestMethod.GET)
    public String getAllOrders(Model model) {
        model.addAttribute("ListOfOrders", ordersDaoService.getAllOrders());
        return "admin/order/allOrders";
    }

    @RequestMapping(value = "/admin/order/{id}/information", method = RequestMethod.GET)
    public String getOrderInformation(@PathVariable int id,Model model) {
        Order order = ordersDaoService.getOrderById(id);
        model.addAttribute("order", order);
        model.addAttribute("listOfDishes", order.getDishes());
        return "admin/order/orderInformation";
    }

    @RequestMapping(value = "/admin/addOrder", method = RequestMethod.GET)
    public String addOrder(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("listOfEmployee", employeeDao.getWaiters());
        model.addAttribute("listOfDishes", dishDao.getAllDishes());
        return "admin/order/addOrUpdateOrder";
    }

    @RequestMapping(value = "/admin/addOrUpdateOrder", method = RequestMethod.POST)
    public String addNewDish(@ModelAttribute Order order, BindingResult result,
                             Model model, final RedirectAttributes redirectAttributes) {

        boolean error = false;

        if (order.getDishes() == null) {
            result.rejectValue("dishes", "error.Dishes");
            error = true;
        }

        if (order.getEmployeeId() == null) {
            result.rejectValue("employeeId", "error.EmployeeId");
            error = true;
        }

        if (order.getTableNumber() <= 0){
            result.rejectValue("tableNumber", "error.TableNumber");
            error = true;
        }

        if (order.getDate() == null) {
            result.rejectValue("date", "error.Date");
            error = true;
        }

        if (error) {
            model.addAttribute("listOfEmployee", employeeDao.getWaiters());
            model.addAttribute("listOfDishes", dishDao.getAllDishes());
            return "admin/order/addOrUpdateOrder";
        }

        redirectAttributes.addFlashAttribute("css", "success");
        if (ordersDaoService.getOrderById(order.getId())==null){
            redirectAttributes.addFlashAttribute("msg", "Order was added successfully!");
            ordersDaoService.addOrder(order);
        }else {
            redirectAttributes.addFlashAttribute("msg", "Order was updated successfully!");
            ordersDaoService.update(order);
        }
        return "redirect:/admin/getAllOrders";
    }

    @RequestMapping(value = "/admin/order/{id}/update", method = RequestMethod.GET)
    public String updateOrder(@PathVariable int id,Model model) {
        Order order = ordersDaoService.getOrderById(id);
        model.addAttribute("order", order);
        model.addAttribute("listOfEmployee", employeeDao.getAllEmployees());
        model.addAttribute("listOfDishes", dishDao.getAllDishes());
        return "admin/order/addOrUpdateOrder";
    }

    @RequestMapping(value = "/admin/order/{id}/closed", method = RequestMethod.GET)
    public String setClosedStatus(@PathVariable int id, final RedirectAttributes redirectAttributes) {
        Order order = ordersDaoService.getOrderById(id);
        order.setStatus(OrderStatus.closed);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Order with id " + id + " was closed!");
        ordersDaoService.update(order);
        return "redirect:/admin/getAllOrders";
    }

    @RequestMapping(value = "/admin/order/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable int id, final RedirectAttributes redirectAttributes) {
        Order order = ordersDaoService.getOrderById(id);
        List<CookedDish> cookedDishes = cookedDishDao.getCookedDishesByOrderId(order.getId());
           try {
               ordersDaoService.deleteOrder(order);
               redirectAttributes.addFlashAttribute("css", "success");
               redirectAttributes.addFlashAttribute("msg", "Order was deleted successfully!");
           }catch (Exception e){
               redirectAttributes.addFlashAttribute("css", "danger");
               redirectAttributes.addFlashAttribute("msg", "Order #" + order.getId() + " can't be deleted! Some dishes has already cooked");
           }
        return "redirect:/admin/getAllOrders";
    }

    @RequestMapping(value = "/admin/order/orderBy/{field}", method = RequestMethod.GET)
    public String getAllOrders(@PathVariable String field, Model model) {
        model.addAttribute("ListOfOrders", ordersDaoService.orderBy(field));
        return "admin/order/allOrders";
    }
}
