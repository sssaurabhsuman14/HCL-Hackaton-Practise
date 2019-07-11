package com.hcl.banking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.banking.Entity.User;
import com.hcl.banking.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public User getUserById(Long id) {
		return userRepository.findById(id).get();
	}
	
	public User add(User user) {
		User savedUser = userRepository.save(user);
		return savedUser;
	}
	
	
	public User findUserByUserName(String userName) {
		return userRepository.findUserByUserName(userName);
	}
	
	
	/*public User updateUser(User user) {
		Long id = user.getId();
		String fname = user.getFirstName();
		String lname = user.getLastName();
		User userFound = userRepository.findById(id).get();
		if(userFound != null) {
			userFound.setFirstName(fname);
			userFound.setLastName(lname);
			return userRepository.save(user);
		}
		return user;
	}*/

	public boolean deleteUser(Long id) {
		User userFound = userRepository.findById(id).get();
		if(userFound != null) {
			userRepository.delete(userFound);
			return true;
		}
		return false;
		
	}
	
}
