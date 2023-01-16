package com.example.blog.services;

import java.util.List;

import com.example.blog.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer postId,Integer userId);

	CommentDto getCommentById(Integer commentId);

	List<CommentDto> getAllComments();

	void deleteComment(Integer commentId);
	
}
