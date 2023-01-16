package com.example.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.payloads.ApiResponse;
import com.example.blog.payloads.CommentDto;
import com.example.blog.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

	@Autowired
	private CommentService commentService;

	// POST-> create comment

	@PostMapping("/comment/post/{postId}/user/{userId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
			@PathVariable(value = "postId") Integer postId, @PathVariable(value = "userId") Integer userId) {

		CommentDto createdComment = commentService.createComment(commentDto, postId, userId);

		return new ResponseEntity<CommentDto>(createdComment, HttpStatus.OK);

	}

	// GET-> get single comment

	@GetMapping("/get/{commentId}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "commentId") Integer commentId) {

		CommentDto commentById = commentService.getCommentById(commentId);

		return new ResponseEntity<CommentDto>(commentById, HttpStatus.OK);
	}

	// GET-> get all comments

	@GetMapping("/allcomments")
	public ResponseEntity<List<CommentDto>> getAllComments() {
		List<CommentDto> allComments = commentService.getAllComments();

		return new ResponseEntity<List<CommentDto>>(allComments, HttpStatus.OK);

	}

	// DELETE-> delete comment

	@DeleteMapping("/delete/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable(value = "commentId") Integer commentId) {

		commentService.deleteComment(commentId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully", true), HttpStatus.GONE);
	}

}
