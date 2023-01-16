package com.example.blog.payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {

	private String username;
	private String passwprd;

}
