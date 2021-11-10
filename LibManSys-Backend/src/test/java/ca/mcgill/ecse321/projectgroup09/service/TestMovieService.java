package ca.mcgill.ecse321.projectgroup09.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;


import ca.mcgill.ecse321.projectgroup09.dao.LibraryItemRepository;
import ca.mcgill.ecse321.projectgroup09.dao.MemberRepository;
import ca.mcgill.ecse321.projectgroup09.dao.MovieRepository;
import ca.mcgill.ecse321.projectgroup09.models.Movie;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Loan.LoanStatus;
import ca.mcgill.ecse321.projectgroup09.models.Member;


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
	private static final double Fees = 0.10;
	private static final String Director = "Rajaa";
	private static final String Genre = "Cool";
	private static final ItemStatus ITEMStatus = ItemStatus.Available;
	private static final Integer LoanablePeriod = 7;
	private static final Long movieId = (long) 1111111;
	private static final Integer Runtime = 20;
	private static final Integer PublishedYear = 2021;
	private static final String Title = "TestMovie";
	    
	@BeforeEach
		 public void setMockOutput() {

	        lenient().when(movieRepository.findMovieBylibraryItemID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
	        	if (invocation.getArgument(0).equals(movieId)) {
		        	Movie movie = new Movie();
		            Member member = new Member();
		            movie.setDailyOverdueFee(Fees);
		            movie.setDirector(Director);
		            movie.setGenre(Genre);
		            movie.setItemStatus(ITEMStatus);
		            movie.setlibraryItemID(movieId);//
		            movie.setLoanablePeriod(LoanablePeriod);
		            movie.setMember(member);
		            movie.setPublishedYear(Runtime);
		            movie.setRuntime(PublishedYear);
		            movie.setTitle(Title);
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
	public void createMovie() {
		
		Movie movie1 = null;
		double Fees = 0.10;
		 String Director = "Rajaa";
		 String Genre = "Cool";
		 ItemStatus ITEMStatus = ItemStatus.Available;
		 Integer LoanablePeriod = 7;
		 Long movieId = (long) 1111111;
	   Integer Runtime = 20;
		 Integer PublishedYear = 2021;
		 String Title = "TestMovie";
		try {
			
			movie1 = movieService.createMovie(movieId, Title, PublishedYear, LoanablePeriod, Fees, ITEMStatus, Director, Runtime, Genre);
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void updateMovie() {
		//Movie movie = movieRepository.findMovieBylibraryItemID(movieId);
		Movie movie1 = null;
		double Fees = 0.10;
		 String Director = "Rajaa";
		 String Genre = "Cool";
		 ItemStatus ITEMStatus = ItemStatus.Available;
		 Integer LoanablePeriod = 7;
		 Long movieId = (long) 1111111;
	   Integer Runtime = 20;
		 Integer PublishedYear = 2021;
		 String Title = "TestMovie";
		 
		try {
			
			movie1 = movieService.updateMovie(movieId, Title, PublishedYear, LoanablePeriod, Fees, ITEMStatus, Director, Runtime, Genre);
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		assertNotNull(movie1);
		assertEquals(LoanablePeriod, movie1.getLoanablePeriod());

	}
		// Test Delete Loan //
	@Test
	public void deleteMovie() {
		//Movie movie = null;
		boolean delete =false;
		try {
		 	
			 delete = movieService.deleteMovie(movieId);
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		//assertNotNull(loan);
		assertEquals(true, delete);
	}


}
