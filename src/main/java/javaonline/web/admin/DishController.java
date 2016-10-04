package javaonline.web.admin;

import javaonline.dao.ICategoryDao;
import javaonline.dao.IDishDao;
import javaonline.dao.IDishIngredientDao;
import javaonline.dao.IIngredientDao;
import javaonline.dao.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.util.Arrays;

@Controller
public class DishController {

    private IDishDao dishService;
    private ICategoryDao categoryDao;
    private IIngredientDao ingredientDao;
    private IDishIngredientDao dishIngredientDao;

    @Autowired
    public void setDishIngredientDao(IDishIngredientDao dishIngredientDao) {
        this.dishIngredientDao = dishIngredientDao;
    }

    @Autowired
    public void setIngredientDao(IIngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    @Autowired
    public void setCategoryDao(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
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
                setValue(categoryDao.getCategoryByName(text));
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
                setValue(ingredientDao.getIngredientByName(text));
            }
        });
    }

    @RequestMapping(value = "/admin/getAllDishes", method = RequestMethod.GET)
    public String dishes(Model model) {
        model.addAttribute("ListOfDishes", dishService.getAllDishes());
        return "admin/dish/allDishes";
    }

    @RequestMapping(value = "/admin/addDish", method = RequestMethod.GET)
    public String addDish(Model model) {
        model.addAttribute("dish", new Dish());
        model.addAttribute("listOfCategories", categoryDao.getAllCategories());
        return "admin/dish/addOrUpdateDish";
    }

    @RequestMapping(value = "/admin/dish/{id}/update", method = RequestMethod.GET)
    public String updateDish(@PathVariable int id, Model model) {
        model.addAttribute("dish", dishService.getDishById(id));
        model.addAttribute("listOfCategories", categoryDao.getAllCategories());
        return "admin/dish/addOrUpdateDish";
    }

    @RequestMapping(value = "/admin/dish/{name}/delete", method = RequestMethod.GET)
    public String deleteDish(@PathVariable String name, Model model, final RedirectAttributes redirectAttributes) {
        try {
            dishService.deleteDishByName(name);
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "Dish was deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("css", "danger");
            redirectAttributes.addFlashAttribute("msg", "Dish '" + name + "' can not be deleted. There is one or more references on it");
            return "redirect:/admin/getAllDishes";
        }
        return "redirect:/admin/getAllDishes";
    }

    @RequestMapping(value = "/admin/addOrUpdateDish", method = RequestMethod.POST)
    public String saveOrUpdateDish(@ModelAttribute("dish") Dish dish, Model model, BindingResult result,
                                   final RedirectAttributes redirectAttributes) {

        boolean error = false;

        if (dish.getName().isEmpty()) {
            result.rejectValue("name", "error.Name");
            error = true;
        }

        if (dish.getCategoryId() == null) {
            result.rejectValue("categoryId", "error.CategoryId");
            error = true;
        }

        if (dish.getPrice() <= 0) {
            result.rejectValue("price", "error.Price");
            error = true;
        }

        if (dish.getWeight() <= 0) {
            result.rejectValue("weight", "error.Weight");
            error = true;
        }

        if (error) {
            model.addAttribute("listOfCategories", categoryDao.getAllCategories());
            return "admin/dish/addOrUpdateDish";
        }

        redirectAttributes.addFlashAttribute("css", "success");
        if (dish.isNew()) {
            redirectAttributes.addFlashAttribute("msg", "Dish was added successfully!");
        } else {
            redirectAttributes.addFlashAttribute("msg", "Dish was updated successfully!");
        }

        try {
            if (dishService.getDishById(dish.getId()) == null) {
                dishService.addDish(dish);
            } else {
                dishService.updateDish(dish);
            }
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("css", "danger");
            redirectAttributes.addFlashAttribute("msg", "Dish '" + dish.getName() + "' has already exist!");
        }
        return "redirect:/admin/getAllDishes";
    }

    @RequestMapping(value = "/admin/dish/orderBy/{field}", method = RequestMethod.GET)
    public String orderBy(@PathVariable String field, Model model) {
        model.addAttribute("ListOfDishes", dishService.orderBy(field));
        return "admin/dish/allDishes";
    }
    @RequestMapping(value = "/admin/dish/{name}", method = RequestMethod.GET)
    public String dishIngredients(@PathVariable String name, Model model) {
        Dish dish = dishService.getDishByName(name);
        model.addAttribute("dish", dish);
        model.addAttribute("listOfDishIngredients", dish.getDishIngredients());
        return "admin/dish/dishIngredients";
    }

    @RequestMapping(value = "/admin/dish/{id}/addIngredient", method = RequestMethod.GET)
    public String addIngredient(@PathVariable int id, Model model) {
        Dish dish = dishService.getDishById(id);
        DishIngredient dishIngredient = new DishIngredient();
        dishIngredient.setDishId(dish);
        model.addAttribute("dishIngredient", dishIngredient);
        model.addAttribute("listOfDishes", dishService.getAllDishes());
        model.addAttribute("listOfIngredients", ingredientDao.getAllIngredients());
        model.addAttribute("listOfUnits", Arrays.asList(Unit.values()));
        return "admin/dish/addDishIngredient";
    }

    @RequestMapping(value = "/admin/saveOrUpdateDishIngredient", method = RequestMethod.POST)
    public String saveOrUpdateDishIngredient(@ModelAttribute DishIngredient dishIngredient, Model model, BindingResult result,
                                   final RedirectAttributes redirectAttributes) {

        boolean error = false;

        if (dishIngredient.getDishId()==null) {
            result.rejectValue("dishId", "error.Dishes");
            error = true;
        }

        if (dishIngredient.getIngredientId() == null) {
            result.rejectValue("ingredientId", "error.IngredientId");
            error = true;
        }

        if (dishIngredient.getQuantity() <= 0) {
            result.rejectValue("quantity", "error.Quantity");
            error = true;
        }

        if (error) {
            model.addAttribute("listOfDishes", dishService.getAllDishes());
            model.addAttribute("listOfIngredients", ingredientDao.getAllIngredients());
            model.addAttribute("listOfUnits", Arrays.asList(Unit.values()));
            return "admin/dish/addDishIngredient";
        }

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Ingredient was added successfully!");

        try {
            dishIngredientDao.addIngredientToDish(dishIngredient);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("css", "danger");
            redirectAttributes.addFlashAttribute("msg", "There is such ingredient in dish!");
        }
        return "redirect:/admin/dish/"+dishIngredient.getDishId().getName();
    }

    @RequestMapping(value = "/admin/dish/{dishName}/dishIngredient/{ingredientName}/delete", method = RequestMethod.GET)
    public String deleteDish(@PathVariable("dishName") String dishName,
                             @PathVariable("ingredientName") String ingredientName,
                             final RedirectAttributes redirectAttributes) {
        try {
            dishIngredientDao.deleteIngredientByIngredientNameAndDishName(ingredientName, dishName);
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "Ingredient was deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("css", "danger");
            redirectAttributes.addFlashAttribute("msg", "Ingredient can not be deleted from dish!");
            return "redirect:/admin/getAllDishes";
        }
        return "redirect:/admin/dish/"+dishName;
    }

}
