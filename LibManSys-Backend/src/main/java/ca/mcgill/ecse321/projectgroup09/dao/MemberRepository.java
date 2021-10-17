package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.Member;

public interface MemberRepository extends CrudRepository<Member, Long> {
	
	Member findMemberByLibCardNumber(Long libCardNumber);
}
