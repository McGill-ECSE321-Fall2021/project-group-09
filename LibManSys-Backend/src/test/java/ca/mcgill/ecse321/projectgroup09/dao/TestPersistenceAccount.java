package ca.mcgill.ecse321.projectgroup09.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


/**
 * No persistence tests for abstract class.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistenceAccount {

	@Autowired
	private AccountRepository accountRepository; 

	@Autowired
	private HeadLibrarianRepository headLibrarianRepository; 

	@Autowired
	private LibrarianRepository librarianRepository; 

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private OnlineMemberRepository onlineMemberRepository; 

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private LoanRepository loanRepository;

	@Autowired 
	private LibraryRepository libraryRepository;
	
	@Autowired 
	private BookRepository bookRepository;
	
	@Autowired 
	private LibraryItemRepository libraryItemRepository;


	@AfterEach
	public void clearAccountsDatabase() {
		loanRepository.deleteAll();
		scheduleRepository.deleteAll();
		headLibrarianRepository.deleteAll();
		librarianRepository.deleteAll();
		bookRepository.deleteAll();
		libraryItemRepository.deleteAll();
		memberRepository.deleteAll();
		onlineMemberRepository.deleteAll();
		accountRepository.deleteAll();
		libraryRepository.deleteAll();
	}
		
}



