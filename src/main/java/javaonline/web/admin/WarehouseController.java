package javaonline.web.admin;

import javaonline.dao.IDishDao;
import javaonline.dao.IIngredientDao;
import javaonline.dao.IWarehouseDao;
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

    @RequestMapping(value = "/warehouseStructure", method = RequestMethod.GET)
    public String warehouseStructure() {
        return "admin/warehouse/warehouseStructure";
    }

    @RequestMapping(value = "/getWarehouseBalance", method = RequestMethod.GET)
    public String getWarehouseBalance(Map<String, Object> model) {
        model.put("warehouseBalance", warehouseService.getWarehouseBalance());
        return "admin/warehouse/warehouseBalance";
    }

    @RequestMapping(value = "/getIngredients", method = RequestMethod.GET)
    public String getIngredients(Map<String, Object> model) {
        model.put("listOfIngredients", IIngredientDao.getAllIngredients());
        return "admin/warehouse/allIngredients";
    }

    @RequestMapping(value = "/getEndingIngredients", method = RequestMethod.GET)
    public ModelAndView getEndingIngredients(@RequestParam("getEndingIngredients") float getEndingIngredients) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("warehouseBalance", warehouseService.getEndingIngredients(getEndingIngredients));
        modelAndView.setViewName("admin/warehouse/warehouseBalance");
        return modelAndView;
    }

    @RequestMapping(value = "/getBalanceByName", method = RequestMethod.GET)
    public ModelAndView getBalanceByName(@RequestParam("getBalanceByName") String getBalanceByName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("warehouseBalance", Arrays.asList(warehouseService.getBalanceByName(getBalanceByName)));
        modelAndView.setViewName("admin/warehouse/warehouseBalance");
        return modelAndView;
    }

    @RequestMapping(value = "/addIntoWarehouse", method = RequestMethod.GET)
    public ModelAndView addIntoWarehouse() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/warehouse/addIngredientIntoWarehouse");
        modelAndView.addObject("ingredient", new Warehouse());
        modelAndView.addObject("listOfUnits", Arrays.asList(Unit.values()));
        List<Ingredient> ingredients = IIngredientDao.getAllIngredients();
        modelAndView.addObject("listOfIngredients", ingredients);
        return modelAndView;
    }

    @RequestMapping(value = "/addIngredient", method = RequestMethod.GET)
    public ModelAndView addIngredient() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/warehouse/addIngredient");
        modelAndView.addObject("newIngredient", new Ingredient());
        return modelAndView;
    }

    @RequestMapping(value = "/addOrUpdateIngredientInWarehouse", method = RequestMethod.POST)
    public ModelAndView newIngredientsIntoWarehouse(
            @ModelAttribute("ingredient") Warehouse warehouse) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/successfulOperation");
        modelAndView.addObject("message", "Ingredient " + warehouse.getIngredientId().getIngredient() +
                " was added into warehouse");
        if (warehouseService.getBalanceByName(warehouse.getIngredientId().getIngredient()) == null){
            warehouseService.addIngredientIntoWarehouse(warehouse);
        }else {
            warehouseService.changeIngredientQuantity(warehouse.getIngredientId().getIngredient(), warehouse.getQuantity());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/newIngredient", method = RequestMethod.POST)
    public ModelAndView newIngredientsIntoReference(
            @ModelAttribute("newIngredient") Ingredient newIngredient) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/successfulOperation");
        modelAndView.addObject("message", "Ingredient " + newIngredient.getIngredient() + " was created");
        IIngredientDao.addIngredient(newIngredient);
        return modelAndView;
    }

    @RequestMapping(value = "/changeIngredientQuantity", method = RequestMethod.GET)
    public ModelAndView getDishByName(@RequestParam("ingredientName") String ingredientName, @RequestParam("newQuantity") int newQuantity) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/successfulOperation");
        modelAndView.addObject("message", "Quantity of " + ingredientName + " was changed");
        warehouseService.changeIngredientQuantity(ingredientName, newQuantity);
        return modelAndView;
    }

    @RequestMapping(value = "/warehouse/{ingredientName}/update", method = RequestMethod.GET)
    public ModelAndView showUpdateUserForm(@PathVariable("ingredientName") String ingredientName) {
        ModelAndView modelAndView = new ModelAndView();
        Warehouse warehouse = warehouseService.getBalanceByName(ingredientName);
        modelAndView.setViewName("admin/warehouse/addIngredientIntoWarehouse");
        modelAndView.addObject("ingredient", warehouse);
        modelAndView.addObject("listOfUnits", Arrays.asList(Unit.values()));
        List<Ingredient> ingredients = IIngredientDao.getAllIngredients();
        modelAndView.addObject("listOfIngredients", ingredients);
        return modelAndView;

    }

    @RequestMapping(value = "/deleteIngredientFromWarehouse", method = RequestMethod.GET)
    public ModelAndView deleteDishByName(@RequestParam("deleteIngredient") String deleteIngredient) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/successfulOperation");
        modelAndView.addObject("message", "Ingredient " + deleteIngredient + " was deleted from the warehouse");
        warehouseService.deleteIngredientFromWarehouse(deleteIngredient);
        return modelAndView;
    }

    @RequestMapping(value = "/warehouse/{ingredientName}/delete", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("ingredientName") String ingredientName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/successfulOperation");
        modelAndView.addObject("message", "Ingredient " + ingredientName + " was deleted from the warehouse");
        warehouseService.deleteIngredientFromWarehouse(ingredientName);
        return modelAndView;
    }
}
