package com.springmvc.controller;

import java.util.List;
import java.util.Optional;

import javax.naming.Binding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springmvc.binding.*;
import com.springmvc.service.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String loadForm(Model model) {
		
		User userObj = new User();
		model.addAttribute("user",userObj );
		return "index";
	}
	
	@PostMapping("/user")
	public String handleMappingBtn(@Valid User user ,BindingResult result, Model model)
	{		
		if(result.hasErrors())
		{
			return "index";
		}
		
		boolean saveUser = userService.saveUser(user);
		System.out.println("User " + user);
		if(saveUser)
		{
		model.addAttribute("msg","User Data Saved Successfully");
		}
		return "redirect:/userCreation";
	}
	
	@GetMapping("/users")
	public String viewUsers(Model model)
	{
		List<User> lstallUsers = userService.getUsers();
		
		model.addAttribute("users", lstallUsers);
		return "view-users";
		
	}
	
	@GetMapping("/user/edit/{id}")
	public String editUser(@PathVariable("id") Integer id, Model model)
	{
		Optional<User> user= userService.editUser(id);
		model.addAttribute("user",user);
		return "index";
		
	}
	
	@GetMapping("/userCreation")
	public String userCreate (Model model) {
		
		User userObj = new User();
		model.addAttribute("user",userObj );
		return "index";
	}
	
	@GetMapping("/user/delete/{id}")
	public String softDeleteUser(@PathVariable("id") Integer id, Model model) {
	    boolean deleted = userService.softDeleteUser(id);
	    if (deleted) {
	        model.addAttribute("msg", "User soft-deleted successfully.");
	    }
	    return "redirect:/users";
	}


}
