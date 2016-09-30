package javaonline.web.admin;

import javaonline.dao.ICategoryDao;
import javaonline.dao.IDishDao;
import javaonline.dao.IDishIngredientDao;
import javaonline.dao.IIngredientDao;
import javaonline.dao.entity.Category;
import javaonline.dao.entity.Dish;
import javaonline.dao.entity.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;

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

    @RequestMapping(value = "/admin/getAllDishes", method = RequestMethod.GET)
    public String dishes(Model model) {
        model.addAttribute("ListOfDishes", dishService.getAllDishes());
        return "admin/dish/allDishes";
    }

    @RequestMapping(value = "/admin/addDish", method = RequestMethod.GET)
    public String addDish(Model model) {
        model.addAttribute("dish", new Dish());
        model.addAttribute("listOfCategories", ICategoryDao.getAllCategories());
        return "admin/dish/addOrUpdateDish";
    }

    @RequestMapping(value = "/admin/dish/{id}/update", method = RequestMethod.GET)
    public String updateDish(@PathVariable int id, Model model) {
        model.addAttribute("dish", dishService.getDishById(id));
        model.addAttribute("listOfCategories", ICategoryDao.getAllCategories());
        return "admin/dish/addOrUpdateDish";
    }

    @RequestMapping(value = "/admin/dish/{name}/delete", method = RequestMethod.GET)
    public String deleteDish(@PathVariable String name, Model model, final RedirectAttributes redirectAttributes) {
        try {
            dishService.deleteDishByName(name);
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "Dish was deleted!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("css", "danger");
            redirectAttributes.addFlashAttribute("msg", "Exception! Dish " + name + " can not be deleted. There is one or more references on it");
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

        if (dish.getPrice() == 0) {
            result.rejectValue("price", "error.Price");
            error = true;
        }

        if (dish.getWeight() == 0) {
            result.rejectValue("weight", "error.Weight");
            error = true;
        }

        if (error) {
            model.addAttribute("listOfCategories", ICategoryDao.getAllCategories());
            return "admin/dish/addOrUpdateDish";
        }

        redirectAttributes.addFlashAttribute("css", "success");
        if (dish.isNew()) {
            redirectAttributes.addFlashAttribute("msg", "Dish added successfully!");
        } else {
            redirectAttributes.addFlashAttribute("msg", "Dish updated successfully!");
        }


        if (dishService.getDishById(dish.getId()) == null) {
            dishService.addDish(dish);
        } else {
            dishService.updateDish(dish);
        }
        return "redirect:/admin/getAllDishes";
    }

    @RequestMapping(value = "/admin/dish/orderBy/{field}", method = RequestMethod.GET)
    public String orderBy(@PathVariable String field, Model model) {
        model.addAttribute("ListOfDishes", dishService.orderBy(field));
        return "admin/dish/allDishes";
    }

}
