package ca.mcgill.ecse321.projectgroup09.dao;

import java.util.*;
import java.sql.Date;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.Member;
import ca.mcgill.ecse321.projectgroup09.models.Librarian;
import ca.mcgill.ecse321.projectgroup09.models.Booking;
/**
 * 
 * @author Zarif Ashraf
 *
 */
public interface BookingRepository extends CrudRepository<Booking, Long> {
	
	List<Booking> findByMember(Member member);
	List<Booking> findByLibrarian(Librarian librarian);
	Booking findBookingByBookingID(Long bookingID);
	List<Booking> findByBookingDate (Date date);
}