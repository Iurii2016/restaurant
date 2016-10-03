package javaonline.web;

import javaonline.dao.IDishDao;
import javaonline.dao.IDishIngredientDao;
import javaonline.dao.IEmployeeDao;
import javaonline.dao.IPositionDao;
import javaonline.dao.entity.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class ClientController {

    private IDishDao dishService;
    private IDishIngredientDao IDishIngredientDao;
    private IEmployeeDao employeeService;
    private IPositionDao IPositionDao;

    @Autowired
    public void setDishService(IDishDao dishService) {
        this.dishService = dishService;
    }

    @Autowired
    public void setIDishIngredientDao(IDishIngredientDao IDishIngredientDao) {
        this.IDishIngredientDao = IDishIngredientDao;
    }

    @Autowired
    public void setEmployeeService(IEmployeeDao employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setIPositionDao(IPositionDao IPositionDao) {
        this.IPositionDao = IPositionDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("ListOfDishes", dishService.getAllDishes());
        return "/client/index";
    }

    @RequestMapping(value = "/client/index", method = RequestMethod.GET)
    public String clientIndex(Model model) {
        model.addAttribute("ListOfDishes", dishService.getAllDishes());
        return "/client/index";
    }

    @RequestMapping(value = "/client/dishInfo", method = RequestMethod.GET)
    public String dishInfo(@RequestParam String dish, Model model) {
        Dish dishByName = dishService.getDishByName(dish);
        model.addAttribute("dish", dishByName);
        model.addAttribute("listOfDishIngredients", dishByName.getDishIngredients());
        return "/client/dishes";
    }

    @RequestMapping(value = "/client/employees", method = RequestMethod.GET)
    public String employees(Model model) {
        model.addAttribute("ListOfEmployee", employeeService.getAllEmployees());
        model.addAttribute("position", IPositionDao.getAllPosition());
        return "/client/employees";
    }

    @RequestMapping(value = "/client/tables", method = RequestMethod.GET)
    public String tables() {
        return "client/tables";
    }

    @RequestMapping(value = "/client/contact", method = RequestMethod.GET)
    public String contact() {
        return "/client/contact";
    }

//    @RequestMapping(value = "/getDishByName", method = RequestMethod.GET)
//    public String getDishByName(@RequestParam("getDishByName") String getDishByName, Model model) {
//        model.addAttribute("dish", dishService.getDishByName(getDishByName));
//        model.addAttribute("listOfDishIngredients", IDishIngredientDao.getIngredientsByDishName(getDishByName));
//        return "/client/dishes";
//    }
//
//    @RequestMapping(value = "/admin/index", method = RequestMethod.GET)
//    public String adminIndex() {
//        return "/admin/index";
//    }
}
