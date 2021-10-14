package ca.mcgill.ecse321.projectgroup09.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.projectgroup09.models.Account;

@SpringBootTest
public class TestAccountPersistence {

	@Autowired
	private AccountRepository accountRepository;
	
	@AfterEach
	public void clearDatabase() {
		// userRepository.deleteAll();
	}
	
	@Test
	void testPersistAndLoadUser() {
		String name = "Test User";
		Account account = new Account();
		account.setName(name);
		
		accountRepository.save(account);
		// Set user to null so we can test loading it again
		account = null;
		
		// try to load user from repository
		account = accountRepository.findUserByName(name);
		
		assertNotNull(account);
		assertEquals(name, account.getName());
	}
}
