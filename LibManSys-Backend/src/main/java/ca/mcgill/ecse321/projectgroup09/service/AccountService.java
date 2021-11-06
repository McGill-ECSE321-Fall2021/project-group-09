package ca.mcgill.ecse321.projectgroup09.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup09.dao.AccountRepository;

/**
 * Service for abstract model class Account.
 */
@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepo;
	
	private Long nextId;
	
	
	/**
	 * Constructor
	 */
	public AccountService() {
		nextId = 1L;
	}
	
	/**
	 * 
	 * @return
	 */
	@Transactional
	public Long getNextAccountId() {
		Long nextId = this.nextId;
		this.nextId++;
		return nextId;
	}
	
}
