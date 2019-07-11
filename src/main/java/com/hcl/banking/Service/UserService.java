package com.hcl.banking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.banking.Entity.User;
import com.hcl.banking.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	/**
	 * This method will return the User details based on it's id
	 * 
	 * @param id : integer id of the user
	 * @return : User details
	 */
	public User getUserById(Long id) {
		return userRepository.findById(id).get();
	}

	/**
	 * This method add new user
	 * 
	 * @param user : User Entity details
	 * @return : User Entity
	 */
	public User add(User user) {
		User savedUser = userRepository.save(user);
		return savedUser;
	}

	/**
	 * This method will return user based on username
	 * 
	 * @param userName : username to be searched
	 * @return : UserEntity
	 */
	public User findUserByUserName(String userName) {
		return userRepository.findUserByUserName(userName);
	}

	/**
	 * This method will delete the existing user
	 * 
	 * @param id : id of user to be deleted
	 * @return : boolean
	 */
	public boolean deleteUser(Long id) {
		User userFound = userRepository.findById(id).get();
		if (userFound != null) {
			userRepository.delete(userFound);
			return true;
		}
		return false;

	}

}
