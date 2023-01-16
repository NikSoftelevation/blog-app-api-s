package com.example.blog.payloads;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private int postId;

	private String title;

	private String content;

	private String imageName;

	private Date addedDate;

	private UserDto user;

	private CategoryDto category;

}
