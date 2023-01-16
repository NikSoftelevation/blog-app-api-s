package com.example.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.entities.Comment;
import com.example.blog.entities.Post;
import com.example.blog.entities.User;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.payloads.CommentDto;
import com.example.blog.repository.Commentrepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private Commentrepository commentRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId,Integer userId)
	{
		
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		

		Comment comment = modelMapper.map(commentDto, Comment.class);

		comment.setPost(post);
		
		comment.setUser(user);

		Comment savedComment = commentRepository.save(comment);

		return modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public CommentDto getCommentById(Integer commentId) {

		Comment cm = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));

		return modelMapper.map(cm, CommentDto.class);

	}

	@Override
	public List<CommentDto> getAllComments() {

		List<Comment> allComments = commentRepository.findAll();

		List<CommentDto> allCommentDtos = allComments.stream()
				.map((comment) -> modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());

		return allCommentDtos;

	}

	@Override
	public void deleteComment(Integer commentId) {

		Comment commentToDelete = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));

		commentRepository.delete(commentToDelete);

	}
}
