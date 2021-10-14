package ca.mcgill.ecse321.projectgroup09.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.assertj.core.util.Arrays;
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
		accountRepository.deleteAll();
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
	
	@Test
	void testPersistUsersWithSameName() {
		String name1 = "Test Name";
		String name2 = "Test Name";
		Account account1 = new Account();
		Account account2 = new Account();
		account1.setName(name1);
		account2.setName(name2);
		
		accountRepository.saveAll(List.of(account1, account2));
		
		//System.out.println(accountRepository.findUserByName(name1));
		// findByUserName returns first instance of account with name = name1
		
	}
}
