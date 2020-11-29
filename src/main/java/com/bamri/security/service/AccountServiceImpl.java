package com.bamri.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bamri.security.dao.AppRole;
import com.bamri.security.dao.AppUser;
import com.bamri.security.repository.AppRoleRepository;
import com.bamri.security.repository.AppUserRepository;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	// Injection des attributs soit par l'annotation autowired ou par constructor.
	@Autowired
	private AppRoleRepository appRoleRepository;
	
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
//	public AccountServiceImpl(AppRoleRepository appRoleRepository, AppUserRepository appUserRepository,
//			BCryptPasswordEncoder bCryptPasswordEncoder) {
//		super();
//		this.appRoleRepository = appRoleRepository;
//		this.appUserRepository = appUserRepository;
//		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//	}

	@Override
	public AppUser saveUser(String username, String password, String confirmedPassword) {
		AppUser user = appUserRepository.findByUsername(username);
		if(user != null) throw new RuntimeException("User Already Exists !");
		if(!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password !");
		
		AppUser appUser = new AppUser();
		appUser.setUsername(username);
		appUser.setPassword(bCryptPasswordEncoder.encode(password));
		appUser.setActivated(true);
		appUserRepository.save(appUser);
		addRoleToUser(username, "USER");
		return appUser;
	}

	@Override
	public AppRole saveRole(AppRole role) {
		// TODO Auto-generated method stub
		return appRoleRepository.save(role);
	}

	@Override
	public AppUser loadUserByUsername(String username) {
		// TODO Auto-generated method stub
		return appUserRepository.findByUsername(username);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		// TODO Auto-generated method stub
		AppUser appUser = appUserRepository.findByUsername(username);
		AppRole appRole = appRoleRepository.findByRoleName(roleName);
		
		appUser.getRoles().add(appRole);
		
	}

}
