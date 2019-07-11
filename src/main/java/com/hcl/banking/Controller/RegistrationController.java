package com.hcl.banking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	/** This method will register new user
	 * @param model : RegistationModel to get the registration details
	 * @return : ResponseEntity for the request
	 */
	@PostMapping
	
	public ResponseEntity<String> registerUser(@RequestBody RegistationModel model) {

		if (isValidate(model)) {
			registrationService.registerAndAddUser(model);
			return new ResponseEntity<>("success", HttpStatus.OK);
		}else {
			new Exception("Check form for registration");
		}
		return new ResponseEntity<>("failure", HttpStatus.BAD_REQUEST);

	}
	
	/** This method will validate the registration form
	 * @param registrationModel : RegistationModel for getting the registration details
	 * @return : boolean 'true' if validated else false
	 */
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
