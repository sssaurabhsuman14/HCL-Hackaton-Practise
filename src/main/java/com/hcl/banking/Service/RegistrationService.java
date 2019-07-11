package com.hcl.banking.Service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.banking.Entity.Account;
import com.hcl.banking.Entity.User;
import com.hcl.banking.Model.RegistationModel;
import com.hcl.banking.Repository.RegistrationRepository;

@Service
public class RegistrationService {
	@Autowired
	RegistrationRepository registrationRepository;

	@Autowired
	AccountService accountService;

	@Autowired
	UserService userService;

	/**
	 * This method will register and add user to user table with initial amount of
	 * 10,000
	 * 
	 * @param model :RegistationModel details
	 * @return : User Entity
	 */
	public User registerAndAddUser(RegistationModel model) {

		User user = new User();
		user.setUserName(model.getUserName());
		user.setPassword(model.getPassword());
		user.setDob(model.getDob());
		user.setPhoneNumber(model.getPhoneNumber());
		userService.add(user);

		Account account = new Account();
		account.setAccountNumber((long) Math.abs(Math.random() * 100000000));
		account.setAmount(10000d);
		account.setUserName(user.getUserName());
		account.setCreatedDate(new Date());
		accountService.addAccount(account);
		return user;

	}

}
