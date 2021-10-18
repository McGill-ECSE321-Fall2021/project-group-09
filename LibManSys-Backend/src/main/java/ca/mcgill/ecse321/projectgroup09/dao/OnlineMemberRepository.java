package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.OnlineMember;
/**
 * 
 * @author Zarif Ashraf
 *
 */
public interface OnlineMemberRepository extends CrudRepository<OnlineMember, Integer> {
	
	OnlineMember findOnlineMemberByLibCardNumber(Integer libCardNumber);
	OnlineMember findOnlineMemberByUserName(String memberUsername);
}
