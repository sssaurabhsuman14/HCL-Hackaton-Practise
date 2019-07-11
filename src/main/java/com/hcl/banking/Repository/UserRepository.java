package com.hcl.banking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.banking.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findUserByUserName(String userName);
	
}
