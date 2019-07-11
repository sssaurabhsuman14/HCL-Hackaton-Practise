package com.hcl.banking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.banking.Entity.User;

@Service
public class LoginService {

	@Autowired
	UserService userService;

	/**
	 * This method will is used to make login
	 * 
	 * @param userName : useraname of the user
	 * @param password : password of the user
	 * @return
	 */
	public Boolean login(String userName, String password) {

		User userAlreadyExist = userService.findUserByUserName(userName);

		if (userAlreadyExist != null && (userAlreadyExist.getPassword()).equals(password)) {
			return true;
		}
		return false;
	}
}
