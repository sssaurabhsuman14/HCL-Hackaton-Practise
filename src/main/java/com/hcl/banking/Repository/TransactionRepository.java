package com.hcl.banking.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.banking.Entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

	public List<Transaction> findByToAccountNumber(Long accountNumber);
	
}
