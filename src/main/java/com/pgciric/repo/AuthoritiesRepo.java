package com.pgciric.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pgciric.entity.Authorities;

public interface AuthoritiesRepo extends JpaRepository<Authorities, String> {

	@Query("select a from Authorities a where a.username=:username")
	public Authorities getAuthoritiesByUsername(String username);

}
