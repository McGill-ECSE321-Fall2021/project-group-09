package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.Member;
import ca.mcgill.ecse321.projectgroup09.models.Booking;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import java.util.List;

public interface MemberRepository extends CrudRepository<Member, Long> {
	
	Member findMemberByLibCardNumber(Long libCardNumber);
	Member findMemberByBookings(Booking booking);
	Member findMemberByLoans(Loan loan);
	List<Member> findMemberByisVerified(boolean isVerified);
	List<Member> findMemberByisResident(boolean isResident);
}
