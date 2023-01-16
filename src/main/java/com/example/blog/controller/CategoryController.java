package com.example.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.blog.payloads.ApiResponse;
import com.example.blog.payloads.CategoryDto;
import com.example.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	// POST-> create category

	@PostMapping("/")
	public ResponseEntity<CategoryDto> cxeateCategory(@Valid @RequestBody CategoryDto categoryDto) {

		CategoryDto createCategory = categoryService.createcategory(categoryDto);

		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);

	}
	// PUT-> update category

	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> cxeateCategory(@Valid  @RequestBody CategoryDto categoryDto,@PathVariable(value = "catId") Integer catId)
	 {

		CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, catId);

		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
	}

	// DELETE-> delete category

	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable(value = "catId") Integer catId)

	{

		categoryService.deleteCategory(catId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("Category is successfully deleted", false),
				HttpStatus.GONE);

	}

	// GET-> get single single category

	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable(value = "catId") Integer catId) {

		CategoryDto categoryDto = categoryService.getCategory(catId);

		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);

	}

	// GET-> get all categories

	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCAtegories() {

		List<CategoryDto> allCategories = categoryService.getAllCategories();

		return ResponseEntity.ok(allCategories);
	}

}
