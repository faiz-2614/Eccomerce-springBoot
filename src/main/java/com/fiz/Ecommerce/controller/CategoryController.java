package com.fiz.Ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fiz.Ecommerce.model.Category;
import com.fiz.Ecommerce.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {

	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	@GetMapping("/public/categories")
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> categories = categoryService.getAllCategories();

		return new ResponseEntity<>(categories, HttpStatus.OK);

	}

	@PostMapping("/admin/category")
	public ResponseEntity<String> createCategory(@RequestBody Category category) {
		categoryService.createNewCategory(category);
		return new ResponseEntity<>("Category Added", HttpStatus.OK);
	}

	@DeleteMapping("/admin/categories/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
		try {
			String status = categoryService.deleteCategory(id);
			return new ResponseEntity<>(status, HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		}

	}

	@PutMapping("/admin/categories/{id}")
	public ResponseEntity<String> deleteCategory(@RequestBody Category category,@PathVariable long id) {
		try {
			Category status = categoryService.updateCategory(category,id);
			return new ResponseEntity<>("Updated", HttpStatus.OK);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		}

	}
}

