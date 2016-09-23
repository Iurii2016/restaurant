package javaonline.web;

import javaonline.dao.IDishDao;
import javaonline.dao.IDishIngredientDao;
import javaonline.dao.IEmployeeDao;
import javaonline.dao.IPositionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class MainController {

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
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/index");
        modelAndView.addObject("ListOfDishes", dishService.getAllDishes());
        return modelAndView;
    }

    @RequestMapping(value = "/client/index", method = RequestMethod.GET)
    public ModelAndView clientIndex() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/client/index");
        modelAndView.addObject("ListOfDishes", dishService.getAllDishes());
        return modelAndView;
    }

    @RequestMapping(value = "/admin/index", method = RequestMethod.GET)
    public ModelAndView adminIndex() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/index");
        return modelAndView;
    }

    @RequestMapping(value = "/getDishByName", method = RequestMethod.GET)
    public ModelAndView getDishByName(@RequestParam("getDishByName") String getDishByName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dish", dishService.getDishByName(getDishByName));
        modelAndView.addObject("listOfDishIngredients", IDishIngredientDao.getIngredientsByDishName(getDishByName));
        modelAndView.setViewName("/client/dishes");
        return modelAndView;
    }

    @RequestMapping(value = "/dishInfo", method = RequestMethod.GET)
    public ModelAndView dishInfo(@RequestParam String dish) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/client/dishes");
        modelAndView.addObject("dish", dishService.getDishByName(dish));
        modelAndView.addObject("listOfDishIngredients", IDishIngredientDao.getIngredientsByDishName(dish));
        return modelAndView;
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ModelAndView employees(Map<String, Object> model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/client/employees");
        modelAndView.addObject("ListOfEmployee", employeeService.getAllEmployees());
        model.put("position", IPositionDao.getAllPosition());
        return modelAndView;
    }

    @RequestMapping(value = "/tables", method = RequestMethod.GET)
    public String tables() {
        return "client/tables";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact() {
        return "/client/contact";
    }


}
