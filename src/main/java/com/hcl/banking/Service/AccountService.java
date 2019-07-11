package com.hcl.banking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.banking.Entity.Account;
import com.hcl.banking.Repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;

	/**
	 * This method will add account to Account Entity
	 * 
	 * @param account : Account Entity details
	 * @return : Account
	 */
	public Account addAccount(Account account) {

		if (isValidate(account)) {
			accountRepository.save(account);
		} else {
			new Exception("Account Not Created");
		}
		return account;

	}

	/**
	 * This method will save the Account details
	 * 
	 * @param account : Account Entity to be saved
	 * @return : Account Entity
	 */
	public Account save(Account account) {
		return accountRepository.save(account);

	}

	/**
	 * This method will return Account details by filtering on username
	 * 
	 * @param userName : username of the User
	 * @return : Account Entity
	 */
	public Account findAccountByUserName(String userName) {
		return accountRepository.findAccountByUserName(userName);
	}

	/**
	 * This method will return Account details by filtering on username
	 * 
	 * @param accountNumber
	 * @return
	 */
	public Account findAccountByAccountNumber(Long accountNumber) {
		return accountRepository.findAccountByAccountNumber(accountNumber);
	}

	/**
	 * This method will validate the account details
	 * 
	 * @param account : account details that needs to be validated
	 * @return : boolean
	 */
	boolean isValidate(Account account) {

		boolean isValidate = (account.getAccountNumber() != null && account.getUserName() != null);

		if (isValidate) {
			return true;
		}

		return false;
	}

}
