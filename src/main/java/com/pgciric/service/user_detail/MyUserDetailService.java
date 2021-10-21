/**
 * 
 */
package com.pgciric.service.user_detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pgciric.repo.UserRepo;

@Service
public class MyUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("usao da trazi usera");
		com.pgciric.entity.User user = userRepo.getUserByUsername(username);
		System.out.println("nasao usera" + user.getUsername());
		return new User(user.getUsername(), user.getPassword(), user.getGrantedAuth());
	}

}
