package ca.mcgill.ecse321.projectgroup09.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.test.util.ReflectionTestUtils;

import ca.mcgill.ecse321.projectgroup09.dao.LibraryItemRepository;
import ca.mcgill.ecse321.projectgroup09.dao.MemberRepository;
import ca.mcgill.ecse321.projectgroup09.dao.MovieRepository;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Member;
import ca.mcgill.ecse321.projectgroup09.models.Movie;


/**
 * author: Rajaa
 */
@ExtendWith(MockitoExtension.class)
public class TestMovieService {
	
	@Mock
    private MovieRepository movieRepository;
	
	@Mock
    private LibraryItemRepository libraryItemRepository;
	
	@Mock
    private MemberRepository memberRepository;

	@InjectMocks
    private MovieService movieService;
	private static final double FEES = 0.10;
	private static final String DIRECTOR = "Rajaa";
	private static final String GENRE = "Cool";
	private static final ItemStatus ITEMSTATUS = ItemStatus.Available;
	private static final Integer LOANABLEPERIOD = 7;
	private static final Long MOVIEID = (long) 1111111;
	private static final Integer RUNTIME = 20;
	private static final Integer PUBLISHEDYEAR = 2021;
	private static final String TITLE = "TestMovie";
	    
	@BeforeEach
		 public void setMockOutput() {

	        lenient().when(movieRepository.findMovieBylibraryItemID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
	        	if (invocation.getArgument(0).equals(MOVIEID)) {
		        	Movie movie = new Movie();
		            Member member = new Member();
		            movie.setDailyOverdueFee(FEES);
		            movie.setDirector(DIRECTOR);
		            movie.setGenre(GENRE);
		            movie.setItemStatus(ITEMSTATUS);
		            ReflectionTestUtils.setField(movie, "libraryItemID", MOVIEID);
		            movie.setLoanablePeriod(LOANABLEPERIOD);
		            movie.setMember(member);
		            movie.setPublishedYear(RUNTIME);
		            movie.setRuntime(PUBLISHEDYEAR);
		            movie.setTitle(TITLE);
		            return movie;
	        	}
	        	else {return null;}
		         

		        });
	        
			 //maybe mocks for a libraryitem and member

	        
			Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> invocation.getArgument(0);
	        lenient().when(libraryItemRepository.save(any(LibraryItem.class))).thenAnswer(returnParameterAsAnswer);
			lenient().when(memberRepository.save(any(Member.class))).thenAnswer(returnParameterAsAnswer);


}

	@Test
	public void testCreateMovie() {
		
		Movie movie1 = null;
		String error = null;

		//double Fees = 0.10;
		try {
			
			movie1 = movieService.createMovie(TITLE, PUBLISHEDYEAR, DIRECTOR, RUNTIME, GENRE);
		}
		catch (Exception e) {
			error = (e.getMessage());
		}
		assertNotNull(movie1);
		assertNull(error);
	}
	
	@Test
	public void testCreateMovieNoDirector() {
		
		Movie movie1 = null;
		String error = null;
		String director = null;

		//double Fees = 0.10;
		try {
			
			movie1 = movieService.createMovie(TITLE, PUBLISHEDYEAR, director, RUNTIME, GENRE);
		}
		catch (Exception e) {
			error = (e.getMessage());
		}
		assertNull(movie1);
		assertNotNull(error);

	}
	
	@Test
	public void testCreateMovieNoGenre() {
		
		Movie movie1 = null;
		String error = null;
		String genre = null;

		//double Fees = 0.10;
		try {
			
			movie1 = movieService.createMovie(TITLE, PUBLISHEDYEAR, DIRECTOR, RUNTIME, genre);
		}
		catch (Exception e) {
			error = (e.getMessage());
		}
		assertNull(movie1);
		assertNotNull(error);

	}
	
	@Test
	public void testCreateMovieNoPublishedYear() {
		
		Movie movie1 = null;
		String error = null;
		Integer publishedYear = null;

		//double Fees = 0.10;
		try {
			
			movie1 = movieService.createMovie(TITLE, publishedYear, DIRECTOR, RUNTIME, GENRE);
		}
		catch (Exception e) {
			error = (e.getMessage());
		}
		assertNull(movie1);
		assertNotNull(error);

	}	

	@Test
	public void testUpdateMovie() {
		//Movie movie = movieRepository.findMovieBylibraryItemID(movieId);
		Movie movie1 = null;
		
		 
		try {
			
			movie1 = movieService.updateMovie(MOVIEID, TITLE, PUBLISHEDYEAR, LOANABLEPERIOD, FEES, ITEMSTATUS, DIRECTOR, RUNTIME, GENRE);
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		assertNotNull(movie1);
		assertEquals(LOANABLEPERIOD, movie1.getLoanablePeriod());

	}
	
	@Test
	public void testUpdateMovieNoDirector() {
		
		Movie movie1 = null;
		String error = null;
		String director = null;

		//double Fees = 0.10;
		try {
			
			movie1 = movieService.updateMovie(MOVIEID, TITLE, PUBLISHEDYEAR, LOANABLEPERIOD, FEES, ITEMSTATUS, director, RUNTIME, GENRE);
		}
		catch (Exception e) {
			error = (e.getMessage());
		}
		assertNull(movie1);
		assertNotNull(error);

	}
	
	@Test
	public void testUpdateMovieNoGenre() {
		
		Movie movie1 = null;
		String error = null;
		String genre = null;

		//double Fees = 0.10;
		try {
			
			movie1 = movieService.updateMovie(MOVIEID, TITLE, PUBLISHEDYEAR, LOANABLEPERIOD, FEES, ITEMSTATUS, DIRECTOR, RUNTIME, genre);
		}
		catch (Exception e) {
			error = (e.getMessage());
		}
		assertNull(movie1);
		assertNotNull(error);

	}
	
	@Test
	public void testUpdateMovieNoPublishedYear() {
		
		Movie movie1 = null;
		String error = null;
		Integer publishedYear = null;

		//double Fees = 0.10;
		try {
			
			movie1 = movieService.updateMovie(MOVIEID, TITLE, publishedYear, LOANABLEPERIOD, FEES, ITEMSTATUS, DIRECTOR, RUNTIME, GENRE);
		}
		catch (Exception e) {
			error = (e.getMessage());
		}
		assertNull(movie1);
		assertNotNull(error);

	}
	
	@Test
	public void testUpdateMovieNoLoablePeriod() {
		
		Movie movie1 = null;
		String error = null;
		Integer loanablePeriod = null;

		//double Fees = 0.10;
		try {
			
			movie1 = movieService.updateMovie(MOVIEID, TITLE, PUBLISHEDYEAR, loanablePeriod, FEES, ITEMSTATUS, DIRECTOR, RUNTIME, GENRE);
		}
		catch (Exception e) {
			error = (e.getMessage());
		}
		assertNull(movie1);
		assertNotNull(error);

	}
		// Test Delete Loan //
	@Test
	public void testDeleteMovie() {
		//Movie movie = null;
		boolean delete =false;
		try {
		 	
			 delete = movieService.deleteMovie(MOVIEID);
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		//assertNotNull(loan);
		assertEquals(true, delete);
	}


}
