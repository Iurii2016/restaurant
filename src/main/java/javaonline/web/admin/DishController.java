package javaonline.web.admin;

import javaonline.dao.ICategoryDao;
import javaonline.dao.IDishDao;
import javaonline.dao.IDishIngredientDao;
import javaonline.dao.IIngredientDao;
import javaonline.dao.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class DishController {

    private IDishDao dishService;
    private ICategoryDao ICategoryDao;
    private IIngredientDao IIngredientDao;
    private IDishIngredientDao IDishIngredientDao;

    @Autowired
    public void setIDishIngredientDao(IDishIngredientDao IDishIngredientDao) {
        this.IDishIngredientDao = IDishIngredientDao;
    }

    @Autowired
    public void setIIngredientDao(IIngredientDao IIngredientDao) {
        this.IIngredientDao = IIngredientDao;
    }

    @Autowired
    public void setICategoryDao(ICategoryDao ICategoryDao) {
        this.ICategoryDao = ICategoryDao;
    }

    @Autowired
    public void setDishService(IDishDao dishService) {
        this.dishService = dishService;
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Category.class, "categoryId", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(ICategoryDao.getCategoryByName(text));
            }
        });
        binder.registerCustomEditor(Dish.class, "dishId", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(dishService.getDishByName(text));
            }
        });
        binder.registerCustomEditor(Ingredient.class, "ingredientId", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(IIngredientDao.getIngredientByName(text));
            }
        });
    }

    @RequestMapping(value = "/dishStructure", method = RequestMethod.GET)
    public String dishStructure() {
        return "admin/dish/dishStructure";
    }

    @RequestMapping(value = "/addDish", method = RequestMethod.GET)
    public ModelAndView addDish() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/dish/addOrUpdateDish");
        modelAndView.addObject("dish", new Dish());
        List<Category> categories = ICategoryDao.getAllCategories();
        modelAndView.addObject("listOfCategories", categories);
        return modelAndView;
    }

    @RequestMapping(value = "/dish/{id}/update", method = RequestMethod.GET)
    public ModelAndView updateDish(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/dish/addOrUpdateDish");
        modelAndView.addObject("dish", dishService.getDishById(id));
        List<Category> categories = ICategoryDao.getAllCategories();
        modelAndView.addObject("listOfCategories", categories);
        return modelAndView;
    }

    @RequestMapping(value = "/addOrUpdateDish", method = RequestMethod.POST)
    public ModelAndView addNewDish(@ModelAttribute Dish dish) {
        ModelAndView modelAndView = new ModelAndView();
        if (dishService.getDishById(dish.getId()) == null) {
            dishService.addDish(dish);
        } else {
            dishService.updateDish(dish);
        }
        modelAndView.addObject("message", dish.getName() + " was added");
        modelAndView.setViewName("admin/successfulOperation");
        return modelAndView;
    }

    @RequestMapping(value = "/addDishIngredient", method = RequestMethod.GET)
    public ModelAndView addDishIngredient() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/dish/addDishIngredient");
        modelAndView.addObject("newDishIngredient", new DishIngredient());
        modelAndView.addObject("listOfDishes", dishService.getAllDishes());
        modelAndView.addObject("listOfIngredients", IIngredientDao.getAllIngredients());
        modelAndView.addObject("listOfUnits", Arrays.asList(Unit.values()));
        return modelAndView;
    }

    @RequestMapping(value = "/addNewDishIngredient", method = RequestMethod.POST)
    public ModelAndView addNewDishIngredient(@ModelAttribute("newDishIngredient") DishIngredient newDishIngredient) {
        ModelAndView modelAndView = new ModelAndView();
        IDishIngredientDao.addIngredientToDish(newDishIngredient);
        modelAndView.addObject("message", newDishIngredient.getIngredientId().getIngredient() + " was added to dish " +
                newDishIngredient.getDishId().getName());
        modelAndView.setViewName("admin/successfulOperation");
        return modelAndView;
    }

    @RequestMapping(value = "/getDishByNameAdmin", method = RequestMethod.GET)
    public ModelAndView getDishByNameAdmin(@RequestParam("getDishByName") String getDishByName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ListOfDishes", Arrays.asList(dishService.getDishByName(getDishByName)));
        modelAndView.setViewName("admin/dish/allDishes");
        return modelAndView;
    }

    @RequestMapping(value = "/deleteDishByName", method = RequestMethod.GET)
    public ModelAndView deleteDishByName(@RequestParam("deleteDishByName") String deleteDishByName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", deleteDishByName + " was deleted");
        dishService.deleteDishByName(deleteDishByName);
        modelAndView.setViewName("admin/successfulOperation");
        return modelAndView;
    }

    @RequestMapping(value = "/dish/{name}/delete", method = RequestMethod.GET)
    public ModelAndView deleteDish(@PathVariable String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", name + " was deleted");
        modelAndView.setViewName("admin/successfulOperation");
        dishService.deleteDishByName(name);
        return modelAndView;
    }

    @RequestMapping(value = "/getAllDishes", method = RequestMethod.GET)
    public String dishes(Map<String, Object> model) {
        model.put("ListOfDishes", dishService.getAllDishes());
        return "admin/dish/allDishes";
    }


    @RequestMapping(value = "/getAllDishIngredients", method = RequestMethod.GET)
    public String getAllDishIngredients(Map<String, Object> model) {
        model.put("ListOfDishIngredients", IDishIngredientDao.getAllDishIngredients());
        return "admin/dish/allDishIngredients";
    }
}
