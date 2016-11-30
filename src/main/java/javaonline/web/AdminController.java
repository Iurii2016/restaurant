package javaonline.web;

import javaonline.dao.*;
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
import java.util.Arrays;
import java.util.List;


@Controller
public class AdminController {

    private ICookedDishDao cookedDishDao;
    private IEmployeeDao employeeDao;
    private IDishDao dishDao;
    private IOrderDao orderDao;
    private IWarehouseDao warehouseDao;
    private IDishIngredientDao dishIngredientDao;
    private ICategoryDao categoryDao;
    private IIngredientDao ingredientDao;
    private IPositionDao positionDao;
    private IMenuDao menuDao;
    private IMenuNameDao menuNameDao;

    @Autowired
    public void setIngredientDao(IIngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    @Autowired
    public void setCategoryDao(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Autowired
    public void setDishIngredientDao(IDishIngredientDao dishIngredientDao) {
        this.dishIngredientDao = dishIngredientDao;
    }

    @Autowired
    public void setWarehouseDao(IWarehouseDao warehouseDao) {
        this.warehouseDao = warehouseDao;
    }

    @Autowired
    public void setOrderDao(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Autowired
    public void setCookedDishDao(ICookedDishDao cookedDishDao) {
        this.cookedDishDao = cookedDishDao;
    }

    @Autowired
    public void setEmployeeDao(IEmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Autowired
    public void setPositionDao(IPositionDao positionDao) {
        this.positionDao = positionDao;
    }

    @Autowired
    public void setDishDao(IDishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Autowired
    public void setMenuNameDao(IMenuNameDao menuNameDao) {
        this.menuNameDao = menuNameDao;
    }

    @Autowired
    public void setMenuDao(IMenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, "date", new CustomDateEditor(sdf, true));
        binder.registerCustomEditor(Date.class, "birthday", new CustomDateEditor(sdf, true));

        binder.registerCustomEditor(Employee.class, "employeeId", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(employeeDao.getEmployeeById(Integer.valueOf(text)));
            }
        });

        binder.registerCustomEditor(Order.class, "orderId", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(orderDao.getOrderById(Integer.valueOf(text)));
            }
        });

        binder.registerCustomEditor(Category.class, "categoryId", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(categoryDao.getCategoryByName(text));
            }
        });
        binder.registerCustomEditor(Dish.class, "dishId", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(dishDao.getDishByName(text));
            }
        });

        binder.registerCustomEditor(Dish.class, "dishes", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(dishDao.getDishByName(text));
            }
        });

        binder.registerCustomEditor(Ingredient.class, "ingredientId", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(ingredientDao.getIngredientByName(text));
            }
        });

        binder.registerCustomEditor(Position.class, "position", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(positionDao.getPositionByName(text));
            }
        });
        binder.registerCustomEditor(MenuName.class, "menuNameId", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(menuNameDao.getMenuNameByName(text));
            }
        });

    }

    @RequestMapping(value = "/admin/cookedDish/orderBy/{field}", method = RequestMethod.GET)
    public String getCookedDishes(@PathVariable String field, Model model) {
        model.addAttribute("ListOfCookedDishes", cookedDishDao.orderBy(field));
        return "admin/cookedDish/allCookedDishes";
    }

    @RequestMapping(value = "/admin/dish/orderBy/{field}", method = RequestMethod.GET)
    public String dishOrderBy(@PathVariable String field, Model model) {
        model.addAttribute("ListOfDishes", dishDao.orderBy(field));
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
        model.addAttribute("dish", dishDao.getDishById(id));
        model.addAttribute("listOfCategories", categoryDao.getAllCategories());
        return "admin/dish/addOrUpdateDish";
    }

    @RequestMapping(value = "/admin/dish/{name}/delete", method = RequestMethod.GET)
    public String deleteDish(@PathVariable String name, Model model, final RedirectAttributes redirectAttributes) {
        try {
            dishDao.deleteDishByName(name);
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "Dish was deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("css", "danger");
            redirectAttributes.addFlashAttribute("msg", "Dish '" + name + "' can not be deleted. There is one or more references on it");
            return "redirect:/admin/dish/orderBy/id";
        }
        return "redirect:/admin/dish/orderBy/id";
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
            if (dishDao.getDishById(dish.getId()) == null) {
                dishDao.addDish(dish);
            } else {
                dishDao.updateDish(dish);
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("css", "danger");
            redirectAttributes.addFlashAttribute("msg", "Dish '" + dish.getName() + "' has already exist!");
        }
        return "redirect:/admin/dish/orderBy/id";
    }

    @RequestMapping(value = "/admin/dish/{name}", method = RequestMethod.GET)
    public String dishIngredients(@PathVariable String name, Model model) {
        Dish dish = dishDao.getDishByName(name);
        model.addAttribute("dish", dish);
        model.addAttribute("listOfDishIngredients", dish.getDishIngredients());
        return "admin/dish/dishIngredients";
    }

    @RequestMapping(value = "/admin/dish/{id}/addIngredient", method = RequestMethod.GET)
    public String addIngredient(@PathVariable int id, Model model) {
        Dish dish = dishDao.getDishById(id);
        DishIngredient dishIngredient = new DishIngredient();
        dishIngredient.setDishId(dish);
        model.addAttribute("dishIngredient", dishIngredient);
        model.addAttribute("listOfDishes", dishDao.getAllDishes());
        model.addAttribute("listOfIngredients", ingredientDao.getAllIngredients());
        model.addAttribute("listOfUnits", Arrays.asList(Unit.values()));
        return "admin/dish/addDishIngredient";
    }

    @RequestMapping(value = "/admin/saveOrUpdateDishIngredient", method = RequestMethod.POST)
    public String saveOrUpdateDishIngredient(@ModelAttribute DishIngredient dishIngredient, Model model, BindingResult result,
                                             final RedirectAttributes redirectAttributes) {

        boolean error = false;

        if (dishIngredient.getDishId() == null) {
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
            model.addAttribute("listOfDishes", dishDao.getAllDishes());
            model.addAttribute("listOfIngredients", ingredientDao.getAllIngredients());
            model.addAttribute("listOfUnits", Arrays.asList(Unit.values()));
            return "admin/dish/addDishIngredient";
        }

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Ingredient was added successfully!");

        try {
            dishIngredientDao.addIngredientToDish(dishIngredient);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("css", "danger");
            redirectAttributes.addFlashAttribute("msg", "There is such ingredient in dish!");
        }
        return "redirect:/admin/dish/" + dishIngredient.getDishId().getName();
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
        return "redirect:/admin/dish/" + dishName;
    }

    @RequestMapping(value = "/admin/employee/orderBy/{field}", method = RequestMethod.GET)
    public String employeeOrderBy(@PathVariable String field, Model model) {
        model.addAttribute("listOfEmployee", employeeDao.orderBy(field));
        return "admin/employee/allEmployees";
    }

    @RequestMapping(value = "/admin/addEmployee", method = RequestMethod.GET)
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("listOfPositions", positionDao.getAllPosition());
        return "admin/employee/addEmployee";
    }

    @RequestMapping(value = "/admin/addOrUpdateEmployee", method = RequestMethod.POST)
    public String saveOrUpdateEmployee(@ModelAttribute("employee") Employee employee, BindingResult result,
                                       Model model, final RedirectAttributes redirectAttributes) {

        boolean error = false;

        if (employee.getSurname().isEmpty()) {
            result.rejectValue("surname", "error.Surname");
            error = true;
        }

        if (employee.getName().isEmpty()) {
            result.rejectValue("name", "error.Name");
            error = true;
        }

        if (employee.getPosition() == null) {
            result.rejectValue("position", "error.Position");
            error = true;
        }

        if (error) {
            model.addAttribute("listOfPositions", positionDao.getAllPosition());
            return "admin/employee/addEmployee";
        }

        redirectAttributes.addFlashAttribute("css", "success");
        if (employee.isNew()) {
            redirectAttributes.addFlashAttribute("msg", "Employee was added successfully!");
        } else {
            redirectAttributes.addFlashAttribute("msg", "Employee was updated successfully!");
        }

        if (employeeDao.getEmployeeById(employee.getId()) == null) {
            employeeDao.addEmployee(employee);
        } else {
            employeeDao.updateEmployee(employee);
        }
        return "redirect:/admin/employee/orderBy/id";
    }

    @RequestMapping(value = "/admin/employee/{id}/update", method = RequestMethod.GET)
    public String showUpdateUserForm(@PathVariable("id") int id, Model model) {
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("listOfPositions", positionDao.getAllPosition());
        return "admin/employee/addEmployee";
    }

    @RequestMapping(value = "/admin/employee/{id}/delete", method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("css", "success");
        Employee employee = employeeDao.getEmployeeById(id);
        try {
            employeeDao.deleteEmployee(employee);
            redirectAttributes.addFlashAttribute("msg", "Employee was deleted successfully!");
        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("msg", "Employee with id#" + employee.getId() + " can't be deleted! " +
                    "There is one or more references on it");
        }
        return "redirect:/admin/employee/orderBy/id";
    }

    @RequestMapping(value = "/admin/menu/orderBy/{field}", method = RequestMethod.GET)
    public String orderBy(@PathVariable String field, Model model) {
        model.addAttribute("ListOfMenu", menuDao.orderBy(field));
        return "admin/menu/allMenu";
    }

    @RequestMapping(value = "/admin/addMenu", method = RequestMethod.GET)
    public String addMenu(Model model) {
        model.addAttribute("menu", new Menu());
        model.addAttribute("listOfDishes", dishDao.getAllDishes());
        model.addAttribute("listOfMenuNames", menuNameDao.getAllMenuName());
        return "admin/menu/addOrUpdateMenu";
    }

    @RequestMapping(value = "/admin/addOrUpdateMenu", method = RequestMethod.POST)
    public String addNewDish(@ModelAttribute Menu menu, BindingResult result,
                             Model model, final RedirectAttributes redirectAttributes) {

        boolean error = false;

        if (menu.getMenuNameId() == null) {
            result.rejectValue("menuNameId", "error.MenuName");
            error = true;
        }

        if (menu.getDishId() == null) {
            result.rejectValue("dishId", "error.DishName");
            error = true;
        }

        if (error) {
            model.addAttribute("listOfDishes", dishDao.getAllDishes());
            model.addAttribute("listOfMenuNames", menuNameDao.getAllMenuName());
            return "admin/menu/addOrUpdateMenu";
        }

        redirectAttributes.addFlashAttribute("css", "success");
        if (menu.isNew()) {
            redirectAttributes.addFlashAttribute("msg", "Menu was added successfully!");
        } else {
            redirectAttributes.addFlashAttribute("msg", "Menu was updated successfully!");
        }


        if (menuDao.getMenuById(menu.getId()) == null) {
            menuDao.addDishInMenu(menu);

        } else {
            menuDao.updateMenu(menu);
        }
        return "redirect:/admin/menu/orderBy/id";
    }

    @RequestMapping(value = "/admin/deleteDishesByMenuName", method = RequestMethod.GET)
    public String deleteDishesByMenuName(@RequestParam("deleteDishesByMenuName") String deleteDishesByMenuName, Model model) {
        menuDao.deleteDishesByMenuName(deleteDishesByMenuName);
        return "admin/successfulOperation";
    }

    @RequestMapping(value = "/admin/menu/{id}/update", method = RequestMethod.GET)
    public String addDish(@PathVariable int id, Model model) {
        model.addAttribute("menu", menuDao.getMenuById(id));
        model.addAttribute("listOfDishes", dishDao.getAllDishes());
        model.addAttribute("listOfMenuNames", menuNameDao.getAllMenuName());
        return "admin/menu/addOrUpdateMenu";
    }

    @RequestMapping(value = "/admin/menu/{id}/delete", method = RequestMethod.GET)
    public String deleteMenu(@PathVariable int id, final RedirectAttributes redirectAttributes) {
        menuDao.deleteMenuByID(id);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Menu was deleted successfully!");
        return "redirect:/admin/menu/orderBy/id";
    }

    @RequestMapping(value = "/admin/order/orderBy/{field}", method = RequestMethod.GET)
    public String getAllOrders(@PathVariable String field, Model model) {
        model.addAttribute("ListOfOrders", orderDao.orderBy(field));
        return "admin/order/allOrders";
    }

    @RequestMapping(value = "/admin/order/{id}/information", method = RequestMethod.GET)
    public String getOrderInformation(@PathVariable int id, Model model) {
        Order order = orderDao.getOrderById(id);
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

        if (order.getTableNumber() <= 0) {
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
        if (orderDao.getOrderById(order.getId()) == null) {
            redirectAttributes.addFlashAttribute("msg", "Order was added successfully!");
            orderDao.addOrder(order);
        } else {
            redirectAttributes.addFlashAttribute("msg", "Order was updated successfully!");
            orderDao.update(order);
        }
        return "redirect:/admin/order/orderBy/id";
    }

    @RequestMapping(value = "/admin/order/{id}/update", method = RequestMethod.GET)
    public String updateOrder(@PathVariable int id, Model model) {
        Order order = orderDao.getOrderById(id);
        model.addAttribute("order", order);
        model.addAttribute("listOfEmployee", employeeDao.getAllEmployees());
        model.addAttribute("listOfDishes", dishDao.getAllDishes());
        return "admin/order/addOrUpdateOrder";
    }

    @RequestMapping(value = "/admin/order/{id}/closed", method = RequestMethod.GET)
    public String setClosedStatus(@PathVariable int id, final RedirectAttributes redirectAttributes) {
        Order order = orderDao.getOrderById(id);
        order.setStatus(OrderStatus.closed);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Order with id " + id + " was closed!");
        orderDao.update(order);
        return "redirect:/admin/order/orderBy/id";
    }

    @RequestMapping(value = "/admin/order/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable int id, final RedirectAttributes redirectAttributes) {
        Order order = orderDao.getOrderById(id);
        List<CookedDish> cookedDishes = cookedDishDao.getCookedDishesByOrderId(order.getId());
        try {
            orderDao.deleteOrder(order);
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "Order was deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("css", "danger");
            redirectAttributes.addFlashAttribute("msg", "Order #" + order.getId() + " can't be deleted! Some dishes has already cooked");
        }
        return "redirect:/admin/order/orderBy/id";
    }

    @RequestMapping(value = "/admin/warehouse/orderBy/{field}", method = RequestMethod.GET)
    public String warehouseOrderBy(@PathVariable String field, Model model) {
        model.addAttribute("warehouseBalance", warehouseDao.orderBy(field));
        return "admin/warehouse/warehouseBalance";
    }

    @RequestMapping(value = "/admin/addWarehouse", method = RequestMethod.GET)
    public String saveOrUpdateWarehouse(Model model) {
        model.addAttribute("warehouse", new Warehouse());
        model.addAttribute("listOfUnits", Arrays.asList(Unit.values()));
        model.addAttribute("listOfIngredients", ingredientDao.getAllIngredients());
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
            model.addAttribute("listOfIngredients", ingredientDao.getAllIngredients());
            return "admin/warehouse/saveOrUpdateWarehouse";
        }

        redirectAttributes.addFlashAttribute("css", "success");

        if (warehouseDao.getBalanceByID(warehouse.getId()) == null) {
            try {
                warehouseDao.addIngredientIntoWarehouse(warehouse);
                redirectAttributes.addFlashAttribute("msg", "Ingredient was added successfully!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("css", "danger");
                redirectAttributes.addFlashAttribute("msg", "Ingredient '" +
                        warehouse.getIngredientId().getIngredient() + "' has already exist.");
                return "redirect:/admin/warehouse/orderBy/id";
            }
        } else {
            try {
                redirectAttributes.addFlashAttribute("msg", "Ingredient was updated successfully!");
                warehouseDao.updateWarehouseBalance(warehouse);
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("css", "danger");
                redirectAttributes.addFlashAttribute("msg", "Change ingredient name is forbidden!");
                return "redirect:/admin/warehouse/orderBy/id";
            }
        }
        return "redirect:/admin/warehouse/orderBy/id";
    }

    @RequestMapping(value = "/admin/warehouse/{id}/update", method = RequestMethod.GET)
    public String warehouseUpdateUserForm(@PathVariable int id, Model model) {
        Warehouse warehouse = warehouseDao.getBalanceByID(id);
        model.addAttribute("warehouse", warehouse);
        model.addAttribute("listOfUnits", Arrays.asList(Unit.values()));
        model.addAttribute("listOfIngredients", ingredientDao.getAllIngredients());
        return "admin/warehouse/saveOrUpdateWarehouse";
    }

    @RequestMapping(value = "/admin/warehouse/{ingredientName}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("ingredientName") String ingredientName, final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Ingredient was deleted successfully!");
        warehouseDao.deleteIngredientFromWarehouse(ingredientName);
        return "redirect:/admin/warehouse/orderBy/id";
    }

}
