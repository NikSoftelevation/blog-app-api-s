package com.example.blog.service.impl;

import com.example.blog.exception.ResourceNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.entities.User;
import com.example.blog.payloads.UserDto;
import com.example.blog.repository.UserRepository;
import com.example.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {

		User user = dtoToUser(userDto);

		User savedUser = userRepository.save(user);

		return userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		User updatedUser = userRepository.save(user);

		UserDto userToDto1 = userToDto(updatedUser);

		return userToDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		return userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = userRepository.findAll();

		List<UserDto> userDtos = users.stream().map(user -> userToDto(user)).collect(Collectors.toList());

		return userDtos;
	}

	@Override
	public void deleteUser(int userId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));

		userRepository.delete(user);

	}

	public User dtoToUser(UserDto userDto) {

		User user = modelMapper.map(userDto, User.class);

		/*
		 * user.setId(userDto.getId());
		 *  user.setName(userDto.getName());
		 * user.setEmail(userDto.getEmail());
		 *  user.setAbout(userDto.getAbout());
		 * user.setPassword(userDto.getPassword());
		 */

		return user;

	}

	public UserDto userToDto(User user) {

		UserDto userDto = modelMapper.map(user, UserDto.class);

		/*
		 * userDto.setId(user.getId()); 
		 * userDto.setName(user.getName());
		 * userDto.setEmail(user.getEmail());
		 *  userDto.setAbout(user.getAbout());
		 * userDto.setPassword(user.getPassword());
		 */
		return userDto;
	}
}
