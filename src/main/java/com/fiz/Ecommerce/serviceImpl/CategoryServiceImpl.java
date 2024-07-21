package com.fiz.Ecommerce.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fiz.Ecommerce.model.Category;
import com.fiz.Ecommerce.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private List<Category> categories = new ArrayList<Category>();

	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return categories;
	}

	@Override
	public void createNewCategory(Category category) {
		Long id = 1l;
		if (categories.size() > 0) {
			id = categories.get(categories.size() - 1).getId() + 1;
		}
		category.setId(id);
		categories.add(category);

	}

	@Override
	public String deleteCategory(long id) {
		Category category = categories.stream().filter(c -> c.getId().equals(id)).findFirst()
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found"));

		categories.remove(category);
		return "Category removed with cid : " + id;
	}

	@Override
	public Category updateCategory(Category category,long id) {
		Optional<Category> cat = categories.stream().filter(c -> c.getId().equals(id)).findFirst();
		if(cat.isPresent()) {
			Category exisstingCat = cat.get();
			exisstingCat.setCategoryName(category.getCategoryName());
			return exisstingCat;
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found");	
		}
	}

}
