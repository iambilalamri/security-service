package com.bamri.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bamri.security.dao.AppUser;

@RepositoryRestResource
public interface AppUserRepository extends JpaRepository<AppUser, Long>{
	
	public AppUser findByUsername(String username);
}
