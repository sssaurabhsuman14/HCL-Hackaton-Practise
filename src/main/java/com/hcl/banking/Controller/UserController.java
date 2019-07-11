package com.hcl.banking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.banking.Entity.User;
import com.hcl.banking.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable("id") Long id) {
		User user = userService.getUserById(id);
		return user;
	}

	@PostMapping("/add")
	public User addUser(@RequestBody User user) {
		return userService.add(user);
	}
	
	/*@PostMapping("/update")
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}*/
	
	@PostMapping("/delete/{id}")
	public Boolean deleteUser(@PathVariable("id") Long id) {
		Boolean isDeleted = userService.deleteUser(id);
		return isDeleted;
	}
}
