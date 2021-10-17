package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.Booking;

public interface BookingRepository extends CrudRepository<Booking, Long> {
	
	Booking findBookingByBookingID(Long bookingID);
}