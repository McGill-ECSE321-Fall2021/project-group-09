package ca.mcgill.ecse321.projectgroup09.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.projectgroup09.dao.LoanRepository;
import ca.mcgill.ecse321.projectgroup09.dao.LibraryItemRepository;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;

/**
 * author: Rajaa
 */
@ExtendWith(MockitoExtension.class)
public class TestLoanService {

	@Mock
	private LoanRepository loanRepository;

	@Mock
	private LibraryItemRepository libraryItemRepository;

	@InjectMocks
	private LoanService loanService;

	@BeforeEach
	public void setMockOutput() {
	}
	// Test Create Loan //

	// Test Delete Loan //

	// Test Add Late Fees //

	// Test Remove Late Fees //

	// Test Change Loan Status //

}
