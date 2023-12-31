package com.exam.serviceImpl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.Category;
import com.exam.repository.CategoryRepository;
import com.exam.service.CategoryService;

import jakarta.transaction.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Category addCategory(Category category) {

		return this.categoryRepository.save(category);

	}

	@Override
	public Category updateCategory(Category category) {

		return this.categoryRepository.save(category);

	}

	@Override
	public Set<Category> getCategories() {
		return new LinkedHashSet<>(this.categoryRepository.findAll());
	}

	@Override
	public Category getCategoriesById(Long categoryId) {
		return this.categoryRepository.findById(categoryId).get();
	}

	@Override
	@Transactional
	public void deleteCategory(Long categoryId) {

		this.categoryRepository.deleteById(categoryId);

	}

}
