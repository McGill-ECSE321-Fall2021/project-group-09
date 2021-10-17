package ca.mcgill.ecse321.projectgroup09.dao;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;
import org.assertj.core.util.Arrays;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestAccountPersistence {

	@Autowired
	private AccountRepository accountRepository; 
	
	@Autowired
	private ArchiveRepository  archiveRepository;
	
	@Autowired
	private BookingRepository bookingrepository; 
	
	@Autowired
	private BookRepository bookrepository;
	
	@Autowired
	private HeadLibrarianRepository headLibrarianRepository; 
	
	@Autowired
	private LibrarianRepository librarianRepository; 
	
	@Autowired
	private LibraryItemRepository libraryItemRepository; 
	
	@Autowired
	private LoanRepository loanRepository;  
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private MovieRepository moveRepository; 
	
	@Autowired
	private MusicAlbumRepository musicAlbumRepository; 
	
	@Autowired
	private NewspaperRepository newspaperRepository; 
	
	@Autowired
	private OnlineMemberRepository onlineMemberRepository; 
	
	@Autowired
	private ScheduleRepository scheduleRepository; 
	
	
	@AfterEach
	public void clearDatabase() {
		memberRepository.deleteAll();
	}
	//Christos tests commented out since the member class has been changed
	/*
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
		
	}*/
}
