package ca.mcgill.ecse321.projectgroup09.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup09.dao.LibraryItemRepository;
import ca.mcgill.ecse321.projectgroup09.dao.MemberRepository;
import ca.mcgill.ecse321.projectgroup09.models.Book;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;
import ca.mcgill.ecse321.projectgroup09.models.Member;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestBookService {

	@Autowired
	private LibraryItemRepository libraryItemRepository; 
	
	@Autowired
	private MemberRepository memberRepository;
	
	// Service this class is focused on testing
	@Autowired
	private BookService testService;
	
	@Autowired
	private MemberService memberService;
	
	/*
	@BeforeEach
	private void setupService() {
		service = new LibraryItemReservationService();
	}
	*/
	
	@AfterEach
	public void clearDatabase() {
		memberRepository.deleteAll();
		libraryItemRepository.deleteAll();
	}
	
	@Test
	public void testReserveBook() {
		long libraryItemId = 123L;
		Book book = new Book(libraryItemId);
		book.setItemStatus(LibraryItem.ItemStatus.Available);
		
		long libCardNumber = 999;
		Member member = new Member(libCardNumber);
		member.setFullName("Test Member"); // Id must be set to save
		
		// save book to repo
		libraryItemRepository.save(book);
		// same with member
		memberRepository.save(member);
		
		try {
			testService.reserveBook(member.getLibCardNumber(), book.getlibraryItemID());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail(e.getMessage());
		}
		
		// Check reserve list has been updated
		List<LibraryItem> reserved = memberService.getReservedItems(libCardNumber);
		
		// Should be 1 if service is working properly
		assertEquals(1, reserved.size());
		assertEquals(libraryItemId, reserved.get(0).getlibraryItemID());
		assertEquals(LibraryItem.ItemStatus.Reserved, reserved.get(0).getItemStatus());
	}
}
