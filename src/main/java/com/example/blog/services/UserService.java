package com.example.blog.services;

import java.util.List;

import com.example.blog.payloads.UserDto;

public interface UserService {
	
	  UserDto createUser(UserDto userDto);
	  UserDto updateUser(UserDto userDto ,Integer userId);
	  UserDto getUserById(Integer userId);
	  List<UserDto> getAllUsers();
	  void deleteUser(int userId);

}
