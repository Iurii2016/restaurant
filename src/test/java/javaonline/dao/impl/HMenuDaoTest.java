package javaonline.dao.impl;

import javaonline.dao.ICategoryDao;
import javaonline.dao.IDishDao;
import javaonline.dao.IMenuDao;
import javaonline.dao.IMenuNameDao;
import javaonline.dao.entity.Category;
import javaonline.dao.entity.Dish;
import javaonline.dao.entity.Menu;
import javaonline.dao.entity.MenuName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(locations = {"classpath:H-application-context-test.xml", "classpath:hibernate-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class HMenuDaoTest {

    private IMenuNameDao menuNameDao;
    private IMenuDao menuDao;
    private ICategoryDao categoryDao;
    private IDishDao dishDao;

    @Autowired
    public void setMenuNameDao(IMenuNameDao menuNameDao) {
        this.menuNameDao = menuNameDao;
    }

    @Autowired
    public void setMenuDao(IMenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Autowired
    public void setCategoryDao(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Autowired
    public void setDishDao(IDishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Test
    @Transactional
    @Rollback
    public void testGetAllMenuName() throws Exception {
        MenuName menuName = createMenuName();
        menuNameDao.addMenuName(menuName);
        List<MenuName> menuNames = menuNameDao.getAllMenuName();
        assertEquals(menuName.getName(), menuNames.get(0).getName());
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteMenuNameByName() throws Exception {
        MenuName menuName = createMenuName();
        menuNameDao.addMenuName(menuName);
        List<MenuName> menuNames = menuNameDao.getAllMenuName();
        assertEquals(1, menuNames.size());
        menuNameDao.deleteMenuNameByName(menuName.getName());
        List<MenuName> afterDeleteMenuNames = menuNameDao.getAllMenuName();
        assertEquals(0, afterDeleteMenuNames.size());
    }

    @Test
    @Transactional
    @Rollback
    public void testGetAllMenu() throws Exception {
        MenuName menuName = createMenuName();
        menuNameDao.addMenuName(menuName);
        Category category = createCategory();
        categoryDao.addCategory(category);
        Dish dish = createDish(category);
        dishDao.addDish(dish);
        Menu menu = createMenu(menuName, dish);
        menuDao.addDishInMenu(menu);
        List<Menu> menus = menuDao.getAllMenu();
        assertEquals(menuName.getName(), menus.get(0).getMenuNameId().getName());
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateMenu() throws Exception {
        MenuName menuName = createMenuName();
        menuNameDao.addMenuName(menuName);
        Category category = createCategory();
        categoryDao.addCategory(category);
        Dish dish = createDish(category);
        dishDao.addDish(dish);
        Menu menu = createMenu(menuName, dish);
        menuDao.addDishInMenu(menu);
        List<Menu> menus = menuDao.getAllMenu();
        assertEquals(menuName.getName(), menus.get(0).getMenuNameId().getName());
        menuName.setName("newMenuName");
        menu.setMenuNameId(menuName);
        menuDao.updateMenu(menu);
        List<Menu> afterUpdateMenus = menuDao.getAllMenu();
        assertEquals("newMenuName", afterUpdateMenus.get(0).getMenuNameId().getName());
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteDishFromMenu() throws Exception {
        MenuName menuName = createMenuName();
        menuNameDao.addMenuName(menuName);
        Category category = createCategory();
        categoryDao.addCategory(category);
        Dish dish = createDish(category);
        dishDao.addDish(dish);
        Menu menu = createMenu(menuName, dish);
        menuDao.addDishInMenu(menu);
        List<Menu> menus = menuDao.getAllMenu();
        assertEquals(menuName.getName(), menus.get(0).getMenuNameId().getName());
        menuDao.deleteMenuByID(menu.getId());
        List<Menu> afterDeleteMenus = menuDao.getAllMenu();
        assertEquals(0, afterDeleteMenus.size());
    }

    private Category createCategory() {
        Category category = new Category();
        category.setName("shashlik");
        return category;
    }

    private Dish createDish(Category category) {
        Dish dish = new Dish();
        dish.setName("Chicken shashlik");
        dish.setCategoryId(category);
        dish.setPrice(70.0F);
        dish.setWeight(100);
        return dish;
    }

    private MenuName createMenuName() {
        MenuName menuName = new MenuName();
        menuName.setName("grill");
        return menuName;
    }

    private Menu createMenu(MenuName menuName, Dish dish) {
        Menu menu = new Menu();
        menu.setDishId(dish);
        menu.setMenuNameId(menuName);
        return menu;
    }

}