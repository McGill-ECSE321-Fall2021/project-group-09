//package ca.mcgill.ecse321.projectgroup09.dao;
//
//import java.util.List;
//import org.springframework.data.repository.CrudRepository;
//import ca.mcgill.ecse321.projectgroup09.models.*;
//
//public interface BookingRepository extends CrudRepository<Booking, Long> {
//	
//	List<Booking> findByMember(Member member);
//	List<Booking> findByLibrarian(Librarian librarian);
//	Booking findBookingByBookingID(Long bookingID);
//	
//}