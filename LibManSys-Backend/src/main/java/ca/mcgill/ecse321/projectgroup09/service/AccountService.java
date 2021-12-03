package ca.mcgill.ecse321.projectgroup09.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup09.dao.AccountRepository;
import ca.mcgill.ecse321.projectgroup09.models.Account;

/**
 * Service for abstract model class Account.
 */
@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepo;
	
	/**
	 * Returns the account object associated with the specified account id if 
	 * it is present in the account repository.
	 * @param accountId
	 * @return
	 */
	@Transactional
	public Account getAccountById(Long accountId) {
		if (accountId == null) {
			throw new IllegalArgumentException("accountId must not be null.");
		}
		Account a = accountRepo.findAccountByAccountID(accountId);
		return a;
	}
	
}
	 
