package javaonline.web.admin;

import javaonline.dao.*;
import javaonline.dao.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
public class CookedDishController {

    private ICookedDishDao cookedDishService;
    private IEmployeeDao IEmployeeDao;
    private IDishDao IDishDao;
    private IOrderDao IOrderDao;
    private IWarehouseDao IWarehouseDao;
    private IDishIngredientDao IDishIngredientDao;

    @Autowired
    public void setIDishIngredientDao(IDishIngredientDao IDishIngredientDao) {
        this.IDishIngredientDao = IDishIngredientDao;
    }

    @Autowired
    public void setIWarehouseDao(IWarehouseDao IWarehouseDao) {
        this.IWarehouseDao = IWarehouseDao;
    }

    @Autowired
    public void setIEmployeeDao(IEmployeeDao IEmployeeDao) {
        this.IEmployeeDao = IEmployeeDao;
    }

    @Autowired
    public void setIDishDao(IDishDao IDishDao) {
        this.IDishDao = IDishDao;
    }

    @Autowired
    public void setIOrderDao(IOrderDao IOrderDao) {
        this.IOrderDao = IOrderDao;
    }

    @Autowired
    public void setCookedDishService(ICookedDishDao cookedDishService) {
        this.cookedDishService = cookedDishService;
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Employee.class, "employeeId", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(IEmployeeDao.getEmployeeById(Integer.valueOf(text)));
            }
        });

        binder.registerCustomEditor(Order.class, "orderId", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(IOrderDao.getOrderById(Integer.valueOf(text)));
            }
        });

        binder.registerCustomEditor(Dish.class, "dishId", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(IDishDao.getDishByName(text));
            }
        });

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, "date", new CustomDateEditor(sdf, true));
    }

    @RequestMapping(value = "/cookedDishStructure", method = RequestMethod.GET)
    public String cookedDishStructure() {
        return "admin/cookedDish/cookedDishStructure";
    }

    @RequestMapping(value = "/getCookedDishes", method = RequestMethod.GET)
    public String getCookedDishes(Model model) {
        model.addAttribute("ListOfCookedDishes", cookedDishService.getCookedDishes());
        return "admin/cookedDish/allCookedDishes";
    }

    @RequestMapping(value = "/addCookedDish", method = RequestMethod.GET)
    public String addCookedDish(Model model) {
        model.addAttribute("newCookedDish", new CookedDish());
        model.addAttribute("listOfDishes", IDishDao.getAllDishes());
        model.addAttribute("listOfEmployees", IEmployeeDao.getAllEmployees());
        model.addAttribute("listOfOrders", IOrderDao.getOpenedOrders());
        return "admin/cookedDish/addCookedDish";
    }

    @RequestMapping(value = "/addNewCookedDish", method = RequestMethod.POST)
    public String addNewCookedDish(@ModelAttribute("addNewCookedDish") CookedDish addNewCookedDish, Model model) {
        model.addAttribute("message", addNewCookedDish.getDishId().getName() + " was cooked");
        model.addAttribute("error", "Not enough ingredients to cook " + addNewCookedDish.getDishId().getName());
        List<DishIngredient> dishIngredients = IDishDao.getDishByName(addNewCookedDish.getDishId().getName())
                .getDishIngredients();
        for (DishIngredient dishIngredient : dishIngredients) {

            float warehouseBalance = IWarehouseDao.getBalanceByName(dishIngredient.getIngredientId().getIngredient()).getQuantity();
            if (warehouseBalance - dishIngredient.getQuantity() < 0) {
                return "admin/incorrectOperation";
            }
        }

        for (DishIngredient dishIngredient : dishIngredients) {
            float currentBalance = IWarehouseDao.getBalanceByName(dishIngredient.getIngredientId().getIngredient()).getQuantity();
            float newBalance = currentBalance - dishIngredient.getQuantity();
            IWarehouseDao.changeIngredientQuantity(dishIngredient.getIngredientId().getIngredient(), newBalance);
        }

        cookedDishService.addCookedDish(addNewCookedDish);
        return "admin/successfulOperation";
    }
}
