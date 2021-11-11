package ca.mcgill.ecse321.projectgroup09.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;

import java.sql.Time;
import java.time.DayOfWeek;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.projectgroup09.dao.LibrarianRepository;
import ca.mcgill.ecse321.projectgroup09.dao.ScheduleRepository;
import ca.mcgill.ecse321.projectgroup09.models.Librarian;
import ca.mcgill.ecse321.projectgroup09.models.Schedule;

@ExtendWith(MockitoExtension.class)
public class TestScheduleService {
	
	@Mock
	private ScheduleRepository sRepo;
	
	@Mock
	private LibrarianRepository lRepo;
	
	@InjectMocks
	private ScheduleService sService;
	
	// Mock attributes
	// S1 = Schedule 1
	private static final Long S1_ID = 901L;
	private static final Time S1_OT = Time.valueOf("09:00:00");
	private static final Time S1_CT = Time.valueOf("16:00:00");
	private static final DayOfWeek S1_DAY = DayOfWeek.MONDAY;
	
	// L1 = Librarian 1
	private static final Long L1_ID = 850L;
	
	// Setup mocks
	@BeforeEach
	public void setMockOutput() {
		// Setup mock schedule
		lenient().when(sRepo.findScheduleByScheduleID(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(S1_ID)) {
	        	Schedule s = new Schedule();
	        	s.setOpeningTime(S1_OT);
	        	s.setClosingTime(S1_CT);
	        	s.setDayofWeek(S1_DAY);
	        	return s;
	        } else {
	            return null;
	        }
	    });
		// Setup mock librarian repo
		// Setup mock member
		lenient().when(lRepo.findLibrarianByEmployeeIDNum(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(L1_ID)) {
	            Librarian l = new Librarian();
	            l.setemployeeIDNum(L1_ID);
	            return l;
	        } else {
	            return null;
	        }
	    });
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(sRepo.save(any(Schedule.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(lRepo.save(any(Librarian.class))).thenAnswer(returnParameterAsAnswer);
		
	}
	
	@Test
	public void testCreateSchedule() {
		// try to create schedule with real input
		
	}
	
	@Test
	public void testCreateSchedule_NullInput() {
		// try to create schedule with null input
		
	}
	
	@Test
	public void testCreateSchedule_InvalidTimes() {
		// try to create schedule with opening time after closing time
		
	}
	
	@Test
	public void testGetScheduleById() {
		
	}
	
	@Test
	public void testGetScheduleById_NotExisting() {
		
	}
	
	@Test
	public void testGetSchedulesByDay() {
		
	}
	
	@Test
	public void testGetSchedulesByDay_NullInput() {
		
	}
	
	@Test
	public void testGetSchedulesByLibrarian() {
		
	}
	
	@Test
	public void testGetSchedulesByLibrarian_NullInput() {
		
	}
	
	@Test
	public void testGetAllSchedules() {
		
	}
	
	@Test
	public void testGetAllSchedules_NullInput() {
		
	}
	
	@Test
	public void testUpdateSchedule() {
		
	}
	
	@Test
	public void testUpdateSchedule_NotExisting() {
		// try to update schedule that does not exist in repository.
		
	}
	
	@Test
	public void testDeleteSchedule() {
		
	}
	
	@Test
	public void testDeleteSchedule_NotExsiting() {
		// try to delete a schedule that does not exist in repository.
		
	}
	
	
	// business methods (only one for service)
	
	@Test
	public void testCreateLibrarySchedule() {
		
	}
	
	@Test
	public void testCreateLibrarySchedule_MissingDay() {
		
	}
	
	
}
