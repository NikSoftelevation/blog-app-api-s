package com.example.blog.controller;

import java.util.List;

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
import com.example.blog.payloads.PostDto;
import com.example.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;

	// POST -> create post

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {

		PostDto createdPost = postService.createPost(postDto, categoryId, userId);

		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);

	}

	// GET -> get by user

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable(value = "userId") Integer userId) {

		List<PostDto> postsByUser = postService.getPostsByUser(userId);

		return new ResponseEntity<List<PostDto>>(postsByUser, HttpStatus.OK);

	}

	// GET-> get by category

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable(value = "categoryId") Integer categoryId) {

		List<PostDto> postsByCategory = postService.getPostsByCategory(categoryId);

		return new ResponseEntity<List<PostDto>>(postsByCategory, HttpStatus.OK);

	}

	// GET-> get all posts

	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPosts() {

		List<PostDto> allPosts = postService.getAllPosts();

		return new ResponseEntity<List<PostDto>>(allPosts, HttpStatus.OK);

	}

	// GET-> get post by id

	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPostbyId(@PathVariable(value = "postId") Integer postId) {

		PostDto postDtoById = postService.getPostById(postId);

		return new ResponseEntity<PostDto>(postDtoById, HttpStatus.OK);

	}

	// DELETE-> delete post

	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable(value = "postId") Integer postId) {

		postService.deletePost(postId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully", true), HttpStatus.GONE);

	}
	// POST-> update post

	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
			@PathVariable(value = "postId") Integer postId) {

		PostDto updatedPost = postService.updatePost(postDto, postId);

		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.ACCEPTED);

	}

	// GET-> search posts

	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPosts(@PathVariable(value = "keywords") String keywords) {

		List<PostDto> searchPosts = postService.searchPosts(keywords);

		return new ResponseEntity<List<PostDto>>(searchPosts, HttpStatus.ACCEPTED);

	}

}
