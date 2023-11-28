package com.exam.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Category;
import com.exam.response.DeleteResponse;
import com.exam.service.CategoryService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/")
	public ResponseEntity<?> addCategory(@RequestBody Category category) {

		Category addCategory = categoryService.addCategory(category);

		return ResponseEntity.ok(addCategory);

	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<?> getCategoryByCategoryId(@PathVariable("categoryId") Long categoryId) {
		Category categoriesById = this.categoryService.getCategoriesById(categoryId);

		return ResponseEntity.ok(categoriesById);
	}

	@GetMapping("/")
	public ResponseEntity<?> getAllCategories() {
		Set<Category> categories = this.categoryService.getCategories();

		return ResponseEntity.ok(categories);
	}

	@PutMapping("/")
	public ResponseEntity<?> updateCategory(@RequestBody Category category) {

		Category updateCategory = this.categoryService.updateCategory(category);
		return ResponseEntity.ok(updateCategory);

	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Long categoryId) {
		this.categoryService.deleteCategory(categoryId);
		return ResponseEntity.ok(new DeleteResponse("successfully deleted..", categoryId));

	}

}
