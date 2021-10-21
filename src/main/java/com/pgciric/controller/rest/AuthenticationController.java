/**
 * 
 */
package com.pgciric.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgciric.jwt.AuthenticationRequest;
import com.pgciric.jwt.AuthenticationResponse;
import com.pgciric.jwt.JwtUtil;

//@RestController
//@RequestMapping("/api")
//public class AuthenticationController {
//	
//	@Autowired
//	private AuthenticationManager authenticationManager;
//	@Autowired
//	private UserDetailsService userDetailsService;
//	@Autowired
//	private JwtUtil jwtTokenUtil;
	
//	@PostMapping("/authenticate")
//	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest) throws Exception{
//		try {
//			authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
//			);
//		}catch (BadCredentialsException e) {
//			throw new Exception("Incorrect username or password", e);
//		}
//		
//		final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
//		final String jwt = jwtTokenUtil.generateToken(userDetails);
//		
//		return ResponseEntity.ok(new AuthenticationResponse(jwt));
//	}
//}
