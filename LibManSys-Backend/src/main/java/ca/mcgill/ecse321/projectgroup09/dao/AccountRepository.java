package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup09.models.Account;

// CrudRepository<T, ID>, ID should maybe be changed to UUID or something other than string in the future
public interface AccountRepository extends CrudRepository<Account, String> {
	
	Account findUserByName(String name);
	
}