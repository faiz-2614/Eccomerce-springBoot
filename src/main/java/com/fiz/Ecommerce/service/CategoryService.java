package com.fiz.Ecommerce.service;

import java.util.List;

import com.fiz.Ecommerce.model.Category;

public interface CategoryService {
	
	public List<Category> getAllCategories();
	public void createNewCategory(Category category);
	public String deleteCategory(long id);
	public Category updateCategory(Category category,long id);
}
