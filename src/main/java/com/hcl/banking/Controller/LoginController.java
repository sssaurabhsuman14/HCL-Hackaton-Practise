package com.hcl.banking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.banking.Model.LoginModel;
import com.hcl.banking.Service.LoginService;

@RestController

@RequestMapping("/login")
public class LoginController {

	@Autowired
	LoginService loginService;

	@PostMapping
	public ResponseEntity<?> login(@RequestBody LoginModel user) {
		String userName=user.getUserName();
		String password =user.getPassword();

		if (validate(userName, password)) {
			boolean isLoginSuccess = loginService.login(userName, password);

			if (isLoginSuccess) {
				//return "login success";
				
				/*
				 * RestTemplate restTemplate = new RestTemplate();
				 * 
				 * String uri = "http://localhost:8080/bank/transaction/"+userName;
				 * List<Transaction> result = restTemplate.getForObject(uri, List.class);
				 */
				return new ResponseEntity<>("login success", HttpStatus.OK);
				
				
			} else {
				new Exception("User name/ Password is incorrect, Please check !!!");
			}
		}
		return new ResponseEntity<>("login unsuccessful", HttpStatus.BAD_REQUEST);
	}

	public boolean validate(String userName, String password) {
		if (userName != null && password != null) {
			return true;
		}
		return false;
	}

}
