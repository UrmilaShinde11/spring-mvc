package com.springmvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.binding.User;
import com.springmvc.repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	public boolean saveUser(User user)
	{
		userRepo.save(user);
		return user.getId() >0;
		
	}
	
	public List<User> getUsers()
	{
		//return userRepo.findAll();
		  return userRepo.findAllActiveUsers();  // exc
		
	}
	
	public Optional<User> editUser(Integer id)
	{
		return userRepo.findById(id);
		
	}
	public boolean softDeleteUser(Integer id) {
	    try {
	        User user = userRepo.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
	        user.setDeleted(true);
	        userRepo.save(user);
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}



}
