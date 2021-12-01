package ca.mcgill.ecse321.projectgroup09.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup09.dao.AccountRepository;
import ca.mcgill.ecse321.projectgroup09.dao.LibrarianRepository;
import ca.mcgill.ecse321.projectgroup09.dao.MemberRepository;
import ca.mcgill.ecse321.projectgroup09.dao.OnlineMemberRepository;
import ca.mcgill.ecse321.projectgroup09.models.Account;
import ca.mcgill.ecse321.projectgroup09.models.OnlineMember;

/**
 * Service for abstract model class Account.
 */
@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private OnlineMemberRepository omRepo;
	
	@Autowired
	private MemberRepository memRepo;
	
	@Autowired 
	private LibrarianRepository librarianRepo;
	
	public static OnlineMember currentOMAccount = null;
	
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
	 
