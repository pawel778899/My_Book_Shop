package com.example.my_book_shop.service;

import com.example.my_book_shop.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    Category saveCategory(Category category);

    Category getCategoryById(Long id);

    Category updateCategory(Category category);

    void deleteCategoryById(Long id);


}
