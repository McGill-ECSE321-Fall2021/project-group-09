package ca.mcgill.ecse321.projectgroup09.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.projectgroup09.dao.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository acountRepo;
	
	
	
}
