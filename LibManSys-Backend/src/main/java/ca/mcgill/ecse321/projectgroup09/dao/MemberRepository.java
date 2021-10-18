package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.Member;
import ca.mcgill.ecse321.projectgroup09.models.Booking;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import java.util.List;
/**
 * 
 * @author Zarif Ashraf
 *
 */
public interface MemberRepository extends CrudRepository<Member, Integer> {
	
	Member findMemberByLibCardNumber(Integer libCardNumber);
	Member findMemberByBooking(Booking booking);
	Member findMemberByLoan(Loan loan);
	List<Member> findMemberByVerificationStatus(boolean isVerified);
	List<Member> findMemberByResidencyStatus(boolean isResident);
}
