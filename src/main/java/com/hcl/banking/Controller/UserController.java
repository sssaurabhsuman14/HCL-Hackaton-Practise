package com.hcl.banking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	/** To get user details
	 * @param id: userId of the user
	 * @return : ResponseEntity User body
	 */
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
		User user = userService.getUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	/** This method will add new user
	 * @param user : User entity details
	 * @return : ResponseEntity User body
	 */
	@PostMapping("/add")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		return new ResponseEntity<>(userService.add(user), HttpStatus.OK); 
	}
	
	/** This method will delete the existing user
	 * @param id : integer id of user
	 * @return : ResponseEntity Boolean body
	 */
	@PostMapping("/delete/{id}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id) {
		Boolean isDeleted = userService.deleteUser(id);
		return new ResponseEntity<>(isDeleted, HttpStatus.OK); 
	}
}
