package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup09.models.Account;

// CrudRepository<T, ID>, TODO ID should maybe be changed to UUID or something other than string in the future
/**
 * 
 * @author Zarif Ashraf
 *
 */
public interface AccountRepository extends CrudRepository<Account, String> {
	
	Account findAccountByAccountID(Long accountID);
	Account findAccountByFullName(String fullName);
	
}
