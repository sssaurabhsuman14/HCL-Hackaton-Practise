package com.hcl.banking.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.banking.Entity.Account;
import com.hcl.banking.Entity.Transaction;
import com.hcl.banking.Repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	AccountService accountService;

	/**
	 * This method will return all transactions of particilar user
	 * 
	 * @param username : username of the user
	 * @return: List of transactions
	 */
	public List<Transaction> getTransactions(String username) {

		Account account = accountService.findAccountByUserName(username);

		return transactionRepository.findByToAccountNumber(account.getAccountNumber());

	}

	/**
	 * This method will process all the transactions of credit/debit
	 * 
	 * @param txn : Transaction entity details
	 * @return : boolean
	 */
	public boolean processTransaction(Transaction txn) {

		boolean isTransactionValid = (txn != null && txn.getToAccountNumber().toString().length() == 8
				&& txn.getFromAccountNumber().toString().length() == 8 && txn.getTransferAmount() > 0);

		if (isTransactionValid) {

			// Saving txn
			txn.setDateOfTransaction(new Date());

			transactionRepository.save(txn);

			Long toAccountNumber = txn.getToAccountNumber();
			Long fromAccountNumber = txn.getFromAccountNumber();
			Double transferedAmount = txn.getTransferAmount();

			Account toAccountEntity = accountService.findAccountByAccountNumber(toAccountNumber);
			Account fromAccountEntity = accountService.findAccountByAccountNumber(fromAccountNumber);

			// calculate txns and Update account entity
			Double toPerviousAmount = toAccountEntity.getAmount();
			Double fromPerviousAmount = fromAccountEntity.getAmount();

			Double toNewBalance = toPerviousAmount - transferedAmount;
			toAccountEntity.setAmount(toNewBalance);
			accountService.save(toAccountEntity);

			Double fromNewBalance = fromPerviousAmount + transferedAmount;
			fromAccountEntity.setAmount(fromNewBalance);
			accountService.save(fromAccountEntity);

			return true;
		}
		return false;
	}

}
