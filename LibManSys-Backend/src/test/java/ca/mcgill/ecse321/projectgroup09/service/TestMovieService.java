package ca.mcgill.ecse321.projectgroup09.service;

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
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
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
	private static final Long Id = (long) 1111111;
	private static final Integer Runtime = 20;
	private static final Integer PublishedYear = 2021;
	private static final String Title = "TestMovie";
	    
	@BeforeEach
		 public void setMockOutput() {

	        lenient().when(movieRepository.findMovieBylibraryItemID(Id)).thenAnswer((InvocationOnMock invocation) -> {

		        	Movie movie = new Movie();
		            Member member = new Member();
		            movie.setDailyOverdueFee(Fees);
		            movie.setDirector(Director);
		            movie.setGenre(Genre);
		            movie.setItemStatus(ITEMStatus);
		            movie.setlibraryItemID(Id);//
		            movie.setLoanablePeriod(LoanablePeriod);
		            movie.setMember(member);
		            movie.setPublishedYear(Runtime);
		            movie.setRuntime(PublishedYear);
		            movie.setTitle(Title);

		            
		            return movie;

		        });
	        
	        lenient().when(movieRepository.findMoviesByGenre(Genre)).thenAnswer((InvocationOnMock invocation) -> {

	        	Movie movie = new Movie();
	            Member member = new Member();

	            
	            return movie;

	        });
	        
	        lenient().when(movieRepository.findMoviesByDirector(Director)).thenAnswer((InvocationOnMock invocation) ->  {

	        	Movie movie = new Movie();
	            Member member = new Member();

	            
	            return movie;

	        });
	        
	        
			Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> invocation.getArgument(0);
	        lenient().when(libraryItemRepository.save(any(LibraryItem.class))).thenAnswer(returnParameterAsAnswer);
			lenient().when(memberRepository.save(any(Member.class))).thenAnswer(returnParameterAsAnswer);


}


}
