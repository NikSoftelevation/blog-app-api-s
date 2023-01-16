package com.example.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.entities.Category;
import com.example.blog.entities.Post;
import com.example.blog.entities.User;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.payloads.PostDto;
import com.example.blog.repository.CategoryRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public PostDto createPost(PostDto postDto, Integer categoryId, Integer userId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

		Post post = modelMapper.map(postDto, Post.class);

		post.setImageName("default.png");

		post.setAddedDate(new Date());

		post.setUser(user);

		post.setCategory(category);

		Post newPost = postRepository.save(post);

		return modelMapper.map(newPost, PostDto.class);

	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

		post.setTitle(postDto.getTitle());

		post.setContent(postDto.getContent());

		post.setImageName(postDto.getImageName());

		Post updatedPost = postRepository.save(post);

		return modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		;

		postRepository.delete(post);
	}

	@Override
	public List<PostDto> getAllPosts() {

		List<Post> posts = postRepository.findAll();

		List<PostDto> postDtos = posts.stream().map((post) -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public PostDto getPostById(Integer postId) {

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {

		Category cat = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

		List<Post> posts = postRepository.findByCategory(cat);

		List<PostDto> postDtos = posts.stream().map((post) -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

		List<Post> posts = postRepository.findByUser(user);

		List<PostDto> postDtos = posts.stream().map((post) -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;

	}

	@Override
	public List<PostDto> searchPosts(String keyword) {

		List<Post> posts = postRepository.findByTitleContaining("%"+keyword+"%");

		List<PostDto> postDtos = posts.stream().map((post) -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;

	}

}
