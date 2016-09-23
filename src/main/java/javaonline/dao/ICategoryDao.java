package javaonline.dao;


import javaonline.dao.entity.Category;

import java.util.List;

public interface ICategoryDao {

    void addCategory(Category category);

    void deleteCategoryByName(String category);

    List<Category> getAllCategories();

    Category getCategoryByName(String name);

    Category getCategoryById(long id);
}
