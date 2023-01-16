package com.example.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

	private int id;

	@NotEmpty
	@Size(min = 4,message="username must be of atleast 4 characters")
	private String name;

	@Email(message="Email address is not valid!!")
	private String email;

	@NotEmpty
	@Size(min=3,max = 10,message="Password must be minimum of 3 chars and max. of 10 chars!!!")
	private String password;

	@NotEmpty
	private String about;

}
