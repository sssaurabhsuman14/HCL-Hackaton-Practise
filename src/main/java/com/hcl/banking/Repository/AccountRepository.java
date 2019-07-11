package com.hcl.banking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.banking.Entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	public Account findAccountByAccountNumber(Long accountNumber);
	
	public Account findAccountByUserName(String userName);
	
	//public Account findTransactionByToAccountNumber(Long accountNumber);
}
