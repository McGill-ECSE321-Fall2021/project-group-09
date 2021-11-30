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
	
	@Autowired
	private OnlineMemberService omService;
	
	public static Account currentAccount = null;
	
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
	
	 @Transactional
	    public OnlineMember loginAsOM(String username, String password) {
	        if (username == null || username.trim().length() == 0) {
	            throw new IllegalArgumentException("Please enter a valid username or email");
	        }
	        if (password == null || password.trim().length() == 0) {
	            throw new IllegalArgumentException("Please enter a valid Password");
	        }

	        List<OnlineMember> onlinemembers = omService.getAllOnlineMembers();
	        
	        OnlineMember foundOM = null;

	        for (OnlineMember om : onlinemembers) {

	            if ((om.getMemberUsername().equals(username) || om.getMemberEmail().equals(username)) && om.getMemberPassword().equals(password)) {
	           
	                currentAccount = om;
	                foundOM = om;
	                break;
	            }
	        }
	        if (foundOM == null) {
	            throw new IllegalArgumentException("Account does not exist, please register a new account or try again.");
	        }

	        return foundOM;

	    }
	 

	 @Transactional
	    public Account getLoggedAccount() {
		
		if (currentAccount == null) {
			throw new IllegalArgumentException("No account is logged into at the moment. Please log in.");
		}
		else {
	        return currentAccount;
	    }
	 }

	    @Transactional
	    public void logout() {
	        currentAccount = null;
	    }

	 }
	 
