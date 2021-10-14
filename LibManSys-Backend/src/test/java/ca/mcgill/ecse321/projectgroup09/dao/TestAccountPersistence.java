package ca.mcgill.ecse321.projectgroup09.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.projectgroup09.models.Account;
import ca.mcgill.ecse321.projectgroup09.models.Member;

@SpringBootTest
public class TestAccountPersistence {

	@Autowired
	private MemberRepository memberRepository;
	
	@AfterEach
	public void clearDatabase() {
		memberRepository.deleteAll();
	}
	
	@Test
	void testPersistAndLoadUser() {
		String name = "Test User";
		Member account = new Member();
		account.setName(name);
		
		memberRepository.save(account);
		// Set user to null so we can test loading it again
		account = null;
		
		// try to load user from repository
		account = memberRepository.findUserByName(name);
		
		assertNotNull(account);
		assertEquals(name, account.getName());
	}
	
	@Test
	void testPersistUsersWithSameName() {
		String name1 = "Test Name";
		String name2 = "Test Name";
		Member account1 = new Member();
		Member account2 = new Member();
		account1.setName(name1);
		account2.setName(name2);
		
		memberRepository.saveAll(List.of(account1, account2));
		
		//System.out.println(accountRepository.findUserByName(name1));
		// findByUserName returns first instance of account with name = name1
		
	}
}
