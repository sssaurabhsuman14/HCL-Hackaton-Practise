package com.hcl.banking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.banking.Entity.Account;
import com.hcl.banking.Repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;

	public Account addAccount(Account account) {

		if (isValidate(account)) {
			accountRepository.save(account);
		} else {
			new Exception("Account Not Created");
		}
		return account;

	}
	
	public Account save(Account account) {
		return accountRepository.save(account);
		
	}
	
	public Account findTransactionByToAccountNumber(Long accountNumber) {
		return null;//accountRepository.findTransactionByToAccountNumber(accountNumber);
		
	}
	public Account findAccountByUserName(String userName) {
		return accountRepository.findAccountByUserName(userName);
	}
	
	public Account findAccountByAccountNumber(Long accountNumber) {
		return accountRepository.findAccountByAccountNumber(accountNumber);
	}

	boolean isValidate(Account account) {

		boolean isValidate = (account.getAccountNumber() != null && account.getUserName() != null);

		if (isValidate) {
			return true;
		}

		return false;
	}

}
