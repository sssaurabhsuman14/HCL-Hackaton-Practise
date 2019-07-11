package com.hcl.banking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	/** This method will fetch all the transactions for particular username
	 * @param username : username for which transactions you want
	 * @return
	 */
	@PostMapping("/{username}")
	public ResponseEntity<List<Transaction>> getTransactions(@PathVariable("username") String username) {

		List<Transaction> transactions =  transactionService.getTransactions(username);
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}

	/** This method will start processing the transactions
	 * @param model : TransactionModel to get the transaction details
	 * @return : boolean ResponseEntity
	 */
	@PostMapping("/process")
	public ResponseEntity<Boolean> processTransaction(@RequestBody TransactionModel model) {

		// TODO: Need to validate if user is doing invalid txn to his account only.
		// TODO: Need to also validate if account number receiving is null to avoid null
		// pointer exception

		if (model != null) {
			Transaction txn = toEntity(model);

			transactionService.processTransaction(txn);
			return new ResponseEntity<>(true, HttpStatus.OK);
		}

		return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
	}

	/** This method will convert TransactionModel to Transaction entity
	 * @param model : TransactionModel containing transaction details
	 * @return : Transaction entity
	 */
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
