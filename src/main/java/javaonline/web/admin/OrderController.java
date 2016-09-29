package javaonline.web.admin;

import javaonline.dao.IDishDao;
import javaonline.dao.IEmployeeDao;
import javaonline.dao.IOrderDao;
import javaonline.dao.entity.Dish;
import javaonline.dao.entity.Employee;
import javaonline.dao.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Map;

@Controller
public class OrderController {

    private IOrderDao ordersDaoService;
    private IEmployeeDao IEmployeeDao;
    private IDishDao IDishDao;

    @Autowired
    public void setIDishDao(IDishDao IDishDao) {
        this.IDishDao = IDishDao;
    }

    @Autowired
    public void setIEmployeeDao(IEmployeeDao IEmployeeDao) {
        this.IEmployeeDao = IEmployeeDao;
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
                setValue(IEmployeeDao.getEmployeeById(Integer.valueOf(text)));
            }
        });
        binder.registerCustomEditor(Dish.class, "dishes", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(IDishDao.getDishByName(text));
            }
        });
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, "date", new CustomDateEditor(sdf, true));
    }

    @RequestMapping(value = "/admin/ordersStructure", method = RequestMethod.GET)
    public String ordersStructure() {
        return "redirect:getAllOrders";
    }

    @RequestMapping(value = "/admin/getAllOrders", method = RequestMethod.GET)
    public String getAllOrders(Model model) {
        model.addAttribute("ListOfOrders", ordersDaoService.getAllOrders());
        return "admin/order/allOrders";
    }

    @RequestMapping(value = "/admin/order/orderBy/{field}", method = RequestMethod.GET)
    public String getAllOrders(@PathVariable String field, Model model) {
        model.addAttribute("ListOfOrders", ordersDaoService.orderBy(field));
        return "admin/order/allOrders";
    }

//    @RequestMapping(value = "/getOpenedOrders", method = RequestMethod.GET)
//    public String getOpenedOrders(Map<String, Object> model) {
//        model.put("ListOfOrders", ordersDaoService.getOpenedOrders());
//        return "admin/order/allOrders";
//    }
//
//    @RequestMapping(value = "/getClosedOrders", method = RequestMethod.GET)
//    public String getClosedOrders(Map<String, Object> model) {
//        model.put("ListOfOrders", ordersDaoService.getClosedOrders());
//        return "admin/order/allOrders";
//    }
//
//    @RequestMapping(value = "/addOrder", method = RequestMethod.GET)
//    public ModelAndView addOrder() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("admin/order/addOrder");
//        modelAndView.addObject("newOrder", new Order());
//        modelAndView.addObject("listOfEmployee", IEmployeeDao.getAllEmployees());
//        modelAndView.addObject("listOfDishes", IDishDao.getAllDishes());
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/addNewOrder", method = RequestMethod.POST)
//    public ModelAndView addNewDish(@ModelAttribute("newOrder") Order newOrder) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("admin/successfulOperation");
//        modelAndView.addObject("message", "Order " + newOrder.getId() + " was added");
//        ordersDaoService.addOrder(newOrder);
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/deleteOpenedOrder", method = RequestMethod.GET)
//    public ModelAndView getDishByName(@RequestParam("deleteOpenedOrder") int deleteOpenedOrder) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("admin/successfulOperation");
//        modelAndView.addObject("message", "Order " + deleteOpenedOrder + " was deleted");
//        ordersDaoService.deleteOpenedOrder(deleteOpenedOrder);
//        return modelAndView;
//    }
//
//
//    @RequestMapping(value = "/setClosedStatus", method = RequestMethod.GET)
//    public ModelAndView setClosedStatus(@RequestParam("setClosedStatus") int setClosedStatus) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("admin/successfulOperation");
//        modelAndView.addObject("message", "Order " + setClosedStatus + " was closed");
//        ordersDaoService.setClosedStatus(setClosedStatus);
//        return modelAndView;
//    }
}
