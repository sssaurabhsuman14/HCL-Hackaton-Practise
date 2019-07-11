package com.hcl.banking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.banking.Model.RegistationModel;
import com.hcl.banking.Service.RegistrationService;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
	
	@Autowired
	RegistrationService registrationService; 
	
	@PostMapping
	public String registerUser(@RequestBody RegistationModel model) {

		if (isValidate(model)) {
			registrationService.registerAndAddUser(model);
			return "success";
		}else {
			new Exception("Check form for registration");
		}
		return "failure";

	}
	
	//Validation for registration form
	public boolean isValidate(RegistationModel registrationModel) {

		boolean isValidate = (registrationModel.getUserName() != null && registrationModel.getPassword() != null
				&& registrationModel.getConfirmPassword() != null && registrationModel.getDob() != null)
				&& (registrationModel.getPassword().equals(registrationModel.getConfirmPassword()));

		if (isValidate) {
			return true;
		}
		return false;
	}

}
