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
import com.example.blog.payloads.UserDto;
import com.example.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	// POST=> create user

	@Autowired
	private UserService userService;

	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createdUserDto = userService.createUser(userDto);

		return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);

	}

	// PUT=> update user

	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable(value = "userId") Integer userId)
	{

		UserDto updatedUser = userService.updateUser(userDto, userId);

		return new ResponseEntity<>(updatedUser, HttpStatus.OK);

	}

	// DELETE=> delete user

	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable(value = "userId") Integer userId) {

		userService.deleteUser(userId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully", true), HttpStatus.GONE);

	}

	// GET=> get all users

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {

		return ResponseEntity.ok(userService.getAllUsers());
	}

	// GET=> get single user

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUSerById(@PathVariable(value = "userId") Integer userId) {

		return ResponseEntity.ok(userService.getUserById(userId));

	}

}