package javaonline.web.admin;

import javaonline.dao.IDishDao;
import javaonline.dao.IMenuDao;
import javaonline.dao.IMenuNameDao;
import javaonline.dao.entity.Dish;
import javaonline.dao.entity.Menu;
import javaonline.dao.entity.MenuName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/menuStructure", method = RequestMethod.GET)
    public String menuStructure() {
        return "admin/menu/menuStructure";
    }

    @RequestMapping(value = "/getAllMenu", method = RequestMethod.GET)
    public String getAllMenu(Map<String, Object> model) {
        model.put("ListOfMenu", menuService.getAllMenu());
        return "admin/menu/allMenu";
    }

    @RequestMapping(value = "/addMenu", method = RequestMethod.GET)
    public ModelAndView addDish() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/menu/addOrUpdateMenu");
        modelAndView.addObject("menu", new Menu());
        List<Dish> dishes = IDishDao.getAllDishes();
        modelAndView.addObject("listOfDishes", dishes);
        List<MenuName> menuNames = IMenuNameDao.getAllMenuName();
        modelAndView.addObject("listOfMenuNames", menuNames);
        return modelAndView;
    }

    @RequestMapping(value = "/menu/{id}/update", method = RequestMethod.GET)
    public ModelAndView addDish(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/menu/addOrUpdateMenu");
        modelAndView.addObject("menu", menuService.getMenuById(id));
        List<Dish> dishes = IDishDao.getAllDishes();
        modelAndView.addObject("listOfDishes", dishes);
        List<MenuName> menuNames = IMenuNameDao.getAllMenuName();
        modelAndView.addObject("listOfMenuNames", menuNames);
        return modelAndView;
    }

    @RequestMapping(value = "/addOrUpdateMenu", method = RequestMethod.POST)
    public ModelAndView addNewDish(@ModelAttribute Menu menu) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/successfulOperation");
        if  (menuService.getMenuById(menu.getId())==null){
            menuService.addDishInMenu(menu);

        } else{
            menuService.updateMenu(menu);
        }
        modelAndView.addObject("message", menu.getDishId().getName() + " was added to menu " + menu.getMenuNameId().getName());
        return modelAndView;
    }

    @RequestMapping(value = "/deleteDishesByMenuName", method = RequestMethod.GET)
    public ModelAndView deleteDishesByMenuName(@RequestParam("deleteDishesByMenuName") String deleteDishesByMenuName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/successfulOperation");
        menuService.deleteDishesByMenuName(deleteDishesByMenuName);
        modelAndView.addObject("message", deleteDishesByMenuName + " was deleted");
        return modelAndView;
    }

    @RequestMapping(value = "/menu/{name}/delete", method = RequestMethod.GET)
    public ModelAndView deleteMenu(@PathVariable String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/successfulOperation");
        modelAndView.addObject("message", name + " was deleted");
        menuService.deleteDishesByMenuName(name);
        return modelAndView;
    }

//    @RequestMapping(value = "/deleteDishFromMenuByName", method = RequestMethod.GET)
//    public ModelAndView deleteDishFromMenuByName(@RequestParam("deleteDishFromMenuByName") String deleteDishFromMenuByName) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("admin/successfulOperation");
//        menuService.deleteDishFromMenu(deleteDishFromMenuByName);
//        modelAndView.addObject("message", deleteDishFromMenuByName + " was deleted from all menu");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/getMenuByName", method = RequestMethod.GET)
//    public ModelAndView getDishByName(@RequestParam("getMenuByName") String getMenuByName) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("ListOfMenu", menuService.getMenuByName(getMenuByName));
//        modelAndView.setViewName("admin/menu/allMenu");
//        return modelAndView;
//    }

}
