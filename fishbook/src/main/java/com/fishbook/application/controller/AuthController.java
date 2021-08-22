package com.fishbook.application.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fishbook.application.configuration.jwt.JwtUtils;
import com.fishbook.application.model.ERole;
import com.fishbook.application.model.Role;
import com.fishbook.application.model.User;
import com.fishbook.application.repository.RoleRepository;
import com.fishbook.application.repository.UserRepository;
import com.fishbook.application.security.UserDetailsImpl;
import com.fishbook.application.security.pojo.JwtResponse;
import com.fishbook.application.security.pojo.LoginRequest;
import com.fishbook.application.security.pojo.MessageResponse;
import com.fishbook.application.security.pojo.SignupRequest;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)

public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUser_id(), userDetails.getUsername(),
				userDetails.getEmail(), roles));

	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {

		if (userRepository.existsByUsername(signupRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: User is Exist"));

		}

		if (userRepository.existsByEmail(signupRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is Exist"));
		}

		User user = new User(signupRequest.getUsername(), signupRequest.getEmail(),
				passwordEncoder.encode(signupRequest.getPassword()));

		Set<String> reqRoles = signupRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (reqRoles == null) {
			Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error, Role User Is Not Found"));
			roles.add(userRole);
		} else {
			reqRoles.forEach(r -> {

				switch (r) {
				case "admin":
					Role adminRole = roleRepository.findByRoleName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error, Role Admin Is Not Found"));
					roles.add(adminRole);
					break;
				case "renter":
					Role renterRole = roleRepository.findByRoleName(ERole.ROLE_RENTER)
							.orElseThrow(() -> new RuntimeException("Error, Role Renter Is Not Found"));
					roles.add(renterRole);
					break;
				default:
					Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error, Role User Is Not Found"));
					roles.add(userRole);
				}

			});
		}
		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User Created"));
	}

}
