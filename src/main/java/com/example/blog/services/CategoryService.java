package com.example.blog.services;

import java.util.List;

import com.example.blog.payloads.CategoryDto;

public interface CategoryService {
	
	public CategoryDto createcategory(CategoryDto categoryDto);
	
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

	public void deleteCategory(Integer categoryId);
	
	public CategoryDto getCategory(Integer categoryId);
	
	public List<CategoryDto> getAllCategories();
	
}
