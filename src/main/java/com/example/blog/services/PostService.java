package com.example.blog.services;

import java.util.List;

import com.example.blog.payloads.PostDto;


public interface PostService {

	PostDto createPost(PostDto postDto, Integer categoryId, Integer userId);

	PostDto updatePost(PostDto postDto, Integer postId);

	void deletePost(Integer postId);

	List<PostDto> getAllPosts();

	PostDto getPostById(Integer postId);

	List<PostDto> getPostsByCategory(Integer categoryId);

	List<PostDto> getPostsByUser(Integer userId);

	List<PostDto> searchPosts(String keyword);
}
