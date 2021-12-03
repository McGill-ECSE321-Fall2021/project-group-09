package ca.mcgill.ecse321.projectgroup09.service;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
	private static final Long NAN_SCHEDULE_ID = -1L;
	
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
		lenient().when(sRepo.findSchedulesByDayofWeek(any(DayOfWeek.class))).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(S1_DAY)) {
	        	List<Schedule> s = new ArrayList<Schedule>();
	        	s.add(new Schedule());
	        	s.get(0).setOpeningTime(S1_OT);
	        	s.get(0).setClosingTime(S1_CT);
	        	s.get(0).setDayofWeek(S1_DAY);
	        	s.get(0).setscheduleID(S1_ID);
	        	Librarian l = new Librarian();
	        	l.setemployeeIDNumber(L1_ID);
	        	s.get(0).setLibrarian(l);
	        	return s;
	        } else {
	            return null;
	        }
	    });
		lenient().when(sRepo.findScheduleByLibrarian(any(Librarian.class))).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(any(Librarian.class))) {
	        	List<Schedule> s = new ArrayList<Schedule>();
	        	s.add(new Schedule());
	        	s.get(0).setOpeningTime(S1_OT);
	        	s.get(0).setClosingTime(S1_CT);
	        	s.get(0).setDayofWeek(S1_DAY);
	        	Librarian l = new Librarian();
	        	l.setemployeeIDNumber(L1_ID);
	        	s.get(0).setLibrarian(l);
	        	return s;
	        } else {
	            return null;
	        }
	    });
		lenient().when(sRepo.findAll()).thenAnswer( (InvocationOnMock invocation) -> {
        	List<Schedule> s = new ArrayList<Schedule>();
        	s.add(new Schedule());
        	s.get(0).setOpeningTime(S1_OT);
        	s.get(0).setClosingTime(S1_CT);
        	s.get(0).setDayofWeek(S1_DAY);
        	Librarian l = new Librarian();
        	l.setemployeeIDNumber(L1_ID);
        	s.get(0).setLibrarian(l);
        	return s;
	    });
		// Setup mock librarian repo
		lenient().when(lRepo.findLibrarianByEmployeeIDNumber(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(L1_ID)) {
	            Librarian l = new Librarian();
	            l.setemployeeIDNumber(L1_ID);
	            return l;
	        } else {
	            return null;
	        }
	    });
		lenient().when(hlRepo.findHeadLibrarianByManagerIDNum(HL1_ID)).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(HL1_ID)) {
	            HeadLibrarian hl = new HeadLibrarian();
	            hl.setemployeeIDNumber(HL1_ID);
	            hl.setAccountID(HL1_ACCOUNT_ID);
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
		// deleted mock
		
	}
	
	@Test
	public void testCreateSchedule() {
		// try to create schedule with real input
		Time openingTime = Time.valueOf("10:00:00");
		Time closingTime = Time.valueOf("18:00:00");
		DayOfWeek day = DayOfWeek.FRIDAY;
		//Librarian l = lRepo.findLibrarianByEmployeeIDNum(L1_ID);
		Schedule s = null;
		try {
			s = sService.createScheduleForLibrarian(openingTime, closingTime, day, L1_ID);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertNotNull(s);
		assertEquals(openingTime, s.getOpeningTime());
		assertEquals(closingTime, s.getClosingTime());
		assertEquals(day, s.getDayofWeek());
		assertEquals(L1_ID, s.getLibrarian().getemployeeIDNumber());
	}
	
	@Test
	public void testCreateSchedule_NullInput() {
		String error = null;
		// try to create schedule with null input
		Time openingTime = Time.valueOf("10:00:00");
		Time closingTime = Time.valueOf("18:00:00");
		DayOfWeek day = null;
		Schedule s = null;
		try {
			s = sService.createScheduleForLibrarian(openingTime, closingTime, day, L1_ID);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(s);
		assertNotNull(error);
		assertEquals("Arguments must not be null.", error);
	}
	
	@Test
	public void testCreateSchedule_InvalidTimes() {
		// try to create schedule with opening time after closing time
		// try to create schedule with real input
		Time openingTime = Time.valueOf("10:00:00");
		Time closingTime = Time.valueOf("8:00:00");
		DayOfWeek day = DayOfWeek.FRIDAY;
		//Librarian l = lRepo.findLibrarianByEmployeeIDNum(L1_ID);
		Schedule s = null;
		String error = null;
		try {
			s = sService.createScheduleForLibrarian(openingTime, closingTime, day, L1_ID);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(s);
		assertNotNull(error);
		assertEquals("Cannot create a schedule with closing time before opening time.", error);
	}
	
	@Test
	public void testGetScheduleById() {
		Schedule s = null;
		try {
			s = sService.getScheduleById(S1_ID);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertNotNull(s);
		assertEquals(S1_OT, s.getOpeningTime());
		assertEquals(S1_CT, s.getClosingTime());
		assertEquals(S1_DAY, s.getDayofWeek());
	}
	
	@Test
	public void testGetScheduleById_NotExisting() {
		Schedule s = null;
		String error = null;
		try {
			s = sService.getScheduleById(NAN_SCHEDULE_ID);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(s);
		assertNotNull(error);
	}
	
	@Test
	public void testGetSchedulesByDay() {
		List<Schedule> sList = null;
		try {
			sList = sService.getSchedulesByDay(S1_DAY);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertNotNull(sList);
		assertEquals(1, sList.size());
		assertEquals(S1_DAY, sList.get(0).getDayofWeek());
		assertEquals(S1_ID, sList.get(0).getscheduleID());
	}
	
	@Test
	public void testGetSchedulesByDay_NullInput() {
		List<Schedule> sList = null;
		String error = null;
		try {
			sList = sService.getSchedulesByDay(null);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(sList);
		assertNotNull(error);
		assertEquals("Argument must not be null.", error);
	}
	
	@Test
	public void testGetSchedulesByLibrarian() {
		List<Schedule> sList = null;
		try {
			sList = sService.getSchedulesByDay(S1_DAY);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertNotNull(sList);
		assertEquals(1, sList.size());
		assertEquals(S1_DAY, sList.get(0).getDayofWeek());
		assertEquals(S1_ID, sList.get(0).getscheduleID());
		assertEquals(L1_ID, sList.get(0).getLibrarian().getemployeeIDNumber());
	}
	
	@Test
	public void testGetSchedulesByLibrarian_NullInput() {
		List<Schedule> sList = null;
		String error = null;
		try {
			sList = sService.getSchedulesByLibrarian(null);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(sList);
		assertNotNull(error);
		assertEquals("Argument must not be null.", error);
	}
	
	@Test
	public void testGetAllSchedules() {
		List<Schedule> sList = null;
		try {
			sList = sService.getAllSchedules();
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertNotNull(sList);
		assertEquals(1, sList.size());
		assertEquals(S1_DAY, sList.get(0).getDayofWeek());
	}
	
	@Test
	public void testUpdateSchedule() {
		Schedule updatedSchedule = null;
		Time openingTime = Time.valueOf("10:00:00");
		Time closingTime = Time.valueOf("17:00:00");
		DayOfWeek day = DayOfWeek.FRIDAY;
		//Librarian l = lRepo.findLibrarianByEmployeeIDNum(L1_ID);
		try {
			updatedSchedule = sService.updateSchedule(S1_ID, openingTime, closingTime, day, L1_ID);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertNotNull(updatedSchedule);
		assertEquals(openingTime, updatedSchedule.getOpeningTime());
		assertEquals(closingTime, updatedSchedule.getClosingTime());
		assertEquals(day, updatedSchedule.getDayofWeek());
		assertEquals(L1_ID, updatedSchedule.getLibrarian().getemployeeIDNumber());
	}
	
	@Test
	public void testUpdateSchedule_NotExisting() {
		// try to update schedule that does not exist in repository.
		String error = null;
		Schedule updatedSchedule = null;
		Time openingTime = Time.valueOf("10:00:00");
		Time closingTime = Time.valueOf("16:00:00");
		DayOfWeek day = DayOfWeek.MONDAY;
		//Librarian l = lRepo.findLibrarianByEmployeeIDNum(L1_ID);
		try {
			updatedSchedule = sService.updateSchedule(NAN_SCHEDULE_ID, openingTime, closingTime, day, null);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(updatedSchedule);
		assertNotNull(error);
		assertEquals("Could not find a schedule with the specified id (id: " + NAN_SCHEDULE_ID + ").", error);
	}
	
	@Test
	public void testUpdateSchedule_ClosingTimeBeforeOpeningTime() {
		Schedule updatedSchedule = null;
		String error = null;
		Time openingTime = Time.valueOf("16:00:00");
		Time closingTime = Time.valueOf("10:00:00");
		try {
			updatedSchedule = sService.updateSchedule(S1_ID, openingTime, closingTime, null, null);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(updatedSchedule);
		assertNotNull(error);
		assertEquals("Could not update schedule with a new closing time (" + closingTime + ") that is after current opening time (" + openingTime + ").", error);
	}
	
	@Test
	public void testDeleteSchedule() {
		Schedule s = sRepo.findScheduleByScheduleID(S1_ID);
		boolean deleted = sService.deleteSchedule(s);
		// schedule exists, so should have been deleted and return true
		assertTrue(deleted);
	}
	
	@Test
	public void testDeleteSchedule_NotExsiting() {
		// try to delete a schedule that does not exist in repository.
		Schedule s = sRepo.findScheduleByScheduleID(NAN_SCHEDULE_ID);
		boolean deleted = sService.deleteSchedule(s);
		// schedule does not exist so nothing deleted, so should return false
		assertFalse(deleted);
	}
	
	@Test
	public void testDeleteScheduleById() {
		boolean deleted = sService.deleteScheduleById(S1_ID);
		// schedule exists, so should have been deleted and return true
		assertTrue(deleted);
	}
	
	@Test
	public void testDeleteScheduleById_NotExsiting() {
		// try to delete a schedule that does not exist in repository.
		boolean deleted = sService.deleteScheduleById(NAN_SCHEDULE_ID);
		// schedule does not exist so nothing deleted, so should return false
		assertFalse(deleted);
	}
	
	
	// business methods (only one for service)
	
	@Test
	public void testCreateLibraryScheduleList() {
		HeadLibrarian hl = hlRepo.findHeadLibrarianByManagerIDNum(HL1_ID);
		// Create what default library schedule should be
		List<Schedule> expectedSchedule = sampleLibrarySchedule(hl);
		
		List<Schedule> newLibrarySchedule = null;
		try {
			newLibrarySchedule = sService.createLibraryScheduleList(HL1_ID, 
					"10:00:00", "14:00:00",
					"8:00:00", "18:00:00",
					"8:00:00", "18:00:00", 
					"8:00:00", "18:00:00",
					"8:00:00", "18:00:00",
					"8:00:00", "18:00:00",
					"10:00:00", "14:00:00");
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		assertNotNull(newLibrarySchedule);
		assertEquals(7, newLibrarySchedule.size());
		// Assert all schedules in list are equal
		for (int i = 0; i < 7; i++) {
			assertEquals(expectedSchedule.get(i), newLibrarySchedule.get(i));
		}			
		assertEquals(1, newLibrarySchedule.get(0).getscheduleID());
		assertEquals(DayOfWeek.SUNDAY, newLibrarySchedule.get(0).getDayofWeek());
		assertEquals(2, newLibrarySchedule.get(1).getscheduleID());
		assertEquals(DayOfWeek.MONDAY, newLibrarySchedule.get(1).getDayofWeek());
		assertEquals(3, newLibrarySchedule.get(2).getscheduleID());
		assertEquals(DayOfWeek.TUESDAY, newLibrarySchedule.get(2).getDayofWeek());
	}
	
	@Test
	public void testCreateLibraryScheduleList_MissingDay() {
		List<Schedule> s = null;
		String error = null;
		
		// no time inputted for monday
		try {
			s = sService.createLibraryScheduleList(HL1_ID,
					"10:00:00", "14:00:00", 
					null, null, 
					"10:00:00", "14:00:00", 
					"10:00:00", "14:00:00", 
					"10:00:00", "14:00:00", 
					"10:00:00", "14:00:00", 
					"10:00:00", "14:00:00");
		} catch (Exception e) {
			error = e.getMessage();
		}
		
		assertNull(s);
		assertNotNull(error);
		assertEquals("Arguments must not be null.", error);
	}
	
	@Test
	public void testCreateLibraryScheduleList_ClosingTimeBeforeOpeningTime() {
		List<Schedule> s = null;
		String error = null;
		
		// sunday opening time is after sunday closing time
		try {
			s = sService.createLibraryScheduleList(HL1_ID, "16:00:00", "14:00:00", "10:00:00", "14:00:00", "10:00:00", "14:00:00",
					"10:00:00", "14:00:00", "10:00:00", "14:00:00", "10:00:00", "14:00:00", "10:00:00", "14:00:00");
		} catch (Exception e) {
			error = e.getMessage();
		}
		
		assertNull(s);
		assertNotNull(error);
		assertEquals("Opening times must be before closing times.", error);
	}
	
	@Test
	public void testCreateDefaultLibraryScheduleList() {
		HeadLibrarian hl = hlRepo.findHeadLibrarianByManagerIDNum(HL1_ID);
		// Create what default library schedule should be
		List<Schedule> expectedSchedule = sampleLibrarySchedule(hl);
		
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
	
	@Test
	public void testTimeBefore() {
		Time t1 = Time.valueOf("09:01:01");
		Time t2 = Time.valueOf("14:00:00");
		boolean before = ScheduleService.timeBefore(t1, t2);
		assertTrue(before);
	}
	
	@Test
	public void testTimeBefore_NotBefore() {
		Time t1 = Time.valueOf("16:00:00");
		Time t2 = Time.valueOf("14:00:00");
		boolean before = ScheduleService.timeBefore(t1, t2);
		assertFalse(before);
	}
	
	// helper method to create sample schedule
	private List<Schedule> sampleLibrarySchedule(HeadLibrarian hl) {
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
		return expectedSchedule;
	}
}
