package com.hcl.banking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.banking.Entity.Transaction;
import com.hcl.banking.Model.TransactionModel;
import com.hcl.banking.Service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@PostMapping("/{username}")
	public List<Transaction> getTransactions(@PathVariable("username") String username) {

		List<Transaction> transactions =  transactionService.getTransactions(username);
		return transactions;
	}

	@PostMapping("/process")
	public boolean processTransaction(@RequestBody TransactionModel model) {

		// TODO: Need to validate if user is doing invalid txn to his account only.
		// TODO: Need to also validate if account number receiving is null to avoid null
		// pointer exception

		if (model != null) {
			Transaction txn = toEntity(model);

			transactionService.processTransaction(txn);
			return true;
		}

		return false;
	}

	Transaction toEntity(TransactionModel model) {

		Transaction txn = new Transaction();

		txn.setAccountType(model.getAccountType());
		txn.setDateOfTransaction(model.getDateOfTransaction());
		txn.setFromAccountNumber(model.getFromAccountNumber());
		txn.setToAccountNumber(model.getToAccountNumber());
		txn.setTransferAmount(model.getTransferAmount());
		return txn;

	}
}
