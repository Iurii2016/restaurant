package javaonline.web.admin;

import javaonline.dao.IDishDao;
import javaonline.dao.IIngredientDao;
import javaonline.dao.IWarehouseDao;
import javaonline.dao.entity.Dish;
import javaonline.dao.entity.Ingredient;
import javaonline.dao.entity.Unit;
import javaonline.dao.entity.Warehouse;
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
public class WarehouseController {

    private IWarehouseDao warehouseService;
    private IIngredientDao IIngredientDao;
    private IDishDao IDishDao;

    @Autowired
    public void setIDishDao(IDishDao IDishDao) {
        this.IDishDao = IDishDao;
    }

    @Autowired
    public void setIIngredientDao(IIngredientDao IIngredientDao) {
        this.IIngredientDao = IIngredientDao;
    }

    @Autowired
    public void setWarehouseService(IWarehouseDao warehouseService) {
        this.warehouseService = warehouseService;
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Dish.class, "dishId", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(IDishDao.getDishByName(text));
            }
        });
        binder.registerCustomEditor(Ingredient.class, "ingredientId", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(IIngredientDao.getIngredientByName(text));
            }
        });
    }

    @RequestMapping(value = "/admin/getWarehouseBalance", method = RequestMethod.GET)
    public String getWarehouseBalance(Model model) {
        model.addAttribute("warehouseBalance", warehouseService.getWarehouseBalance());
        return "admin/warehouse/warehouseBalance";
    }

    @RequestMapping(value = "/admin/addWarehouse", method = RequestMethod.GET)
    public String saveOrUpdateWarehouse(Model model) {
        model.addAttribute("warehouse", new Warehouse());
        model.addAttribute("listOfUnits", Arrays.asList(Unit.values()));
        model.addAttribute("listOfIngredients", IIngredientDao.getAllIngredients());
        return "admin/warehouse/saveOrUpdateWarehouse";
    }

    @RequestMapping(value = "/admin/saveOrUpdateWarehouse", method = RequestMethod.POST)
    public String newIngredientsIntoWarehouse(@ModelAttribute("warehouse") Warehouse warehouse, BindingResult result,
                                              Model model, final RedirectAttributes redirectAttributes) {

        boolean error = false;

        if (warehouse.getIngredientId() == null) {
            result.rejectValue("ingredientId", "error.IngredientId");
            error = true;
        }

        if (warehouse.getQuantity() <= 0) {
            result.rejectValue("quantity", "error.Quantity");
            error = true;
        }

        if (error) {
            model.addAttribute("listOfUnits", Arrays.asList(Unit.values()));
            model.addAttribute("listOfIngredients", IIngredientDao.getAllIngredients());
            return "admin/warehouse/saveOrUpdateWarehouse";
        }

        redirectAttributes.addFlashAttribute("css", "success");

        if (warehouseService.getBalanceByID(warehouse.getId()) == null) {
            try {
                warehouseService.addIngredientIntoWarehouse(warehouse);
                redirectAttributes.addFlashAttribute("msg", "Ingredient was added successfully!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("css", "danger");
                redirectAttributes.addFlashAttribute("msg", "Ingredient '" +
                        warehouse.getIngredientId().getIngredient() + "' has already exist.");
                return "redirect:/admin/getWarehouseBalance";
            }
        } else {
            try {
                redirectAttributes.addFlashAttribute("msg", "Ingredient was updated successfully!");
                warehouseService.updateWarehouseBalance(warehouse);
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("css", "danger");
                redirectAttributes.addFlashAttribute("msg", "Change ingredient name is forbidden!");
                return "redirect:/admin/getWarehouseBalance";
            }
        }
        return "redirect:/admin/getWarehouseBalance";
    }

    @RequestMapping(value = "/admin/warehouse/{id}/update", method = RequestMethod.GET)
    public String showUpdateUserForm(@PathVariable int id, Model model) {
        Warehouse warehouse = warehouseService.getBalanceByID(id);
        model.addAttribute("warehouse", warehouse);
        model.addAttribute("listOfUnits", Arrays.asList(Unit.values()));
        model.addAttribute("listOfIngredients", IIngredientDao.getAllIngredients());
        return "admin/warehouse/saveOrUpdateWarehouse";
    }

    @RequestMapping(value = "/admin/warehouse/{ingredientName}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("ingredientName") String ingredientName, final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Ingredient was deleted successfully!");
        warehouseService.deleteIngredientFromWarehouse(ingredientName);
        return "redirect:/admin/getWarehouseBalance";
    }

    @RequestMapping(value = "/admin/warehouse/orderBy/{field}", method = RequestMethod.GET)
    public String orderBy(@PathVariable String field, Model model) {
        model.addAttribute("warehouseBalance", warehouseService.orderBy(field));
        return "admin/warehouse/warehouseBalance";
    }

}
