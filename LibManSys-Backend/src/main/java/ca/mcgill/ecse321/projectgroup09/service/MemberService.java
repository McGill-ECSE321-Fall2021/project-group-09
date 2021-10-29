package ca.mcgill.ecse321.projectgroup09.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup09.dao.MemberRepository;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;
import ca.mcgill.ecse321.projectgroup09.models.Member;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberDao;

	/**
	 * Get list of library items currently reserved by a member.
	 * 
	 * @param memberID Library card number of member.
	 * @return {@code List<LibraryItem>} List of LibraryItems.
	 */
	@Transactional
	public List<LibraryItem> getReservedItems(Long memberID) {
		if (Objects.isNull(memberID)) {
			throw new IllegalArgumentException("memberID argument must not be null");
		}
		
		Member m = memberDao.findMemberByLibCardNumber(memberID);
		if (Objects.isNull(m)) {
			throw new IllegalStateException("Member with library card number " + memberID + " , does not exists in Member repository.");
		}
		
		List<LibraryItem> reservedItems = m.getReserved();// = new ArrayList<LibraryItem>();
		/*
		for (LibraryItem li : m.getReserved()) {
			reservedItems.add(li.clone());
		}
		*/
		return reservedItems;
	}
}
