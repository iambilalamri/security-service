package com.bamri.security.service;

import com.bamri.security.dao.AppRole;
import com.bamri.security.dao.AppUser;

public interface AccountService {

	public AppUser saveUser(String username, String password, String confirmedPassword);
	
	public AppRole saveRole(AppRole role);
	
	public AppUser loadUserByUsername(String username);
	
	public void addRoleToUser(String username, String roleName);
}
