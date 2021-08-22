package com.fishbook.application.security.pojo;

import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupRequest {

	private String username;
	private String email;
	private Set<String> roles;
	private String password;
	
	
	
}
