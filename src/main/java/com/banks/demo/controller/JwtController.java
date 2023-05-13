package com.banks.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.banks.demo.entity.Role;
import com.banks.demo.entity.User;
import com.banks.demo.jwt.JwtRequest;
import com.banks.demo.jwt.JwtResponse;
import com.banks.demo.jwt.JwtTokenProvider;
import com.banks.demo.repository.RoleRepository;
import com.banks.demo.service.Impl.JwtUserDetailsService;

@RestController
public class JwtController {
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenProvider.generateToken(userDetails);
		System.out.println(token);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	
	@PostMapping("/register")
	public ResponseEntity<User> saveUser(@Validated @RequestBody User user) throws Exception {
		User saveUser = this.userDetailsService.saveUser(user);
		Role role = this.roleRepo.findById(2).get();
		user.getRoles().add(role);
		return new ResponseEntity<User>(saveUser, HttpStatus.CREATED);
	}
	
	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);
		try {
			this.authenticationManager.authenticate(authenticationToken);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("invalid password");
		}
	}

}
