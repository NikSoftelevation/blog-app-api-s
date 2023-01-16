package com.example.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.entities.Category;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.payloads.CategoryDto;
import com.example.blog.repository.CategoryRepository;
import com.example.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createcategory(CategoryDto categoryDto) {

		Category cat = modelMapper.map(categoryDto, Category.class);

		Category addedCat = categoryRepository.save(cat);

		return modelMapper.map(addedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

		Category cat = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

		cat.setCategoryTitle(categoryDto.getCategoryTitle());

	    cat.setCategoryDescription(categoryDto.getCategoryDescription());
	    
		Category updatedCat = categoryRepository.save(cat);

		return modelMapper.map(updatedCat, CategoryDto.class);

	}

	@Override
	public void deleteCategory(Integer categoryId) {

		Category cat = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

		categoryRepository.delete(cat);

	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {

		Category cat = categoryRepository.findById(categoryId)
		.orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));	
		
			
		
		return modelMapper.map(cat,CategoryDto.class);
		

	}

	@Override
	public List<CategoryDto> getAllCategories() {

		
	List<Category> categories = categoryRepository.findAll();
		
	
	List<CategoryDto> catDtos = categories.stream().map((cat)-> modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
	
	
	return catDtos;
	
	}

}
