package javaonline.web.admin;

import javaonline.dao.IDishDao;
import javaonline.dao.IMenuDao;
import javaonline.dao.IMenuNameDao;
import javaonline.dao.entity.Dish;
import javaonline.dao.entity.Menu;
import javaonline.dao.entity.MenuName;
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
public class MenuController {

    private IMenuDao menuService;
    private IDishDao IDishDao;
    private IMenuNameDao IMenuNameDao;

    @Autowired
    public void setIDishDao(IDishDao IDishDao) {
        this.IDishDao = IDishDao;
    }

    @Autowired
    public void setIMenuNameDao(IMenuNameDao IMenuNameDao) {
        this.IMenuNameDao = IMenuNameDao;
    }

    @Autowired
    public void setMenuService(IMenuDao menuService) {
        this.menuService = menuService;
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(MenuName.class, "menuNameId", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(IMenuNameDao.getMenuNameByName(text));
            }
        });
        binder.registerCustomEditor(Dish.class, "dishId", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(IDishDao.getDishByName(text));
            }
        });
    }

    @RequestMapping(value = "/admin/getAllMenu", method = RequestMethod.GET)
    public String getAllMenu(Model model) {
        model.addAttribute("ListOfMenu", menuService.getAllMenu());
        return "admin/menu/allMenu";
    }

    @RequestMapping(value = "/admin/addMenu", method = RequestMethod.GET)
    public String addDish(Model model) {
        model.addAttribute("menu", new Menu());
        model.addAttribute("listOfDishes", IDishDao.getAllDishes());
        model.addAttribute("listOfMenuNames", IMenuNameDao.getAllMenuName());
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
            model.addAttribute("listOfDishes", IDishDao.getAllDishes());
            model.addAttribute("listOfMenuNames", IMenuNameDao.getAllMenuName());
            return "admin/menu/addOrUpdateMenu";
        }

        redirectAttributes.addFlashAttribute("css", "success");
        if (menu.isNew()) {
            redirectAttributes.addFlashAttribute("msg", "Menu added successfully!");
        } else {
            redirectAttributes.addFlashAttribute("msg", "Menu updated successfully!");
        }


        if (menuService.getMenuById(menu.getId()) == null) {
            menuService.addDishInMenu(menu);

        } else {
            menuService.updateMenu(menu);
        }
        return "redirect:/admin/getAllMenu";
    }

    @RequestMapping(value = "/admin/deleteDishesByMenuName", method = RequestMethod.GET)
    public String deleteDishesByMenuName(@RequestParam("deleteDishesByMenuName") String deleteDishesByMenuName, Model model) {
        menuService.deleteDishesByMenuName(deleteDishesByMenuName);
        return "admin/successfulOperation";
    }

    @RequestMapping(value = "/admin/menu/{id}/update", method = RequestMethod.GET)
    public String addDish(@PathVariable int id, Model model) {
        model.addAttribute("menu", menuService.getMenuById(id));
        model.addAttribute("listOfDishes", IDishDao.getAllDishes());
        model.addAttribute("listOfMenuNames", IMenuNameDao.getAllMenuName());
        return "admin/menu/addOrUpdateMenu";
    }

    @RequestMapping(value = "/admin/menu/{id}/delete", method = RequestMethod.GET)
    public String deleteMenu(@PathVariable int id, final RedirectAttributes redirectAttributes) {
        menuService.deleteMenuByID(id);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Menu was deleted!");
        return "redirect:/admin/getAllMenu";
    }

    @RequestMapping(value = "/admin/menu/orderBy/{field}", method = RequestMethod.GET)
    public String orderBy(@PathVariable String field, Model model) {
        model.addAttribute("ListOfMenu", menuService.orderBy(field));
        return "admin/menu/allMenu";
    }

}
