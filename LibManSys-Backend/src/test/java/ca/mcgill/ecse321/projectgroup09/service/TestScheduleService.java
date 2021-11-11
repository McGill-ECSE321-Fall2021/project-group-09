package ca.mcgill.ecse321.projectgroup09.service;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.projectgroup09.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.projectgroup09.dao.LibrarianRepository;
import ca.mcgill.ecse321.projectgroup09.dao.ScheduleRepository;
import ca.mcgill.ecse321.projectgroup09.models.HeadLibrarian;
import ca.mcgill.ecse321.projectgroup09.models.Librarian;
import ca.mcgill.ecse321.projectgroup09.models.Library;
import ca.mcgill.ecse321.projectgroup09.models.Schedule;

@ExtendWith(MockitoExtension.class)
public class TestScheduleService {
	
	@Mock
	private ScheduleRepository sRepo;
	
	@Mock
	private LibrarianRepository lRepo;
	
	@Mock
	private HeadLibrarianRepository hlRepo;
	
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
	
	// HL1 = HeadLibrarian 1
	private static final Long HL1_ID = 851L;
	private static final Long HL1_ACCOUNT_ID = 751L;
	
	private static final Long HL1_INVALID_ID = -1L;
	
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
		lenient().when(lRepo.findLibrarianByEmployeeIDNum(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(L1_ID)) {
	            Librarian l = new Librarian();
	            l.setemployeeIDNum(L1_ID);
	            return l;
	        } else {
	            return null;
	        }
	    });
		lenient().when(hlRepo.findHeadLibrarianByManagerIDNum(HL1_ID)).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(HL1_ID)) {
	            HeadLibrarian hl = new HeadLibrarian();
	            hl.setemployeeIDNum(HL1_ID);
	            hl.setAccountId(HL1_ACCOUNT_ID);
	            return hl;
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
		lenient().when(hlRepo.save(any(HeadLibrarian.class))).thenAnswer(returnParameterAsAnswer);
		
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
	public void testCreateLibraryScheduleList() {
		
	}
	
	@Test
	public void testCreateLibraryScheduleList_MissingDay() {
		
	}
	
	@Test
	public void testCreateDefaultLibraryScheduleList() {
		HeadLibrarian hl = hlRepo.findHeadLibrarianByManagerIDNum(HL1_ID);
		// Create what default library schedule should be
		List<Schedule> expectedSchedule = new ArrayList<Schedule>();
		for (int i = 1; i < 8; i++) {
			Schedule s = new Schedule();
			s.setscheduleID(Long.valueOf(i));
			s.setLibrarian(hl);
			if (i == 1 || i == 7) {
				s.setOpeningTime(Library.DEFAULT_WEEKEND_OPENING_TIME);
				s.setClosingTime(Library.DEFAULT_WEEKEND_CLOSING_TIME);
			} else {
				s.setOpeningTime(Library.DEFAULT_WEEKDAY_OPENING_TIME);
				s.setClosingTime(Library.DEFAULT_WEEKDAY_CLOSING_TIME);
			}
			expectedSchedule.add(s);
		}
		expectedSchedule.get(0).setDayofWeek(DayOfWeek.SUNDAY);
		expectedSchedule.get(1).setDayofWeek(DayOfWeek.MONDAY);
		expectedSchedule.get(2).setDayofWeek(DayOfWeek.TUESDAY);
		expectedSchedule.get(3).setDayofWeek(DayOfWeek.WEDNESDAY);
		expectedSchedule.get(4).setDayofWeek(DayOfWeek.THURSDAY);
		expectedSchedule.get(5).setDayofWeek(DayOfWeek.FRIDAY);
		expectedSchedule.get(6).setDayofWeek(DayOfWeek.SATURDAY);
		List<Schedule> defaultLibrarySchedule = null;
		try {
			defaultLibrarySchedule = sService.createDefaultLibraryScheduleList(HL1_ID);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		assertNotNull(defaultLibrarySchedule);
		assertEquals(7, defaultLibrarySchedule.size());
		// Assert all schedules in list are equal
		for (int i = 0; i < 7; i++) {
			assertEquals(expectedSchedule.get(i), defaultLibrarySchedule.get(i));
		}			
		assertEquals(1, defaultLibrarySchedule.get(0).getscheduleID());
		assertEquals(DayOfWeek.SUNDAY, defaultLibrarySchedule.get(0).getDayofWeek());
		assertEquals(2, defaultLibrarySchedule.get(1).getscheduleID());
		assertEquals(DayOfWeek.MONDAY, defaultLibrarySchedule.get(1).getDayofWeek());
		assertEquals(3, defaultLibrarySchedule.get(2).getscheduleID());
		assertEquals(DayOfWeek.TUESDAY, defaultLibrarySchedule.get(2).getDayofWeek());
	}
	
	@Test
	public void testCreateDefaultLibraryScheduleList_NullInput() {
		List<Schedule> defaultLibrarySchedule = null;
		String error = null;
		try {
			defaultLibrarySchedule = sService.createDefaultLibraryScheduleList(null);
		} catch (Exception e) {
			error = e.getMessage();
		}
		
		assertNull(defaultLibrarySchedule);
		assertNotNull(error);
		assertEquals("Argument must not be null.", error);
	}
	
	@Test
	public void testCreateDefaultLibraryScheduleList_HeadLibrarianNotExist() {
		List<Schedule> defaultLibrarySchedule = null;
		String error = null;
		try {
			defaultLibrarySchedule = sService.createDefaultLibraryScheduleList(HL1_INVALID_ID);
		} catch (Exception e) {
			error = e.getMessage();
		}
		
		assertNull(defaultLibrarySchedule);
		assertNotNull(error);
		assertEquals("Head librarian does not exist in repository.", error);
	}
	
}
