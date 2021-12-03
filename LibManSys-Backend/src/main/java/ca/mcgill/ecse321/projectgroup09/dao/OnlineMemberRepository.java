package ca.mcgill.ecse321.projectgroup09.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup09.models.Booking;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.OnlineMember;
/**
 * 
 * @author Zarif Ashraf
 *
 */
public interface OnlineMemberRepository extends CrudRepository<OnlineMember, Long> {

	OnlineMember findOnlineMemberByLibCardNumber(Long libCardNumber);
	OnlineMember findOnlineMemberByBookings(Booking booking);
	OnlineMember findOnlineMemberByLoans(Loan loan);
	List<OnlineMember> findOnlineMemberByisVerifiedResident(boolean isVerified);
	List<OnlineMember> findOnlineMemberByFullName(String fullName);
	OnlineMember findOnlineMemberByMemberUsername(String memberUsername);
}
