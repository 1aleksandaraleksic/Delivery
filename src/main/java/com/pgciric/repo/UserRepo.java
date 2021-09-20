package com.pgciric.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pgciric.entity.Roles;
import com.pgciric.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.username=:username")
	User getUserByUsername(String username);

	@Query("from roles")
	public List<Roles> getRoles();
}
