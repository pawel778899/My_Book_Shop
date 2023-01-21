package com.example.my_book_shop.controller;

import com.example.my_book_shop.model.Category;
import com.example.my_book_shop.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        super();
        this.categoryService = categoryService;
    }


    // handler method to handle list categories and return model and view
    @GetMapping("/categories")
    public String categoryList(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories";
    }

    @GetMapping("/categories/new")
    public String createCategoryForm(Model model) {

        // create student object to hold category form data
        Category category = new Category();
        model.addAttribute("category", category);
        return "create_category";

    }

    @PostMapping("/categories")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/categories/edit/{id}")
    public String editCategoryForm(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "edit_category";
    }

    @PostMapping("/categories/{id}")
    public String updateCategory(@PathVariable Long id,
                                @ModelAttribute("category") Category category,
                                Model model) {

        // get category from database by id
        Category existingCategory= categoryService.getCategoryById(id);
        existingCategory.setId(id);
        existingCategory.setName(category.getName());

        // save updated category object
        categoryService.updateCategory(existingCategory);
        return "redirect:/categories";
    }

    // handler method to handle delete category request

    @GetMapping("/categories/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/categories";
    }

}
